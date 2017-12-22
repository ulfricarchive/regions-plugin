package com.ulfric.plugin.regions.selection;

import java.util.Iterator;

import com.google.common.collect.Iterators;
import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.commons.spatial.shape.Square;
import com.ulfric.commons.value.Bean;

public final class SquareSelection extends Bean implements Selection {

	private Point2d x;
	private Point2d z;

	@Override
	public Iterator<Point2d> iterator() {
		return Iterators.forArray(x, z);
	}

	@Override
	public boolean isComplete() {
		return x != null && z != null;
	}

	@Override
	public void primary(Point2d point) {
		x = point;
		z = null;
	}

	@Override
	public void secondary(Point2d point) {
		z = point;
	}

	@Override
	public void clear() {
		x = null;
		z = null;
	}

	@Override
	public Square toSquare() {
		if (!isComplete()) {
			throw new IllegalStateException("CuboidSelection not complete; x=" + x + ", y=" + z);
		}

		return new Square(x, z);
	}

}
