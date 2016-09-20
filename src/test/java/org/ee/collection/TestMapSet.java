package org.ee.collection;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class TestMapSet {
	@Test
	public void test() {
		Object object = "123";
		Set<Object> set = new MapSet<>();
		Assert.assertFalse(set.contains(null));
		Assert.assertFalse(set.remove(null));
		set.add(object);
		Assert.assertTrue(set.contains(object));
		Assert.assertTrue(set.remove(object));
		Assert.assertFalse(set.remove(object));
		Assert.assertEquals(0, set.size());
		set.add(object);
		Assert.assertEquals(1, set.size());
		set.add(object);
		Assert.assertEquals(1, set.size());
	}
}
