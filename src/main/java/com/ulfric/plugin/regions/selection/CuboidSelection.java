package com.ulfric.plugin.regions.selection;

import com.google.common.collect.Iterators;

import com.ulfric.commons.value.Bean;
import com.ulfric.spatialregions.shape.Square;
import com.ulfric.spatialregions.shape.Point2d;
import com.ulfric.spatialregions.shape.Shape;

import java.util.Iterator;

public final class CuboidSelection extends Bean implements Selection {

	private Point2d x;
	private Point2d z;

	@Override
	public Iterator<Point2d> iterator() {
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
	public void start(Point2d point) {
		x = point;
		z = null;
	}

	@Override
	public void add(Point2d point) {
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

		return new Square(x, z);
	}

}
