package com.ulfric.plugin.regions.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.Permission;

@Name("region")
@Alias("rg")
@Permission("region.use")
public class RegionCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO help command
	}

}
