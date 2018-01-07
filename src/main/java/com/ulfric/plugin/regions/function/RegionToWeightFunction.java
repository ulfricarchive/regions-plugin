package com.ulfric.plugin.regions.function;

import com.ulfric.commons.spatial.Region;

public class RegionToWeightFunction extends RegionFunction {

	public RegionToWeightFunction() {
		super("weight");
	}

	@Override
	public Object apply(Region region) {
		return region.getWeight();
	}

}
