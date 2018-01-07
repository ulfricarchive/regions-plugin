package com.ulfric.plugin.regions;

import com.ulfric.plugin.Plugin;
import com.ulfric.plugin.regions.command.RegionCommand;
import com.ulfric.plugin.regions.command.RegionCreateCommand;
import com.ulfric.plugin.regions.command.RegionDeleteCommand;
import com.ulfric.plugin.regions.command.RegionResolver;
import com.ulfric.plugin.regions.flag.DefaultFlagsContainer;
import com.ulfric.plugin.regions.function.Point2dToXFunction;
import com.ulfric.plugin.regions.function.Point2dToZFunction;
import com.ulfric.plugin.regions.function.RegionToNameFunction;
import com.ulfric.plugin.regions.function.RegionToWeightFunction;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.plugin.regions.selection.SelectionContainer;

public class RegionsPlugin extends Plugin {

	public RegionsPlugin() {
		install(GuardService.class);
		install(DefaultFlagsContainer.class);
		install(SelectionContainer.class);

		install(Point2dToXFunction.class);
		install(Point2dToZFunction.class);
		install(RegionToNameFunction.class);
		install(RegionToWeightFunction.class);

		install(RegionResolver.class);

		install(RegionCommand.class);
		install(RegionCreateCommand.class);
		install(RegionDeleteCommand.class);

		install(RegionCheckToolListener.class);
	}

}
