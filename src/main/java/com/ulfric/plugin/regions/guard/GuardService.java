package com.ulfric.plugin.regions.guard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.block.Block;

import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.RegionSpace;
import com.ulfric.dragoon.acrodb.Database;
import com.ulfric.dragoon.acrodb.Store;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.logging.Log;
import com.ulfric.plugin.regions.RegionService;
import com.ulfric.plugin.services.ServiceApplication;

public class GuardService extends ServiceApplication implements RegionService<GuardService> {

	private static GuardService last; // TODO stop leaking instances - this is for performance so we can avoid the service lookup

	public static GuardService getLastCreated() {
		return last;
	}

	public static List<Region> getRegionsAt(Block block) {
		RegionSpace space = last.getRegions(block.getWorld().getUID());
		return space.getRegions(block.getX(), block.getZ());
	}

	public static List<Region> getRegionsAt(Location location) {
		RegionSpace space = last.getRegions(location.getWorld().getUID());
		return space.getRegions(location.getBlockX(), location.getBlockZ());
	}

	@Inject
	@Database({"guard", "regions"})
	private Store<RegionDocument> regions;

	@Inject
	private Log logger;

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

		for (PersistentSpatialHashRegions regions : this.worlds.values()) {
			Region existing = regions.getByName(name);

			if (existing != null) {
				return existing;
			}
		}
		return null;
	}

	private void loadRegions() {
		regions.getAllDocuments()
			.forEach(this::loadRegion);
	}

	private void loadRegion(RegionDocument data) {
		logger.info("Loading region: " + data.getIdentifier());
		Region region = RegionDocumentHelper.regionFromData(data);

		PersistentSpatialHashRegions space = getRegions(data.getWorld());
		space.addFromFile(region);
	}

	private void clearRegions() {
		worlds.clear();
	}

}