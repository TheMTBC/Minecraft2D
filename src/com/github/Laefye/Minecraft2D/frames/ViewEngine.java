package com.github.Laefye.Minecraft2D.frames;

import com.github.Laefye.Minecraft2D.game.Camera;
import com.github.Laefye.Minecraft2D.types.Location;

public class ViewEngine {
    private Game game;
    private Camera camera;
    private double BLOCK_SIZE = 64;

    public ViewEngine(Game game, Location cameraLocation) {
        this.game = game;
        this.camera = new Camera(cameraLocation, this);
    }

    public Camera getCamera() {
        return camera;
    }

    public Game getGame() {
        return game;
    }

    public Location translateGameToScreen(Location x) {
        return new Location(
                (x.getX() - camera.getOffset().getX()) * BLOCK_SIZE + game.getWidth() / 2.0,
                -(x.getY() - 255 - camera.getOffset().getY()) * BLOCK_SIZE + game.getHeight() / 2.0
        );
    }

    public Location translateScreenToGame(Location x) {
        return new Location(
                (x.getX() - game.getWidth() / 2.0) / BLOCK_SIZE + camera.getOffset().getX(),
                -(x.getY() - game.getHeight() / 2.0) / BLOCK_SIZE + 255 + camera.getOffset().getY() + 1
        );
    }
}
