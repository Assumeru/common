package org.ee.web.request.resource;

import java.io.File;

import org.ee.web.request.Request;

public class CssHandler extends ResourceHandler {
	public CssHandler() {
		this("css/");
	}

	public CssHandler(String directory) {
		super(directory);
	}

	@Override
	protected String getType(Request request, File file) {
		return "text/css";
	}
}
