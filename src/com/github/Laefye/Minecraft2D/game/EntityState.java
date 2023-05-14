package com.github.Laefye.Minecraft2D.game;

import java.util.UUID;

public class EntityState {
    private UUID uuid;
    private World world;
    private Entity parent;

    public EntityState(Entity parent, World world) {
        uuid = UUID.randomUUID();
        this.world = world;
        this.parent = parent;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Entity getParent() {
        return parent;
    }

    public World getWorld() {
        return world;
    }
}
