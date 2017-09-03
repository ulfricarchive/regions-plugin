package com.ulfric.protec.selection;

import com.google.common.collect.Iterators;

import com.ulfric.commons.value.Bean;
import com.ulfric.estate.shape.Cube;
import com.ulfric.estate.shape.Point;
import com.ulfric.estate.shape.Shape;
import com.ulfric.servix.services.region.Selection;

import java.util.Iterator;

public final class CuboidSelection extends Bean implements Selection {

	private Point x;
	private Point z;

	@Override
	public Iterator<Point> iterator() {
		return Iterators.forArray(x, z);
	}

	@Override
	public int size() {
		int size = 0;
		if (x != null) {
			size++;
		}
		if (z != null) {
			size++;
		}
		return size;
	}

	@Override
	public boolean isComplete() {
		return x != null && z != null;
	}

	@Override
	public void start(Point point) {
		x = point;
		z = null;
	}

	@Override
	public void add(Point point) {
		if (x == null) {
			start(point);
			return;
		}

		z = point;
	}

	@Override
	public void clear() {
		x = null;
		z = null;
	}

	@Override
	public Shape toShape() {
		if (!isComplete()) {
			throw new IllegalStateException("CuboidSelection not complete; x=" + x + ", y=" + z);
		}

		return new Cube(x, z);
	}

}
