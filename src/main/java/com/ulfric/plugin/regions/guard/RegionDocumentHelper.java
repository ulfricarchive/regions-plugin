package com.ulfric.plugin.regions.guard;

import java.util.Map;

import com.google.gson.JsonElement;
import com.ulfric.commons.json.JsonHelper;
import com.ulfric.commons.spatial.Region;
import com.ulfric.commons.spatial.flag.Flags;
import com.ulfric.commons.spatial.shape.Point2d;
import com.ulfric.commons.spatial.shape.Shape;
import com.ulfric.commons.spatial.shape.Square;

public class RegionDocumentHelper {

	public static Region regionFromData(RegionDocument data) {
		return Region.builder()
				.setName(data.getIdentifier().toString())
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

	private static Square boundsFromData(RegionDocument data) {
		SquareDocument shape = data.getBounds();

		Point2d one = null;
		Point2d two = null;

		if (shape != null) {
			one = shape.getPositionOne();
			two = shape.getPositionTwo();
		}

		if (one == null) {
			one = Point2d.ZERO;
		}

		if (two == null) {
			two = Point2d.ZERO;
		}

		return new Square(one, two);
	}

	public static void regionIntoData(Region region, RegionDocument document) {
		document.setIdentifier(region.getName().toLowerCase());
		document.setWeight(region.getWeight() == 0 ? null : region.getWeight());
		document.setFlags(serializeFlags(region));
		document.setBounds(serializeBounds(region));
	}

	private static Map<String, JsonElement> serializeFlags(Region region) {
		return JsonHelper.read(region.getFlags().toJson(), Map.class); // TODO does type need to be specified?
	}

	private static SquareDocument serializeBounds(Region region) {
		Shape shape = region.getBounds();
		SquareDocument data = new SquareDocument();
		data.setPositionOne(shape.getMin());
		data.setPositionTwo(shape.getMax());
		return data;
	}

	private RegionDocumentHelper() {
	}

}
