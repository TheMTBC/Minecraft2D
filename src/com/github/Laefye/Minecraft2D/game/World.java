package com.github.Laefye.Minecraft2D.game;

import com.github.Laefye.Minecraft2D.game.entities.Player;
import com.github.Laefye.Minecraft2D.types.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private ArrayList<Chunk> chunks;
    private ArrayList<EntityState> entities;
    private Thread ticks;
    private long lastTick = 0;
    public double tps = 0;

    public World() {
        chunks = new ArrayList<>();
        chunks.add(new Chunk(new Location(0, 0), this));
        entities = new ArrayList<>(0);
        ticks = new Thread(() -> {
            while (!ticks.isInterrupted()) {
                try {
                    lastTick = System.nanoTime();
                    Thread.sleep(1000 / 60);
                    tick();
                    tps = (double) (System.nanoTime() - lastTick) / 1000000000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        ticks.start();
    }

    public void tick() {
        for (var e : entities) {
            e.getParent().tick(e);
        }
        countChunk(chunk -> {
            chunk.countBlock(blockState -> {
                blockState.getParent().tick(blockState);
            });
        });
    }

    public EntityState addEntity(Entity entity) {
        var e = entity.getDefaultEntityState(this);
        entities.add(e);
        entity.spawn(e);
        return e;
    }

    public interface ChunkCountEvent {
        void run(Chunk chunk);
    }

    public void countChunk(ChunkCountEvent event) {
        for (var c : chunks) {
            event.run(c);
        }
    }

    public Chunk getChunk(Location location) {
        for (var c : chunks) {
            if (c.getLocation().equals(location)) {
                return c;
            }
        }
        return null;
    }

    public BlockState getBlock(Location location) {
        var cl = new Location(Math.floor((double) location.getBlockX() / Chunk.WIDTH), 0);
        var c = getChunk(cl);
        return c.getBlock(location);
    }

    public BlockState setBlock(Location location, Block block) {
        var cl = new Location(Math.floor((double) location.getBlockX() / Chunk.WIDTH), 0);
        var c = getChunk(cl);
        return c.setBlock(location, block);
    }

    public Chunk loadChunk(Location location) {
        if (getChunk(location) != null) {
            return getChunk(location);
        }
        var c = new Chunk(location, this);
        chunks.add(c);
        return c;
    }
}
