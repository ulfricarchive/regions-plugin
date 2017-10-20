package com.ulfric.plugin.regions.command;

import org.bukkit.World;
import org.bukkit.entity.Player;

import com.ulfric.commons.bukkit.world.WorldHelper;
import com.ulfric.commons.naming.Name;
import com.ulfric.i18n.content.Details;
import com.ulfric.plugin.commands.Permission;
import com.ulfric.plugin.commands.Restricted;
import com.ulfric.plugin.commands.argument.Argument;
import com.ulfric.plugin.commands.argument.Slug;
import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.plugin.regions.selection.Selection;
import com.ulfric.plugin.regions.selection.SelectionService;
import com.ulfric.spatialregions.Region;
import com.ulfric.spatialregions.shape.Empty;
import com.ulfric.spatialregions.shape.Shape;

@Name("create")
@Permission("region.create")
@Restricted("RegionCreate")
public class RegionCreateCommand extends RegionCommand {

	@Argument(optional = true)
	@Slug
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

		Region region = Region.builder()
				.setName(name)
				.setWeight(weight)
				.setBounds(bounds())
				.build();

		if (!GuardService.getLastCreated().createRegion(region, world.getUID())) {
			tell("regions-create-already-exists");
			return;
		}

		Details details = details();
		details.add("region", region);
		tell("regions-create", details);
	}

	private Shape bounds() {
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