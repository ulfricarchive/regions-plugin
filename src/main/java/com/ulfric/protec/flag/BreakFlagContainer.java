package com.ulfric.protec.flag;

import com.ulfric.dragoon.application.Container;
import com.ulfric.estate.Flags;

public class BreakFlagContainer extends Container {

	public BreakFlagContainer() { // TODO proper flag registration through Feature types
		addBootHook(() -> Flags.registerFlag(Break.class));

		addShutdownHook(() -> Flags.removeFlag(Break.class));

		install(BreakListener.class);
	}

}