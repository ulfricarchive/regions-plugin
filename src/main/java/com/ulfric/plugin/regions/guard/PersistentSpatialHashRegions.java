package com.ulfric.plugin.regions.guard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.SpatialHashRegions;
import com.ulfric.dragoon.acrodb.Store;

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

			RegionDocument document = database.get(name);
			if (document == null) {
				document = new RegionDocument();
			}

			saveDataIntoRegion(region, document);

			database.persist(document);
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
			database.deleteDocument(name);
		}
	}

}