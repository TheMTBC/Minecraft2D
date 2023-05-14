package com.github.Laefye.Minecraft2D.game.blocks;

import com.github.Laefye.Minecraft2D.Main;
import com.github.Laefye.Minecraft2D.game.Block;
import com.github.Laefye.Minecraft2D.game.BlockState;
import com.github.Laefye.Minecraft2D.game.Chunk;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.Location;

public class Sand extends Block {
    @Override
    public BlockState getDefaultBlockState(Location location, Chunk chunk) {
        var b = super.getDefaultBlockState(location, chunk);
        b.getProperties().put("ticks", 0);
        return b;
    }

    @Override
    public void tick(BlockState blockState) {
        super.tick(blockState);
        var ticks = (int) blockState.getProperties().get("ticks");
        ticks++;
        blockState.getProperties().put("ticks", ticks);
        if (ticks % 10 == 0) {
            var w = blockState.getChunk().getWorld();
            var ll = blockState.getLocation().add(0, -1);
            if (w.getBlock(ll).getParent() == Main.REGISTER.getBlock(new Identifier("minecraft2d", "air"))) {
                w.setBlock(ll, Main.REGISTER.getBlock(new Identifier("minecraft2d", "sand")));
                w.setBlock(blockState.getLocation(), Main.REGISTER.getBlock(new Identifier("minecraft2d", "air")));
            }
        }
    }
}
