package com.ulfric.plugin.regions.guard;

import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.commons.value.Bean;

public class SquareDocument extends Bean {

	private Point2d positionOne;
	private Point2d positionTwo;

	public Point2d getPositionOne() {
		return positionOne;
	}

	public void setPositionOne(Point2d positionOne) {
		this.positionOne = positionOne;
	}

	public Point2d getPositionTwo() {
		return positionTwo;
	}

	public void setPositionTwo(Point2d positionTwo) {
		this.positionTwo = positionTwo;
	}

}
