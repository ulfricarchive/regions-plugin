package com.ulfric.plugin.regions.flag;

import com.ulfric.dragoon.application.Container;
import com.ulfric.plugin.regions.flag.block.breaks.BreakFlagContainer;
import com.ulfric.plugin.regions.flag.block.place.PlaceFlagContainer;

public class DefaultFlagsContainer extends Container {

	public DefaultFlagsContainer() {
		install(BreakFlagContainer.class);
		install(PlaceFlagContainer.class);
	}

}