package com.ulfric.plugin.regions.function;

import com.ulfric.commons.spatial.Region;
import com.ulfric.i18n.function.Function;

public abstract class RegionFunction extends Function<Region> {

	public RegionFunction(String name) {
		super(name, Region.class);
	}

}
