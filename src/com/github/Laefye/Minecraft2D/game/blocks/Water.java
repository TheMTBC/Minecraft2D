package com.github.Laefye.Minecraft2D.game.blocks;

import com.github.Laefye.Minecraft2D.Main;
import com.github.Laefye.Minecraft2D.game.Block;
import com.github.Laefye.Minecraft2D.game.BlockState;
import com.github.Laefye.Minecraft2D.game.Chunk;
import com.github.Laefye.Minecraft2D.game.Register;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.ImageResource;
import com.github.Laefye.Minecraft2D.types.Location;

import java.awt.*;

public class Water extends Block {
    @Override
    public BlockState getDefaultBlockState(Location location, Chunk chunk) {
        var b = super.getDefaultBlockState(location, chunk);
        b.getProperties().put("ticks", 0);
        b.getProperties().put("level", 16);
        return b;
    }

    @Override
    public void draw(Graphics g, int x, int y, BlockState blockState) {
        var t = (ImageResource) Main.REGISTER.getResource(new Identifier(identifier.getNamespace(), "textures/blocks/" + identifier.getPath()));
        var height = ((int) blockState.getProperties().get("level") * 1.0) / 16 * 64;
        var intHeight = (int) height;
        g.drawImage(t.getImage(), x, y - intHeight + 64, 64,  intHeight, null);
    }

    @Override
    public void tick(BlockState blockState) {
        super.tick(blockState);
        var ticks = (int) blockState.getProperties().get("ticks");
        ticks++;
        blockState.getProperties().put("ticks", ticks);
        if (ticks % 10 == 0) {
            var location = blockState.getLocation();
            var lbottom = new Location(location.getBlockX(), location.getBlockY() - 1);
            var lbottomstate = blockState.getChunk().getWorld().getBlock(lbottom).getParent();
            if (lbottomstate == Main.REGISTER.getBlock(new Identifier("minecraft2d", "air")) ||
                lbottomstate == Main.REGISTER.getBlock(new Identifier("minecraft2d", "water"))) {
                blockState.getChunk().getWorld().setBlock(lbottom, Main.REGISTER.getBlock(new Identifier("minecraft2d", "water")));
                return;
            }
            if ((int) blockState.getProperties().get("level") > 1) {
                var lleft = new Location(location.getBlockX() - 1, location.getBlockY());
                if (blockState.getChunk().getWorld().getBlock(lleft).getParent() == Main.REGISTER.getBlock(new Identifier("minecraft2d", "air"))) {
                    var b = blockState.getChunk().getWorld().setBlock(lleft, Main.REGISTER.getBlock(new Identifier("minecraft2d", "water")));
                    b.getProperties().put("level", (int) blockState.getProperties().get("level") - 1);
                }
                var lright = new Location(location.getBlockX() + 1, location.getBlockY());
                if (blockState.getChunk().getWorld().getBlock(lright).getParent() == Main.REGISTER.getBlock(new Identifier("minecraft2d", "air"))) {
                    var b = blockState.getChunk().getWorld().setBlock(lright, Main.REGISTER.getBlock(new Identifier("minecraft2d", "water")));
                    b.getProperties().put("level", (int) blockState.getProperties().get("level") - 1);
                }
            }
        }
    }
}
