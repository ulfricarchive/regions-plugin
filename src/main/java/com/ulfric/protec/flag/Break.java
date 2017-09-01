package com.ulfric.protec.flag;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.estate.Flag;
import com.ulfric.estate.Flags;
import com.ulfric.estate.Region;
import com.ulfric.protec.guard.GuardService;

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
