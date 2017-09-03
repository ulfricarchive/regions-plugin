package com.ulfric.protec.selection.command;

import com.ulfric.andrew.Alias;
import com.ulfric.andrew.Command;
import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.commons.naming.Name;

@Name("selection")
@Alias("sel")
@Permission("selection.use")
public class SelectionCommand implements Command {

	@Override
	public void run(Context context) {
		// TODO help command
	}

}
