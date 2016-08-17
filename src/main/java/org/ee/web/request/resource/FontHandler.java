package org.ee.web.request.resource;

import java.io.File;
import java.util.Map;

import org.ee.collection.MapBuilder;
import org.ee.web.request.Request;

public class FontHandler extends ResourceHandler {
	private static final Map<String, String> TYPES = new MapBuilder<String, String>()
			.put("svg", "application/svg+xml")
			.put("eot", "application/vnd.ms-fontobject")
			.put("ttf", "application/x-font-ttf")
			.put("woff", "application/font-woff")
			.put("woff2", "font/woff2")
			.build(true);

	public FontHandler() {
		this("fonts/");
	}

	public FontHandler(String directory) {
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
