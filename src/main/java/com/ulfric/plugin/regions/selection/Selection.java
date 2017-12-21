package com.ulfric.plugin.regions.selection;

import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.commons.spatial.shape.Square;

public interface Selection extends Iterable<Point2d> {

	boolean isComplete();

	void primary(Point2d point);

	void secondary(Point2d point);

	void clear();

	Square toSquare();

}