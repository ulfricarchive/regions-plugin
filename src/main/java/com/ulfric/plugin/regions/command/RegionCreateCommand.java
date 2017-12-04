package com.ulfric.plugin.regions.command;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.ulfric.commons.bukkit.world.WorldHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.shape.Empty;
import com.ulfric.commons.spatial.shape.Shape;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.commands.argument.Slug;
import com.ulfric.plugin.commands.permissions.Permission;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.plugin.regions.selection.Selection;
import com.ulfric.plugin.regions.selection.SelectionService;
import com.ulfric.plugin.restrictions.command.Restricted;

@Name("create")
@Permission("region.create")
@Restricted("RegionCreate")
public class RegionCreateCommand extends RegionCommand {

	@Slug
	@Argument(optional = true)
	private String name;

	@Argument(optional = true)
	private World world;

	@Argument(optional = true)
	private int weight;

	@Override
	public void run() { // TODO use selection for region bounds rather than shapeless (empty)
		if (name == null) {
			requestOnSign("regions-create-sign-give-name", "/region create");
			return;
		}

		defaultWorldIfMissing();

		Region region = createRegionBean();

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

	private Region createRegionBean() {
		return Region.builder()
				.setName(name)
				.setWeight(weight)
				.setBounds(createBoundsBean())
				.build();
	}

	private Shape createBoundsBean() {
		return ifPlayer(player -> {
			Selection selection = SelectionService.get().getSelection(player.getUniqueId());

			if (selection.isComplete()) {
				return selection.toShape();
			}

			return Empty.INSTANCE; // TODO warn the player?
		}).orElse(Empty.INSTANCE);
	}

	private void defaultWorldIfMissing() {
		if (world == null) {
			world = ifPlayer(Player::getWorld).orElseGet(WorldHelper::getMainWorld);
		}
	}

}