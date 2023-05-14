package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.Location;
import com.github.Laefye.Minecraft2D.Main;


public class Chunk {
    private BlockState[] blockStates;
    public static int WIDTH = 32;
    public static int HEIGHT = 256;
    private Location location;
    private World world;

    public Chunk(Location location, World world) {
        this.world = world;
        this.location = location;
        blockStates = new BlockState[WIDTH * HEIGHT];
        generate();
    }

    public Location getLocation() {
        return location;
    }

    public World getWorld() {
        return world;
    }

    private void generate() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (y < 128 & y > 0) {
                    setBlock(new Location(x + this.location.getBlockX() * WIDTH, y), Main.REGISTER.getBlock(new Identifier("minecraft2d", "stone")));
                } else if (y == 128) {
                    setBlock(new Location(x + this.location.getBlockX() * WIDTH, y), Main.REGISTER.getBlock(new Identifier("minecraft2d", "grass")));
                } else if (y == 0) {
                    setBlock(new Location(x + this.location.getBlockX() * WIDTH, y), Main.REGISTER.getBlock(new Identifier("minecraft2d", "bedrock")));
                } else {
                    setBlock(new Location(x + this.location.getBlockX() * WIDTH, y), Main.REGISTER.getBlock(new Identifier("minecraft2d", "air")));
                }
            }
        }
    }

    private int getBlockIndex(Location location) {
        return location.getBlockY() * WIDTH + location.getBlockX() % WIDTH;
    }

    private boolean checkBlockOutChunk(Location location) {
        return ( location.getBlockY() < 0 || location.getBlockY() > (HEIGHT - 1) ) || (location.getBlockX() < this.location.getBlockX() * WIDTH || location.getBlockX() >= (this.location.getBlockX() + 1) * WIDTH);
    }

    public BlockState setBlock(Location location, Block block) {
        if (checkBlockOutChunk(location)) {
            return null;
        }
        var l = new Location(location.getBlockX(), location.getY());
        var bs = block.getDefaultBlockState(l, this);
        blockStates[getBlockIndex(location)] = bs;
        return bs;
    }

    public BlockState getBlock(Location location) {
        return blockStates[getBlockIndex(location)];
    }

    public interface BlockCountEvent {
        void run(BlockState blockState);
    }

    public void countBlock(BlockCountEvent event) {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                event.run(getBlock(new Location(x * 1.0, y * 1.0)));
            }
        }
    }
}
