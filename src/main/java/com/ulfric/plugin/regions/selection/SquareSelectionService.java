package com.ulfric.plugin.regions.selection;

import java.util.Map;
import java.util.UUID;

import com.ulfric.commons.collection.MapHelper;

public class SquareSelectionService implements SelectionService {

	private final Map<UUID, Selection> selections = MapHelper.newConcurrentMap(1);

	@Override
	public Class<SelectionService> getService() {
		return SelectionService.class;
	}

	@Override
	public Selection getSelection(UUID uniqueId) {
		return selections.computeIfAbsent(uniqueId, ignore -> new SquareSelection());
	}

}
