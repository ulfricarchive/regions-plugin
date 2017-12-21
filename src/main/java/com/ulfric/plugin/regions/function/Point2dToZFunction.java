package com.ulfric.plugin.regions.function;

import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.i18n.function.Function;

public class Point2dToZFunction extends Function<Point2d> {

	public Point2dToZFunction() {
		super("z", Point2d.class);
	}

	@Override
	public Object apply(Point2d point2d) {
		return point2d.getZ();
	}

}
