package com.ulfric.protec;

import com.ulfric.estate.RegionSpace;
import com.ulfric.servix.Service;

import java.util.UUID;

public interface RegionService<T extends RegionService<T>> extends Service<T> {

	RegionSpace getRegions(UUID world);

}