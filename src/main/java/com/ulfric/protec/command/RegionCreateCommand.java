package com.ulfric.protec.command;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import com.ulfric.andrew.Context;
import com.ulfric.andrew.Permission;
import com.ulfric.andrew.argument.Argument;
import com.ulfric.andrew.argument.Slug;
import com.ulfric.commons.naming.Name;
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
	public void run(Context context) {
		CommandSender sender = context.getSender();
		World world = world(sender);

		if (world == null) {
			TellService.sendMessage(sender, "regions-create-world-needed");
			return;
		}

		// TODO finish
	}

	private World world(CommandSender sender) {
		if (world != null) {
			return world;
		}

		return sender instanceof Entity ? ((Entity) sender).getWorld() : null;
	}

}