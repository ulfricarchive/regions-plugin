package com.ulfric.plugin.regions.guard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ulfric.dragoon.rethink.Location;
import com.ulfric.dragoon.rethink.Store;
import com.ulfric.plugin.regions.collection.SpatialHashRegions;
import com.ulfric.spatialregions.Region;

final class PersistentSpatialHashRegions extends SpatialHashRegions { // TODO cleanup class

	private final UUID world;
	private final Object mutex = new Object();
	private final Store<RegionDocument> database;
	private final Map<String, Region> byName = new HashMap<>();

	public PersistentSpatialHashRegions(UUID world, Store<RegionDocument> database) {
		this.world = world;
		this.database = database;
	}

	public void addFromFile(Region region) {
		super.add(region);
	}

	public Region getByName(String name) {
		return byName.get(name);
	}

	@Override
	public void add(Region region) {
		super.add(region);

		String name = region.getName().toLowerCase();
		synchronized (mutex) {
			byName.put(name, region);

			Location location = Location.key(name);
			RegionDocument document = database.get(location).join().get();
			if (document == null) {
				document = new RegionDocument();
			}

			saveDataIntoRegion(region, document);

			database.insert(document).join();
		}
	}

	private void saveDataIntoRegion(Region region, RegionDocument data) {
		RegionDocumentHelper.regionIntoData(region, data);
		data.setWorld(world);
	}

	@Override
	public void remove(Region region) {
		super.remove(region);

		String name = region.getName().toLowerCase();
		synchronized (mutex) {
			byName.remove(name);
			database.delete(Location.key(name));
		}
	}

}