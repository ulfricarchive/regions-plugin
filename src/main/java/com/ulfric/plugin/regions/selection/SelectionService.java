package com.ulfric.plugin.regions.selection;

import java.util.UUID;

import com.ulfric.plugin.services.Service;

public interface SelectionService extends Service<SelectionService> { // TODO implement this

	public static SelectionService get() {
		return Service.get(SelectionService.class);
	}

	public Selection getSelection(UUID uniqueId);

}