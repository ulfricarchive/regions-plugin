package com.ulfric.protec.selection;

import com.ulfric.dragoon.application.Container;
import com.ulfric.protec.selection.command.SelectionClearCommand;
import com.ulfric.protec.selection.command.SelectionCommand;

public class SelectionContainer extends Container {

	public SelectionContainer() {
		install(SelectionCommand.class);
		install(SelectionClearCommand.class);

		install(SelectionListener.class);
	}

}
