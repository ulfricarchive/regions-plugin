package com.ulfric.protec;

import com.ulfric.plugin.Plugin;
import com.ulfric.protec.flag.DefaultFlagsContainer;

public class Protec extends Plugin {

	public Protec() {
		install(DefaultFlagsContainer.class);
	}

}
