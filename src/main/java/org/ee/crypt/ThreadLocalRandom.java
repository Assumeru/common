package org.ee.crypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.ee.config.ConfigurationException;

/**
 * A reusable class for creating {@link SecureRandom} instances per thread.
 */
public class ThreadLocalRandom extends ThreadLocal<SecureRandom> {
	@Override
	protected SecureRandom initialValue() {
		try {
			return SecureRandom.getInstanceStrong();
		} catch (NoSuchAlgorithmException e) {
			throw new ConfigurationException("Failed to create a secure random instance", e);
		}
	}
}
