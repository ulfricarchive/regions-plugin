package com.ulfric.plugin.regions.flag.block.breaks;

import com.ulfric.commons.spatial.flag.Flags;
import com.ulfric.dragoon.application.Container;

public class BreakFlagContainer extends Container {

	public BreakFlagContainer() { // TODO proper flag registration through Feature types
		addBootHook(() -> Flags.registerFlag(Break.class));

		addShutdownHook(() -> Flags.unregisterFlag(Break.class));

		install(BreakListener.class);
	}

}