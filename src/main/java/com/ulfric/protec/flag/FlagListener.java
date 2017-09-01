package com.ulfric.protec.flag;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;

import com.ulfric.estate.Region;

import java.util.Iterator;
import java.util.List;

public class FlagListener implements Listener {

	protected final boolean testPermissions(Permissible permissible) {
		return permissible.hasPermission("protec-break");
	}

	protected final void cancelIfDenied(Block block, Cancellable event) {
		if (!Break.isBreakAllowed(block)) {
			event.setCancelled(true);
		}
	}

	protected final boolean cancelsIfDenied(Block block, Cancellable event) {
		if (!Break.isBreakAllowed(block)) {
			event.setCancelled(true);
			return true;
		}

		return false;
	}

	protected final void cancelIfDenied(List<Region> regions, Cancellable event) {
		if (!Break.isBreakAllowed(regions)) {
			event.setCancelled(true);
		}
	}

	protected final void removeAllDenied(Iterable<Block> blocks) {
		Iterator<Block> iterator = blocks.iterator();
		while (iterator.hasNext()) {
			if (!Break.isBreakAllowed(iterator.next())) {
				iterator.remove();
			}
		}
	}

}
