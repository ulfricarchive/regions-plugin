package com.ulfric.plugin.regions.guard;

import com.google.gson.JsonElement;

import com.ulfric.commons.value.Bean;

public class ShapeData extends Bean {

	private String type;
	private JsonElement data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonElement getData() {
		return data;
	}

	public void setData(JsonElement data) {
		this.data = data;
	}

}
