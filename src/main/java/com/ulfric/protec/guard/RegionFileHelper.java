package com.ulfric.protec.guard;

import com.google.gson.JsonElement;

import com.ulfric.commons.json.JsonHelper;
import com.ulfric.estate.Flags;
import com.ulfric.estate.Region;
import com.ulfric.estate.shape.Empty;
import com.ulfric.estate.shape.Shape;
import com.ulfric.estate.shape.Shapes;

import java.util.Map;
import java.util.Objects;

public class RegionFileHelper {

	public static Region regionFromData(RegionData data) {
		return Region.builder()
				.setName(data.getName())
				.setWeight(data.getWeight() == null ? 0 : data.getWeight())
				.setFlags(flagsFromData(data))
				.setBounds(boundsFromData(data))
				.build();
	}

	private static Flags flagsFromData(RegionData data) {
		Map<String, JsonElement> flags = data.getFlags();
		if (flags == null) {
			return Flags.EMPTY;
		}

		return Flags.create(JsonHelper.toJsonObject(flags).getAsJsonObject());
	}

	private static Shape boundsFromData(RegionData data) {
		ShapeData shape = data.getBounds();

		if (shape == null) {
			return Empty.INSTANCE;
		}

		// TODO better error handling
		Class<? extends Shape> type = Shapes.getShape(shape.getType());
		Objects.requireNonNull(type, "type");
		return JsonHelper.read(shape.getData(), type);
	}

	public static void regionIntoData(Region region, RegionData data) {
		data.setName(region.getName());
		data.setWeight(region.getWeight() == 0 ? null : region.getWeight());
		data.setFlags(serializeFlags(region));
		data.setBounds(serializeBounds(region));
	}

	private static Map<String, JsonElement> serializeFlags(Region region) {
		return JsonHelper.read(region.getFlags().toJson(), Map.class); // TODO does type need to be specified?
	}

	private static ShapeData serializeBounds(Region region) {
		Shape shape = region.getBounds();
		ShapeData data = new ShapeData();
		data.setType(shape.getName());
		data.setData(shape.toJson());
		return data;
	}

	private RegionFileHelper() {
	}

}
