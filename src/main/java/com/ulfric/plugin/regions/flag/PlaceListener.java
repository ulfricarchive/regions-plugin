package com.ulfric.plugin.regions.flag;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener extends FlagListener { // TODO handle all place events

	@EventHandler(ignoreCancelled = true)
	public void on(BlockPlaceEvent event) {
		if (testPermissions(event.getPlayer())) {
			return;
		}

		cancelIfDenied(event.getBlock(), event);
	}

}