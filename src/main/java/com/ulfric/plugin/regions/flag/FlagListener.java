package com.ulfric.plugin.regions.flag;

import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;

public abstract class FlagListener implements Listener {

	protected abstract boolean testPermissions(Permissible permissible);

}
