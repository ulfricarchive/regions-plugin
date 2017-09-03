package com.ulfric.protec.selection;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.ulfric.commons.spigot.item.ItemStackHelper;
import com.ulfric.commons.spigot.player.InteractEventHelper;
import com.ulfric.estate.shape.Point;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;
import com.ulfric.servix.services.locale.TellService;
import com.ulfric.servix.services.region.Selection;
import com.ulfric.servix.services.region.SelectionService;

public class SelectionListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerInteractEvent event) {
		Block block = event.getClickedBlock();
		if (block == null) {
			return;
		}

		ItemStack item = event.getItem();
		if (item.hasItemMeta() || !ItemStackHelper.matches(item, Material.WOOD_AXE)) {// TODO configurable
			return;
		}

		if (!InteractEventHelper.isClickBlock(event)) {
			return;
		}

		Player player = event.getPlayer();
		if (!player.hasPermission("selection.create")) {
			return;
		}

		SelectionService service = SelectionService.get();
		Selection selection = service.getSelection(player.getUniqueId());

		Point point = Point.builder()
				.setX(block.getX())
				.setY(block.getY())
				.setZ(block.getZ())
				.build();
		if (InteractEventHelper.isLeftClickBlock(event)) {
			selection.start(point);
			TellService.sendMessage(player, "selection-started", Details.of("point", point));
		} else {
			selection.add(point);
			TellService.sendMessage(player, "selection-added-point", Details.of(
					Detail.of("selection", selection),
					Detail.of("point", point)));
		}
	}

}
