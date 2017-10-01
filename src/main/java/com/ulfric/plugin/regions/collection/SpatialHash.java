package com.ulfric.plugin.regions.collection;

import org.apache.commons.collections4.CollectionUtils;

import com.ulfric.commons.collection.Computations;
import com.ulfric.spatialregions.shape.Point;
import com.ulfric.spatialregions.shape.Shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public final class SpatialHash<V> {

	private final int sectionSize;
	private final Int2ObjectMap<List<Entry>> data = new Int2ObjectOpenHashMap<>();

	public SpatialHash(int sectionSize) {
		this.sectionSize = sectionSize;
		validateSectionSize();
	}

	private void validateSectionSize() {
		if (sectionSize < 1) {
			throw new IllegalArgumentException("Section size must be at least 1, was " + sectionSize);
		}
	}

	public void put(Shape shape, V value) {
		Objects.requireNonNull(shape, "shape");
		Objects.requireNonNull(value, "value");

		Point min = shape.getMin();
		Point max = shape.getMax();

		int size = this.sectionSize;
		int minX = min.getX() / size;
		int maxX = max.getX() / size;
		int minY = min.getY() / size;
		int maxY = max.getY() / size;
		int minZ = min.getZ() / size;
		int maxZ = max.getZ() / size;

		Int2ObjectMap<List<Entry>> data = this.data;
		for (int x = minX; x < maxX; x++) {
			for (int y = minY; y < maxY; y++) {
				for (int z = minZ; z < maxZ; z++) {
					int packed = this.pack(x, y, z);
					List<Entry> entries = data.computeIfAbsent(packed, Computations::newArrayListIgnoring);
					entries.add(new Entry(shape, value));
				}
			}
		}
	}

	public void remove(Predicate<V> value) { // TODO stop leaking empty lists if all entries are removed
		for (List<Entry> entries : data.values()) {
			Iterator<Entry> entriesIterator = entries.iterator();

			while (entriesIterator.hasNext()) {
				if (value.test(entriesIterator.next().value)) {
					entriesIterator.remove();
				}
			}
		}
	}

	public List<V> get(int x, int y, int z) {
		int packed = pack(x, y, z);

		List<Entry> entries = data.get(packed);
		if (CollectionUtils.isEmpty(entries)) {
			return Collections.emptyList();
		}

		int size = entries.size();
		List<V> values = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			Entry entry = entries.get(i);
			if (entry.shape.containsPoint(x, y, z)) {
				values.add(entry.value);
			}
		}
		return values;
	}

	private int pack(int x, int y, int z) {
		return (x << 16) | ((0xFF & z) << 8) | (y);
	}

	private final class Entry {
		final Shape shape;
		final V value;

		Entry(Shape shape, V value) {
			this.shape = shape;
			this.value = value;
		}
	}

}
