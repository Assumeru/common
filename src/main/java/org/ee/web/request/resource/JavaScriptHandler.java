package org.ee.web.request.resource;

import java.io.File;

import org.ee.web.request.Request;
import org.ee.web.request.resource.ResourceHandler;

public class JavaScriptHandler extends ResourceHandler {
	public JavaScriptHandler() {
		this("js/");
	}

	public JavaScriptHandler(String directory) {
		super(directory);
	}

	@Override
	protected String getType(Request request, File file) {
		return "application/javascript";
	}
}
