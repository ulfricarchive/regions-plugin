package com.ulfric.plugin.regions.guard;

import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonElement;
import com.ulfric.dragoon.rethink.Document;

public class RegionDocument extends Document {

	private UUID world;
	private Integer weight;
	private Map<String, JsonElement> flags;
	private ShapeData bounds;

	public UUID getWorld() {
		return world;
	}

	public void setWorld(UUID world) {
		this.world = world;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Map<String, JsonElement> getFlags() {
		return flags;
	}

	public void setFlags(Map<String, JsonElement> flags) {
		this.flags = flags;
	}

	public ShapeData getBounds() {
		return bounds;
	}

	public void setBounds(ShapeData bounds) {
		this.bounds = bounds;
	}

}
