package com.ulfric.plugin.regions.selection;

import com.ulfric.spatialregions.shape.Point;
import com.ulfric.spatialregions.shape.Shape;

public interface Selection extends Iterable<Point> {

	int size();

	boolean isComplete();

	void start(Point point);

	void add(Point point);

	void clear();

	Shape toShape();

}