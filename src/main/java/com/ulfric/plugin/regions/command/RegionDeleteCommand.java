package com.ulfric.plugin.regions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spatial.Region;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.plugin.restrictions.command.Restricted;

@Name("delete")
@Permission("region-delete")
@Restricted("RegionDelete")
public class RegionDeleteCommand extends RegionCommand {

	@Argument
	private Region region;

	@Override
	public void run() {
		GuardService.getLastCreated().deleteRegion(region);

		tell("regions-delete");
	}

}