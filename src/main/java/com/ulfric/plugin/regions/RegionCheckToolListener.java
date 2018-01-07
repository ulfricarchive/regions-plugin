package com.ulfric.plugin.regions;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.ulfric.commons.bukkit.item.ItemStackHelper;
import com.ulfric.commons.spatial.Region;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.locale.TellService;
import com.ulfric.plugin.regions.guard.GuardService;

public class RegionCheckToolListener implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void on(PlayerInteractEvent event) {
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}

		if (!ItemStackHelper.matches(event.getItem(), Material.LEATHER)) {
			return;
		}

		Player player = event.getPlayer();
		if (!player.hasPermission("region-check")) {
			return;
		}

		List<Region> regions = GuardService.getRegionsAt(event.getClickedBlock());
		if (CollectionUtils.isEmpty(regions)) {
			TellService.sendMessage(player, "regions-check-empty");
			return;
		}

		TellService.sendMessage(player, "regions-check", Details.of("regions", regions));
	}

}
