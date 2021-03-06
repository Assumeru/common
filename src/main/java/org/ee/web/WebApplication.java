package org.ee.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ee.collection.ListMap;
import org.ee.config.ConfigurationException;
import org.ee.web.request.RequestHandler;
import org.ee.web.request.servlet.ServletRequest;
import org.ee.web.response.Response;
import org.ee.web.response.ResponseWriter;

public abstract class WebApplication extends HttpServlet {
	private static final long serialVersionUID = 262671820208940735L;
	private final String characterEncoding;

	public WebApplication() {
		String key = getClass().getName() + ".requestEncoding";
		String encoding = System.getProperty(key);
		if(encoding == null) {
			System.err.println("Using UTF-8 character encoding, use jvm argument -D" + key + "=<encoding> to define another value.");
			encoding = "UTF-8";
		}
		characterEncoding = encoding;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		handle(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		handle(request, response);
	}

	private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setCharacterEncoding(request);
		Response res = getRequestHandler().handle(new ServletRequest(request, response));
		response.setStatus(res.getStatus().getCode());
		for(Cookie cookie : res.getCookies()) {
			response.addCookie(cookie);
		}
		setHeaders(res.getHeaders(), response);
		writeOutput(res, response);
	}

	protected void setCharacterEncoding(HttpServletRequest request) {
		try {
			request.setCharacterEncoding(characterEncoding);
		} catch (UnsupportedEncodingException e) {
			throw new ConfigurationException("Failed to set character encoding", e);
		}
	}

	private void setHeaders(ListMap<String, String> headers, HttpServletResponse response) {
		for(Map.Entry<String, List<String>> entry : headers.entrySet()) {
			for(String value : entry.getValue()) {
				response.addHeader(entry.getKey(), value);
			}
		}
	}

	private void writeOutput(Response res, HttpServletResponse response) throws IOException {
		ResponseWriter responseWriter = getResponseWriter();
		try(OutputStream out = response.getOutputStream()) {
			responseWriter.write(res.getOutput(), out);
		}
	}

	protected abstract RequestHandler getRequestHandler();

	protected abstract ResponseWriter getResponseWriter();
}
