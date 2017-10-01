package com.ulfric.plugin.regions.guard;

import com.google.gson.JsonElement;

import com.ulfric.commons.value.Bean;

import java.util.Map;
import java.util.UUID;

public class RegionData extends Bean {

	private UUID world;
	private String name;
	private Integer weight;
	private Map<String, JsonElement> flags;
	private ShapeData bounds;

	public UUID getWorld() {
		return world;
	}

	public void setWorld(UUID world) {
		this.world = world;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
