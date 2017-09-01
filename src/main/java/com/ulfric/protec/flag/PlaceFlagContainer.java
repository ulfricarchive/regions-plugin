package com.ulfric.protec.flag;

import com.ulfric.dragoon.application.Container;
import com.ulfric.estate.Flags;

public class PlaceFlagContainer extends Container {

	public PlaceFlagContainer() { // TODO proper flag registration through Feature types
		addBootHook(() -> Flags.registerFlag(Place.class));

		addShutdownHook(() -> Flags.removeFlag(Place.class));

		install(PlaceListener.class);
	}

}