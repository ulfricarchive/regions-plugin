package com.ulfric.plugin.regions.selection.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Alias;
import com.ulfric.plugin.commands.Command;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.Permission;

@Name("selection")
@Alias("sel")
@Permission("selection.use")
public class SelectionCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO help command
	}

}
