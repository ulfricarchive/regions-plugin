package com.ulfric.protec.guard;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.data.config.Settings;
import com.ulfric.estate.Region;
import com.ulfric.estate.RegionSpace;
import com.ulfric.protec.RegionService;
import com.ulfric.servix.ServiceApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GuardService extends ServiceApplication implements RegionService<GuardService> {

	private static GuardService last; // TODO stop leaking instances

	public static GuardService getLastCreated() {
		return last;
	}

	public static List<Region> getRegionsAt(Block block) {
		RegionSpace space = last.getRegions(block.getWorld().getUID());
		return space.getRegions(block.getX(), block.getY(), block.getZ());
	}

	public static List<Region> getRegionsAt(Location location) {
		RegionSpace space = last.getRegions(location.getWorld().getUID());
		return space.getRegions(location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	@Settings
	private RegionsFile regions;

	private final Map<UUID, PersistentSpatialHashRegions> worlds = new HashMap<>();

	public GuardService() {
		last = this;

		addBootHook(this::loadRegions);
		addBootHook(this::clearRegions);
	}

	@Override
	public Class<GuardService> getService() {
		return GuardService.class;
	}

	@Override
	public PersistentSpatialHashRegions getRegions(UUID world) {
		return worlds.computeIfAbsent(world, key -> new PersistentSpatialHashRegions(key, regions));
	}

	List<RegionData> getRegionData() {
		return regions.getRegions();
	}

	void updateRegions(List<RegionData> data) {
		regions.setRegions(data);
	}

	private void loadRegions() {
		regions.getRegions().forEach(this::loadRegion);
	}

	private void loadRegion(RegionData data) {
		Region region = RegionFileHelper.regionFromData(data);

		PersistentSpatialHashRegions space = getRegions(data.getWorld());
		space.addFromFile(region);
	}

	private void clearRegions() {
		worlds.clear();
	}

}