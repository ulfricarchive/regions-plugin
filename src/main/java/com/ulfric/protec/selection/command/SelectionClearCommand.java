package com.ulfric.protec.selection.command;

import org.bukkit.entity.Player;

import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.commons.naming.Name;
import com.ulfric.servix.services.locale.TellService;
import com.ulfric.servix.services.region.Selection;
import com.ulfric.servix.services.region.SelectionService;

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
