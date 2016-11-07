package com.schemast.util;

public abstract class Named {
	private final Name name;

    protected Named(String name) {
	    this.name = new Name(name);
    }

	public String getName() {
		return name.get();
	}
}
