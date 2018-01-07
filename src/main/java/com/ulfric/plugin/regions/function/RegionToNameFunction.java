package com.ulfric.plugin.regions.function;

import com.ulfric.commons.spatial.Region;

public class RegionToNameFunction extends RegionFunction {

	public RegionToNameFunction() {
		super("name");
	}

	@Override
	public Object apply(Region region) {
		return region.getName();
	}

}
