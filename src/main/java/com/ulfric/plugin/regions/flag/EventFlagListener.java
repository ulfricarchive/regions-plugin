package com.ulfric.plugin.regions.flag;

import java.util.List;

import org.bukkit.event.Event;

import com.ulfric.commons.spatial.Region;

public abstract class EventFlagListener<T extends Event> extends FlagListener {

	protected abstract boolean isDenied(T event);

	protected abstract boolean isDenied(List<Region> regions);

}
