package com.ulfric.plugin.regions.selection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.bukkit.item.ItemStackHelper;
import com.ulfric.commons.bukkit.player.InteractEventHelper;
import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.locale.TellService;

public class SelectionListener implements Listener {

	@Inject
	private SelectionService service;

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}

		if (!InteractEventHelper.isClickBlock(event)) {
			return;
		}

		if (!isWand(event.getItem())) {
			return;
		}

		Player player = event.getPlayer();
		if (!player.hasPermission("regions-selection-create")) {
			return;
		}

		Selection selection = service.getSelection(player.getUniqueId());

		Point2d point = blockToPoint(block);

		Details details = Details.of(
				Detail.of("selection", selection),
				Detail.of("point", point),
				Detail.of("block", block));

		if (InteractEventHelper.isLeftClickBlock(event)) {
			selection.primary(point);
			TellService.sendMessage(player, "regions-selection-primary", details);
		} else {
			selection.secondary(point);
			TellService.sendMessage(player, "regions-selection-secondary", details);
		}
	}

	private boolean isWand(ItemStack item) {
		return item != null && !item.hasItemMeta() && ItemStackHelper.matches(item, Material.WOOD_AXE); // TODO configurable
	}

	private Point2d blockToPoint(Block block) {
		return Point2d.builder()
				.setX(block.getX())
				.setZ(block.getZ())
				.build();
	}

}
