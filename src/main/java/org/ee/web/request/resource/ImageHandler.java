package org.ee.web.request.resource;

import java.io.File;
import java.util.Map;

import org.ee.collection.MapBuilder;
import org.ee.web.request.Request;

public class ImageHandler extends ResourceHandler {
	private static final Map<String, String> TYPES = new MapBuilder<String, String>()
			.put("svg", "application/svg+xml")
			.put("png", "image/png")
			.put("jpg", "image/jpeg")
			.putGet("jpeg", "jpg")
			.build(true);

	public ImageHandler() {
		this("img/");
	}

	public ImageHandler(String directory) {
		super(directory);
	}

	@Override
	protected String getType(Request request, File file) {
		String extension = getFileExtension(file);
		String type = TYPES.get(extension);
		if(type != null) {
			return type;
		}
		return "application/octet-stream";
	}
}
