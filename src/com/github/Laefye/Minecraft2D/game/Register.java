package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.Main;
import com.github.Laefye.Minecraft2D.types.Resource;

import java.io.IOException;
import java.util.HashMap;

public class Register {
    private final HashMap<Identifier, Block> blocks = new HashMap<>();
    private final HashMap<Identifier, Entity> entities = new HashMap<>();
    private final HashMap<Identifier, Resource> resources = new HashMap<>();

    public void register(Identifier identifier, Block block) throws IOException {
        block.register(identifier);
        var r = block.getResources();
        for (var i : r.keySet()) {
            Main.REGISTER.register(
                    i,
                    r.get(i)
            );
        }
        blocks.put(identifier, block);
    }

    public void register(Identifier identifier, Entity entity) throws IOException {
        entity.register(identifier);
        var r = entity.getResources();
        for (var i : r.keySet()) {
            Main.REGISTER.register(
                    i,
                    r.get(i)
            );
        }
        entities.put(identifier, entity);
    }

    public void register(Identifier identifier, Resource resource) {
        resources.put(identifier, resource);
    }

    public Resource getResource(Identifier identifier) {
        if (!resources.containsKey(identifier)) {
            return null;
        }
        return resources.get(identifier);
    }

    public Block getBlock(Identifier identifier) {
        if (!blocks.containsKey(identifier)) {
            return null;
        }
        return blocks.get(identifier);
    }

    public Entity getEntity(Identifier identifier) {
        if (!entities.containsKey(identifier)) {
            return null;
        }
        return entities.get(identifier);
    }
}
