package com.ulfric.protec.flag;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.ulfric.estate.Region;
import com.ulfric.protec.guard.GuardService;

import java.util.List;

public class BreakListener extends FlagListener { // TODO handle slime blocks in later versions

	@EventHandler(ignoreCancelled = true)
	public void on(BlockBreakEvent event) {
		if (testPermissions(event.getPlayer())) {
			return;
		}

		cancelIfDenied(event.getBlock(), event);
	}

	@EventHandler(ignoreCancelled = true)
	public void on(BlockPlaceEvent event) {
		if (testPermissions(event.getPlayer())) {
			return;
		}

		if (event.getBlockReplacedState().getType() != Material.AIR) {
			cancelIfDenied(event.getBlock(), event);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void on(EntityChangeBlockEvent event) {
		if (testPermissions(event.getEntity())) {
			return;
		}

		if (event.getTo() == Material.AIR) {
			cancelIfDenied(event.getBlock(), event);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void on(EntityExplodeEvent event) {
		if (testPermissions(event.getEntity())) {
			return;
		}

		removeAllDenied(event.blockList());
	}

	@EventHandler(ignoreCancelled = true)
	public void on(BlockBurnEvent event) {
		cancelIfDenied(event.getBlock(), event);
	}

	@EventHandler(ignoreCancelled = true)
	public void on(BlockPistonRetractEvent event) {
		if (!event.isSticky()) {
			return;
		}

		List<Region> placer = GuardService.getRegionsAt(event.getBlock());
		List<Region> broken = GuardService.getRegionsAt(event.getRetractLocation());
		broken.removeAll(placer);

		if (broken.isEmpty()) {
			return;
		}

		cancelIfDenied(broken, event);
	}

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerInteractEvent event) {
		if (event.getAction() != Action.LEFT_CLICK_BLOCK) {
			return;
		}

		if (testPermissions(event.getPlayer())) {
			return;
		}

		Block fire = event.getClickedBlock().getRelative(event.getBlockFace());

		if (fire.getType() != Material.FIRE) {
			return;
		}

		if (cancelsIfDenied(fire, event)) {
			event.setUseInteractedBlock(Result.DENY);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerBucketFillEvent event) {
		if (testPermissions(event.getPlayer())) {
			return;
		}

		Block block = event.getBlockClicked().getRelative(event.getBlockFace());

		cancelIfDenied(block, event);
	}

}