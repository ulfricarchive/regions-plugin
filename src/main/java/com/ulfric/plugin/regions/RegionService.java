package com.ulfric.plugin.regions;

import com.ulfric.plugin.services.Service;
import com.ulfric.spatialregions.RegionSpace;

import java.util.UUID;

public interface RegionService<T extends RegionService<T>> extends Service<T> {

	RegionSpace getRegions(UUID world);

}