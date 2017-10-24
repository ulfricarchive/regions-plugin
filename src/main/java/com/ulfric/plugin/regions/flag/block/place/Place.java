package com.ulfric.plugin.regions.flag.block.place;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.commons.spatial.Flag;
import com.ulfric.commons.spatial.Flags;
import com.ulfric.commons.spatial.Region;
import com.ulfric.plugin.regions.guard.GuardService;

public interface Place extends Flag {

	boolean IS_PLACE_ALLOWED_DEFAULT = false;

	static boolean isPlaceAllowed(Block block) {
		return isPlaceAllowed(GuardService.getRegionsAt(block));
	}

	static boolean isPlaceAllowed(Location location) {
		return isPlaceAllowed(GuardService.getRegionsAt(location));
	}

	static boolean isPlaceAllowed(List<Region> regions) {
		if (regions.isEmpty()) {
			return true;
		}

		for (Region region : regions) {
			Flags flags = region.getFlags();

			if (flags instanceof Place) {
				return ((Place) flags).isPlaceAllowed();
			}
		}

		return IS_PLACE_ALLOWED_DEFAULT;
	}

	boolean isPlaceAllowed();

}
