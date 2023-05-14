package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.*;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.ImageResource;
import com.github.Laefye.Minecraft2D.types.Location;
import com.github.Laefye.Minecraft2D.types.Resource;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Block {
    protected Identifier identifier;

    public void register(Identifier identifier) {
        this.identifier = identifier;
    }

    public Map<Identifier, Resource> getResources() throws IOException {
        var resources = new HashMap<Identifier, Resource>();
        resources.put(
                new Identifier(identifier.getNamespace(), "textures/blocks/" + identifier.getPath()),
                ImageResource.load("/assets/" + identifier.getNamespace() + "/textures/blocks/" + identifier.getPath() + ".png")
        );
        return resources;
    }

    public BlockState getDefaultBlockState(Location location, Chunk chunk) {
        return new BlockState(location, this, chunk);
    }

    public void draw(Graphics g, int x, int y, BlockState blockState) {
        var t = (ImageResource) Main.REGISTER.getResource(new Identifier(identifier.getNamespace(), "textures/blocks/" + identifier.getPath()));
        g.drawImage(t.getImage(), x, y, null);
    }

    public void tick(BlockState blockState) {

    }
}
