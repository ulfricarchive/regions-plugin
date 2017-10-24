package com.ulfric.plugin.regions.flag.block.place;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockCanChangeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.permissions.Permissible;

import com.ulfric.commons.spatial.Region;
import com.ulfric.plugin.regions.flag.block.BlockEventFlagListener;

public class PlaceListener extends BlockEventFlagListener { // TODO handle all place events

	@EventHandler(ignoreCancelled = true)
	public void on(BlockPlaceEvent event) {
		if (testPermissions(event)) {
			return;
		}

		cancelIfDenied(event);
	}

	@EventHandler
	public void on(BlockCanChangeEvent event) {
		if (testPermissions(event)) {
			return;
		}

		if (isDenied(event)) {
			event.setChangeable(false);
		}
	}

	private boolean testPermissions(BlockCanChangeEvent event) {
		return event.hasPlayer() && testPermissions(event.getPlayer());
	}

	@Override
	protected boolean isDenied(Block block) {
		return !Place.isPlaceAllowed(block);
	}

	@Override
	protected boolean isDenied(List<Region> regions) {
		return !Place.isPlaceAllowed(regions);
	}

	@Override
	protected boolean testPermissions(Permissible permissible) {
		return permissible.hasPermission("regions-place");
	}

}