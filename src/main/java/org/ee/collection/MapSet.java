package org.ee.collection;

import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Like HashSet, but with a get method.
 */
public class MapSet<E> extends AbstractSet<E> implements Serializable {
	private static final long serialVersionUID = 5566760858232768414L;
	private final Map<E, E> map;

	public MapSet() {
		map = new HashMap<>();
	}

	public MapSet(Collection<? extends E> c) {
		map = new HashMap<>(Math.max((int) (c.size() / .75f) + 1, 16));
		addAll(c);
	}

	@Override
	public Iterator<E> iterator() {
		return map.keySet().iterator();
	}

	/**
	 * @see Map#get(Object)
	 */
	public E get(Object key) {
		return map.get(key);
	}

	@Override
	public boolean add(E e) {
		return map.put(e, e) == null;
	}

	@Override
	public boolean remove(Object o) {
		if(contains(o)) {
			E value = map.get(o);
			return map.remove(o) == value;
		}
		return false;
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return map.containsKey(o);
	}
}
