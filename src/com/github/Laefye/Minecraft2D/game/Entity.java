package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.ImageResource;
import com.github.Laefye.Minecraft2D.types.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Entity {
    private Identifier identifier;

    public void register(Identifier identifier) {
        this.identifier = identifier;
    }

    public Map<Identifier, Resource> getResources() throws IOException {
        return new HashMap<Identifier, Resource>();
    }

    public EntityState getDefaultEntityState(World world) {
        return new EntityState(this, world);
    }

    public void tick(EntityState entityState) {

    }

    public void spawn(EntityState entityState) {

    }
}
