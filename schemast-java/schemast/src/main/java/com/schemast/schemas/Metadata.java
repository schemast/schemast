package com.schemast.schemas;

import com.schemast.util.Name;

public class Metadata {

    public static class Builder {
        private String name;
        private int version;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder version(int version) {
            this.version = version;
            return this;
        }

        public Metadata build() {
	        return new Metadata(name, version);
        }
    }

    private final Name name;
    private final Version version;

    private Metadata(String name, int version) {
        this.name = new Name(name);
        this.version = new Version(version);
    }

    public String getName() {
        return name.get();
    }

    public int getVersion() {
        return version.get();
    }
}
