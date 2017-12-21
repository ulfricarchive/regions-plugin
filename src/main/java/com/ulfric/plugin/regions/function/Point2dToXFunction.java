package com.ulfric.plugin.regions.function;

import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.i18n.function.Function;

public class Point2dToXFunction extends Function<Point2d> {

	public Point2dToXFunction() {
		super("x", Point2d.class);
	}

	@Override
	public Object apply(Point2d point2d) {
		return point2d.getX();
	}

}
