package com.ulfric.protec.command;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.andrew.argument.Slug;
import com.ulfric.commons.naming.Name;
import com.ulfric.estate.Region;
import com.ulfric.i18n.content.Details;
import com.ulfric.protec.guard.GuardService;
import com.ulfric.servix.services.locale.TellService;

@Name("create")
@Permission("region.create")
public class RegionCreateCommand extends RegionCommand {

	@Argument
	@Slug
	private String name;

	@Argument(optional = true)
	private World world;

	@Override
	public void run(Context context) { // TODO use selection for region bounds rather than shapeless (empty)
		CommandSender sender = context.getSender();
		World world = world(sender);

		if (world == null) {
			TellService.sendMessage(sender, "regions-create-world-needed");
			return;
		}

		Region region = GuardService.getLastCreated().createRegion(world, name);
		if (region == null) {
			TellService.sendMessage(sender, "regions-create-already-exists", details());
			return;
		}

		Details details = details();
		details.add("createdRegion", region);
		TellService.sendMessage(sender, "regions-create", details);
	}

	private Details details() {
		Details details = new Details();
		details.add("regionName", name);
		details.add("regionWorld", world);
		return details;
	}

	private World world(CommandSender sender) {
		if (world != null) {
			return world;
		}

		return sender instanceof Entity ? ((Entity) sender).getWorld() : null;
	}

}