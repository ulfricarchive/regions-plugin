package com.ulfric.protec;

import com.ulfric.plugin.Plugin;
import com.ulfric.protec.command.RegionCommand;
import com.ulfric.protec.command.RegionCreateCommand;
import com.ulfric.protec.flag.DefaultFlagsContainer;
import com.ulfric.protec.selection.SelectionContainer;
import com.ulfric.protec.selection.command.SelectionClearCommand;
import com.ulfric.protec.selection.command.SelectionCommand;

public class Protec extends Plugin {

	public Protec() {
		install(DefaultFlagsContainer.class);
		install(SelectionContainer.class);

		install(RegionCommand.class);
		install(RegionCreateCommand.class);
		install(SelectionCommand.class);
		install(SelectionClearCommand.class);
	}

}
