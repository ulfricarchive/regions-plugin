package com.ulfric.plugin.regions.guard;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.dragoon.conf4j.Settings;
import com.ulfric.plugin.regions.RegionService;
import com.ulfric.plugin.services.ServiceApplication;
import com.ulfric.spatialregions.Region;
import com.ulfric.spatialregions.RegionSpace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;

public class GuardService extends ServiceApplication implements RegionService<GuardService> {

	private static GuardService last; // TODO stop leaking instances - this is for performance so we can avoid the service lookup

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

	public boolean createRegion(Region region, UUID world) {
		Objects.requireNonNull(region, "region");
		Objects.requireNonNull(world, "world");

		if (regionExists(region.getName())) {
			return false;
		}

		getRegions(world).add(region);
		return true;
	}

	private boolean regionExists(String name) {
		return getRegionByName(name) != null;
	}

	public Region getRegionByName(String name) {
		Objects.requireNonNull(name, "name");

		for (Entry<UUID, PersistentSpatialHashRegions> regions : this.worlds.entrySet()) {
			Region existing = regions.getValue().getByName(name);

			if (existing != null) {
				return existing;
			}
		}
		return null;
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