package com.ulfric.plugin.regions.selection.command;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.regions.selection.Selection;
import com.ulfric.plugin.regions.selection.SelectionService;

@Name("clear")
@Permission("selection.clear")
public class SelectionClearCommand extends SelectionCommand {

	@Override
	public void run() {
		Selection selection = SelectionService.get().getSelection(uniqueId());
		selection.clear();

		tell("selection-cleared");
	}

}
