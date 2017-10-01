package com.ulfric.plugin.regions.command;

import com.ulfric.plugin.commands.argument.ResolutionRequest;
import com.ulfric.plugin.commands.argument.Resolver;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.spatialregions.Region;

public class RegionResolver extends Resolver<Region> {

	public RegionResolver() {
		super(Region.class);
	}

	@Override
	public Region apply(ResolutionRequest request) {
		return GuardService.getLastCreated().getRegionByName(request.getArgument());
	}

}
