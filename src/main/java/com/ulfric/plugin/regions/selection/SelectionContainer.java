package com.ulfric.plugin.regions.selection;

import com.ulfric.dragoon.application.Container;
import com.ulfric.plugin.regions.selection.command.SelectionClearCommand;
import com.ulfric.plugin.regions.selection.command.SelectionCommand;

public class SelectionContainer extends Container {

	public SelectionContainer() {
		install(SquareSelectionService.class);
		install(SelectionListener.class);
		install(SelectionCommand.class);
		install(SelectionClearCommand.class);
	}

}
