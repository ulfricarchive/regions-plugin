package com.ulfric.plugin.regions.selection.command;

import org.bukkit.entity.Player;

import com.ulfric.commons.naming.Name;
import com.ulfric.plugin.commands.Context;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.locale.TellService;
import com.ulfric.plugin.regions.selection.Selection;
import com.ulfric.plugin.regions.selection.SelectionService;

@Name("clear")
@Permission("selection.clear")
public class SelectionClearCommand extends SelectionCommand {

	@Override
	public void run(Context context) {
		Player player = Context.getPlayer(context);

		Selection selection = SelectionService.get().getSelection(player.getUniqueId());
		selection.clear();

		TellService.sendMessage(player, "selection-cleared");
	}

}
