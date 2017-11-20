package com.ulfric.plugin.regions.selection;

import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.commons.spatial.shape.Shape;

public interface Selection extends Iterable<Point2d> {

	int size();

	boolean isComplete();

	void start(Point2d point);

	void add(Point2d point);

	void clear();

	Shape toShape();

}