package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.types.Location;

import java.util.HashMap;

public class BlockState {
    private Location location;
    private Block parent;
    private Chunk chunk;
    private HashMap<String, Object> properties;

    public BlockState(Location location, Block parent, Chunk chunk) {
        this.location = location;
        this.parent = parent;
        this.chunk = chunk;
        this.properties = new HashMap<>();
    }

    public Chunk getChunk() {
        return chunk;
    }

    public Location getLocation() {
        return location;
    }

    public Block getParent() {
        return parent;
    }

    public HashMap<String, Object> getProperties() {
        return properties;
    }
}
