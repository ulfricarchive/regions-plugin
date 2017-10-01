package com.ulfric.plugin.regions.flag;

import com.ulfric.spatialregions.Flag;
import com.ulfric.spatialregions.Flags;

public interface Place extends Flag {

	boolean IS_PLACE_ALLOWED_DEFAULT = false;

	static boolean isPlaceAllowed(Flags flags) {
		if (flags instanceof Place) {
			return ((Place) flags).isPlaceAllowed();
		}

		return IS_PLACE_ALLOWED_DEFAULT;
	}

	boolean isPlaceAllowed();

}
