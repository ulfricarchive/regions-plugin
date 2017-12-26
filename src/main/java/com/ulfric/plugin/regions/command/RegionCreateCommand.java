package com.ulfric.plugin.regions.command;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.ulfric.commons.bukkit.world.WorldHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.shape.Square;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.commands.argument.Slug;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.plugin.regions.selection.Selection;
import com.ulfric.plugin.regions.selection.SelectionService;
import com.ulfric.plugin.restrictions.command.Restricted;

@Name("create")
@Permission("region-create")
@Restricted("RegionCreate")
public class RegionCreateCommand extends RegionCommand {

	@Inject
	private SelectionService selection;

	@Slug
	@Argument(optional = true)
	private String name;

	@Argument(optional = true)
	private int weight;

	@Argument(optional = true)
	private World world;

	@Override
	public void run() {
		if (name == null) {
			requestOnSign("regions-create-sign-give-name", "/region create");
			return;
		}

		defaultWorldIfMissing();

		Region region = createRegion();

		if (region == null) {
			tell("regions-create-select-region");
			return;
		}

		if (!executeRegionCreation(region)) {
			tell("regions-create-failed");
			return;
		}

		notifyCreationSuccess(region);
	}

	private void notifyCreationSuccess(Region region) {
		Details details = details();
		details.add("region", region);
		tell("regions-create", details);
	}

	private boolean executeRegionCreation(Region region) {
		return GuardService.getLastCreated().createRegion(region, world.getUID());
	}

	private Region createRegion() {
		Square square = createSquare();
		if (square == null) {
			return null;
		}
		return Region.builder()
				.setName(name)
				.setWeight(weight)
				.setBounds(square)
				.build();
	}

	private Square createSquare() {
		return ifPlayer(player -> {
			Selection selection = this.selection.getSelection(player.getUniqueId());

			if (!selection.isComplete()) {
				return null;
			}

			return selection.toSquare();
		}).orElse(null);
	}

	private void defaultWorldIfMissing() {
		if (world == null) {
			world = ifPlayer(Player::getWorld).orElseGet(WorldHelper::getMainWorld);
		}
	}

}