package com.ulfric.plugin.regions.collection;

import com.ulfric.spatialregions.Region;
import com.ulfric.spatialregions.RegionSpace;

import java.util.Collections;
import java.util.List;

public class SpatialHashRegions implements RegionSpace {

	private final SpatialHash<Region> regions = new SpatialHash<>(128);

	@Override
	public List<Region> getRegions(int x, int z) {
		List<Region> regions = this.regions.get(x, z);
		if (regions.size() > 1) {
			Collections.sort(regions);
		}
		return regions;
	}

	@Override
	public void add(Region region) {
		regions.put(region.getBounds(), region);
	}

	@Override
	public void remove(Region region) {
		String name = region.getName();
		regions.remove(existing -> existing.getName().equals(name));
	}

}