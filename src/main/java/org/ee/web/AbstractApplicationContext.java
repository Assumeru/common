package org.ee.web;

import java.io.File;
import java.io.FilenameFilter;
import java.security.SecureRandom;

import org.ee.crypt.ThreadLocalRandom;

public class AbstractApplicationContext implements ApplicationContext {
	private final javax.servlet.ServletContext context;
	private final ThreadLocalRandom random;

	public AbstractApplicationContext(javax.servlet.ServletContext context) {
		this.context = context;
		random = new ThreadLocalRandom();
	}

	@Override
	public File[] getFiles(final String path, final String suffix) {
		return getFiles(path, (dir, name) -> name.endsWith(suffix));
	}

	@Override
	public File[] getFiles(String path, FilenameFilter filter) {
		return getFile(path).listFiles(filter);
	}

	@Override
	public File getFile(String path) {
		return new File(context.getRealPath(path));
	}

	@Override
	public String getContextPath() {
		return context.getContextPath();
	}

	@Override
	public javax.servlet.ServletContext getContext() {
		return context;
	}

	@Override
	public SecureRandom getSecureRandom() {
		return random.get();
	}
}
