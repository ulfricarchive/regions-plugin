package com.ulfric.protec.flag;

import com.ulfric.estate.Flag;
import com.ulfric.estate.Flags;

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
