package com.ulfric.plugin.regions.flag.block;

import java.util.Iterator;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.block.BlockEvent;

import com.ulfric.plugin.regions.flag.EventFlagListener;
import com.ulfric.spatialregions.Region;

public abstract class BlockEventFlagListener extends EventFlagListener<BlockEvent> {

	protected final <T extends BlockEvent & Cancellable> void cancelIfDenied(T event) {
		if (isDenied(event)) {
			event.setCancelled(true);
		}
	}

	protected final <T extends BlockEvent & Cancellable> boolean canceslIfDenied(T event) {
		if (isDenied(event)) {
			event.setCancelled(true);
			return true;
		}

		return false;
	}

	protected final void cancelIfDenied(Block block, Cancellable event) {
		if (isDenied(block)) {
			event.setCancelled(true);
		}
	}

	protected final boolean cancelsIfDenied(Block block, Cancellable event) {
		if (isDenied(block)) {
			event.setCancelled(true);
			return true;
		}

		return false;
	}

	protected final void cancelIfDenied(List<Region> regions, Cancellable event) {
		if (isDenied(regions)) {
			event.setCancelled(true);
		}
	}

	protected final void removeAllDenied(Iterable<Block> blocks) {
		Iterator<Block> iterator = blocks.iterator();
		while (iterator.hasNext()) {
			if (isDenied(iterator.next())) {
				iterator.remove();
			}
		}
	}

	@Override
	protected final boolean isDenied(BlockEvent event) {
		return isDenied(event.getBlock());
	}

	protected abstract boolean isDenied(Block block);

}
