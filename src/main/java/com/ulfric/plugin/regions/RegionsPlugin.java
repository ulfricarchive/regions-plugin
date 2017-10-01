package com.ulfric.plugin.regions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.regions.command.RegionCommand;
import com.ulfric.plugin.regions.command.RegionCreateCommand;
import com.ulfric.plugin.regions.flag.DefaultFlagsContainer;
import com.ulfric.plugin.regions.selection.SelectionContainer;
import com.ulfric.plugin.regions.selection.command.SelectionClearCommand;
import com.ulfric.plugin.regions.selection.command.SelectionCommand;

public class RegionsPlugin extends Plugin {

	public RegionsPlugin() {
		install(DefaultFlagsContainer.class);
		install(SelectionContainer.class);

		install(RegionCommand.class);
		install(RegionCreateCommand.class);
		install(SelectionCommand.class);
		install(SelectionClearCommand.class);
	}

}
