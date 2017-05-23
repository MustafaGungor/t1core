package com.mebitech.config.commands;

import com.mebitech.core.api.plugin.ICommand;

public abstract class BaseCommand implements ICommand {

	private static final String NAME = "MEBITECH-CONFIG";
	private static final String VERSION = "1.0.0";

	@Override
	public String getPluginName() {
		return NAME;
	}

	@Override
	public String getPluginVersion() {
		return VERSION;
	}

}
