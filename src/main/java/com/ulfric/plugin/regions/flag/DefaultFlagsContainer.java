package com.ulfric.plugin.regions.flag;

import com.ulfric.dragoon.application.Container;

public class DefaultFlagsContainer extends Container {

	public DefaultFlagsContainer() {
		install(BreakFlagContainer.class);
		install(PlaceFlagContainer.class);
	}

}