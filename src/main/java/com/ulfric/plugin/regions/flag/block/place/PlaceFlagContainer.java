package com.ulfric.plugin.regions.flag.block.place;

import com.ulfric.commons.spatial.Flags;
import com.ulfric.dragoon.application.Container;

public class PlaceFlagContainer extends Container {

	public PlaceFlagContainer() { // TODO proper flag registration through Feature types
		addBootHook(() -> Flags.registerFlag(Place.class));

		addShutdownHook(() -> Flags.removeFlag(Place.class));

		install(PlaceListener.class);
	}

}