package com.schemast.util;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Appender<T extends Named> {
	private final Map<String, T> m = new HashMap<>();

	public void append(T item) {
		if (item == null) {
			throw new NullPointerException("Cannot append a null");
		} else if (m.putIfAbsent(item.getName(), item) != null) {
			throw new IllegalStateException("Duplicate item found: " + item.getName());
		}
	}

	public Collection<T> getItems() {
		return Collections.unmodifiableCollection(m.values());
	}

	public boolean isEmpty() {
		return m.isEmpty();
	}
}
