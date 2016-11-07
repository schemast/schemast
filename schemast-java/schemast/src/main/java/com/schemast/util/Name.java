package com.schemast.util;

import com.schemast.models.InvalidModelException;

import static com.schemast.Constants.NAME;

public class Name {
	private final String name;
	
	public Name(String name) {
		if (name == null || name.isEmpty())
			throw new InvalidModelException(NAME + " is required");
		this.name = name;
	}

	public String get() {
		return name;
	}
}
