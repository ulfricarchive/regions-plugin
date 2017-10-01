package com.ulfric.plugin.regions.guard;

import com.ulfric.plugin.regions.collection.SpatialHashRegions;
import com.ulfric.spatialregions.Region;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

final class PersistentSpatialHashRegions extends SpatialHashRegions { // TODO cleanup class

	private final UUID world;
	private final RegionsFile file;
	private final Map<String, Region> byName = new HashMap<>();

	public PersistentSpatialHashRegions(UUID world, RegionsFile file) {
		this.world = world;
		this.file = file;
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

		String name = region.getName();
		synchronized (file) {
			byName.put(name, region);

			List<RegionData> allData = new ArrayList<>(file.getRegions());
			RegionData base = null;

			for (RegionData data : allData) {
				if (data.getName().equalsIgnoreCase(name)) {
					base = data;
					break;
				}
			}

			if (base == null) {
				base = new RegionData();
				allData.add(base);
			}

			saveDataIntoRegion(region, base);

			file.setRegions(allData);
		}
	}

	private void saveDataIntoRegion(Region region, RegionData data) {
		RegionFileHelper.regionIntoData(region, data);
		data.setWorld(world);
	}

	@Override
	public void remove(Region region) {
		super.remove(region);

		String name = region.getName();
		synchronized (file) {
			byName.remove(name);
			List<RegionData> allData = new ArrayList<>(file.getRegions());

			Iterator<RegionData> iterator = allData.iterator();
			while (iterator.hasNext()) {
				if (iterator.next().getName().equalsIgnoreCase(name)) {
					iterator.remove();
					break;
				}
			}
		}
	}

}