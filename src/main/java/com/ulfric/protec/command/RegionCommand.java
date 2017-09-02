package com.ulfric.protec.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Command;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.commons.naming.Name;

@Name("region")
@Alias("rg")
@Permission("region.use")
public class RegionCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO help command
	}

}
