package com.github.Laefye.Minecraft2D.types;

import java.util.Objects;

public class Identifier {
    private String namespace;
    private String path;

    public Identifier(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Identifier identifier)) {
            return false;
        }
        return getNamespace().equals(identifier.getNamespace()) && getPath().equals(identifier.getPath());
    }

    @Override
    public String toString() {
        return getNamespace() + ":" + getPath();
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }
}
