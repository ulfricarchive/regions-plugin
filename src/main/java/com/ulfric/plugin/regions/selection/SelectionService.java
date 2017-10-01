package com.ulfric.plugin.regions.selection;

import com.ulfric.plugin.services.Service;

import java.util.UUID;

public interface SelectionService extends Service<SelectionService> { // TODO implement this

	public static SelectionService get() {
		return Service.get(SelectionService.class);
	}

	public Selection getSelection(UUID uniqueId);

}