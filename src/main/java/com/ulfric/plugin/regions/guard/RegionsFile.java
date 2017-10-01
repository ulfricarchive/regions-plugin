package com.ulfric.plugin.regions.guard;

import com.ulfric.commons.value.Bean;

import java.util.List;

public class RegionsFile extends Bean {

	private List<RegionData> regions;

	public List<RegionData> getRegions() {
		return regions;
	}

	public void setRegions(List<RegionData> regions) {
		this.regions = regions;
	}

}
