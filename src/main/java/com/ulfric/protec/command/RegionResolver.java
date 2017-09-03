package com.ulfric.protec.command;

import com.ulfric.andrew.argument.ResolutionRequest;
import com.ulfric.andrew.argument.Resolver;
import com.ulfric.estate.Region;
import com.ulfric.protec.guard.GuardService;

public class RegionResolver extends Resolver<Region> {

	public RegionResolver() {
		super(Region.class);
	}

	@Override
	public Region apply(ResolutionRequest request) {
		return GuardService.getLastCreated().getRegionByName(request.getArgument());
	}

}
