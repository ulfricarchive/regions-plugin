package com.ulfric.plugin.regions.guard;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.SpatialHashRegions;
import com.ulfric.dragoon.acrodb.Store;

final class PersistentSpatialHashRegions extends SpatialHashRegions { // TODO cleanup class

	private final UUID world;
	private final Store<RegionDocument> database;
	private final Map<String, Region> byName = new HashMap<>();

	public PersistentSpatialHashRegions(UUID world, Store<RegionDocument> database) {
		this.world = world;
		this.database = database;
	}

	public void addFromFile(Region region) {
		register(region);
	}

	public Region getByName(String name) {
		return byName.get(name.toLowerCase());
	}

	@Override
	public void add(Region region) {
		register(region);

		RegionDocument document = database.get(region.getName().toLowerCase());
		if (document == null) {
			document = new RegionDocument();
		}

		saveDataIntoRegion(region, document);

		database.persist(document);
	}

	private void register(Region region) {
		super.add(region);
		byName.put(region.getName().toLowerCase(), region);
	}

	private void saveDataIntoRegion(Region region, RegionDocument data) {
		RegionDocumentHelper.regionIntoData(region, data);
		data.setWorld(world);
	}

	@Override
	public void remove(Region region) {
		super.remove(region);

		String name = region.getName().toLowerCase();
		byName.remove(name);
		database.deleteDocument(name);
	}

}