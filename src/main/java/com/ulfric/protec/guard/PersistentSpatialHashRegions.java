package com.ulfric.protec.guard;

import com.ulfric.estate.Region;
import com.ulfric.protec.collection.SpatialHashRegions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

final class PersistentSpatialHashRegions extends SpatialHashRegions { // TODO cleanup class

	private final UUID world;
	private final RegionsFile file;

	public PersistentSpatialHashRegions(UUID world, RegionsFile file) {
		this.world = world;
		this.file = file;
	}

	public void addFromFile(Region region) {
		super.add(region);
	}

	@Override
	public void add(Region region) {
		super.add(region);

		String name = region.getName();
		synchronized (file) {
			List<RegionData> allData = new ArrayList<>(file.getRegions());
			RegionData base = null;

			for (RegionData data : allData) {
				if (Objects.equals(data.getName(), name)) {
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
			List<RegionData> allData = new ArrayList<>(file.getRegions());

			Iterator<RegionData> iterator = allData.iterator();
			while (iterator.hasNext()) {
				if (Objects.equals(iterator.next().getName(), name)) {
					iterator.remove();
					break;
				}
			}
		}
	}

}