package com.ulfric.plugin.regions.guard;

import com.google.gson.JsonElement;

import com.ulfric.commons.json.JsonHelper;
import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.flag.Flags;
import com.ulfric.dragoon.rethink.Location;
import com.ulfric.spatialregions.shape.Empty;
import com.ulfric.spatialregions.shape.Shape;
import com.ulfric.spatialregions.shape.Shapes;

import java.util.Map;
import java.util.Objects;

public class RegionDocumentHelper {

	public static Region regionFromData(RegionDocument data) {
		return Region.builder()
				.setName(data.getLocation().getKey())
				.setWeight(data.getWeight() == null ? 0 : data.getWeight())
				.setFlags(flagsFromData(data))
				.setBounds(boundsFromData(data))
				.build();
	}

	private static Flags flagsFromData(RegionDocument data) {
		Map<String, JsonElement> flags = data.getFlags();
		if (flags == null) {
			return Flags.EMPTY;
		}

		return Flags.create(JsonHelper.toJsonObject(flags).getAsJsonObject());
	}

	private static Shape boundsFromData(RegionDocument data) {
		ShapeData shape = data.getBounds();

		if (shape == null) {
			return Empty.INSTANCE;
		}

		// TODO better error handling
		Class<? extends Shape> type = Shapes.getShape(shape.getType());
		Objects.requireNonNull(type, "type");
		return JsonHelper.read(shape.getData(), type);
	}

	public static void regionIntoData(Region region, RegionDocument document) {
		document.setLocation(Location.key(region.getName().toLowerCase()));
		document.setWeight(region.getWeight() == 0 ? null : region.getWeight());
		document.setFlags(serializeFlags(region));
		document.setBounds(serializeBounds(region));
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

	private RegionDocumentHelper() {
	}

}
