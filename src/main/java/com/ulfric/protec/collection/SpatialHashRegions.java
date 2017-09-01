package com.ulfric.protec.collection;

import com.ulfric.estate.Region;
import com.ulfric.estate.RegionSpace;

import java.util.Collections;
import java.util.List;

public class SpatialHashRegions implements RegionSpace {

	private final SpatialHash<Region> regions = new SpatialHash<>(128);

	@Override
	public List<Region> getRegions(int x, int y, int z) {
		List<Region> regions = this.regions.get(x, y, z);
		Collections.sort(regions);
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