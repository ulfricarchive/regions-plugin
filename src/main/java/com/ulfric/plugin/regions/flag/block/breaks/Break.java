package com.ulfric.plugin.regions.flag.block.breaks;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.plugin.regions.guard.GuardService;
import com.ulfric.spatialregions.Flag;
import com.ulfric.spatialregions.Flags;
import com.ulfric.spatialregions.Region;

import java.util.List;

public interface Break extends Flag {

	boolean IS_BREAK_ALLOWED_DEFAULT = false;

	static boolean isBreakAllowed(Block block) {
		return isBreakAllowed(GuardService.getRegionsAt(block));
	}

	static boolean isBreakAllowed(Location location) {
		return isBreakAllowed(GuardService.getRegionsAt(location));
	}

	static boolean isBreakAllowed(List<Region> regions) {
		if (regions.isEmpty()) {
			return true;
		}

		for (Region region : regions) {
			Flags flags = region.getFlags();

			if (flags instanceof Break) {
				return ((Break) flags).isBreakAllowed();
			}
		}

		return IS_BREAK_ALLOWED_DEFAULT;
	}

	boolean isBreakAllowed();

}
