package com.github.Laefye.Minecraft2D.game.entities;

import com.github.Laefye.Minecraft2D.Main;
import com.github.Laefye.Minecraft2D.game.Chunk;
import com.github.Laefye.Minecraft2D.game.Entity;
import com.github.Laefye.Minecraft2D.game.EntityState;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.Location;

public class Player extends Entity {
    @Override
    public void tick(EntityState entityState) {
        super.tick(entityState);

        var game = Main.WINDOW.game;

        if (Main.WINDOW.gameInput.isLeftMouseDown) {
            var l = Main.WINDOW.game.viewEngine.translateScreenToGame(
                    new Location(Main.WINDOW.gameInput.mouseX, Main.WINDOW.gameInput.mouseY - 33)
            );
            var identifier = new Identifier("", "");
            if (Main.WINDOW.gameInput.inventory == 1) {
                identifier = new Identifier("minecraft2d", "stone");
            } else if (Main.WINDOW.gameInput.inventory == 2) {
                identifier = new Identifier("minecraft2d", "grass");
            } else if (Main.WINDOW.gameInput.inventory == 3) {
                identifier = new Identifier("minecraft2d", "sand");
            } else if (Main.WINDOW.gameInput.inventory == 4) {
                identifier = new Identifier("minecraft2d", "water");
            }
            System.out.println("Place block identifier: " + identifier);
            entityState.getWorld().setBlock(l, Main.REGISTER.getBlock(identifier));
        } else if (Main.WINDOW.gameInput.isRightMouseDown) {
            var l = game.viewEngine.translateScreenToGame(
                    new Location(Main.WINDOW.gameInput.mouseX, Main.WINDOW.gameInput.mouseY - 33)
            );
            entityState.getWorld().setBlock(l, Main.REGISTER.getBlock(new Identifier("minecraft2d", "air")));
        }

        if (game.viewEngine.getCamera().getLocation().getBlockX() % Chunk.WIDTH == 1) {
            entityState.getWorld().loadChunk(
                    new Location(
                            Math.floor(game.viewEngine.getCamera().getLocation().getBlockX() / (double) Chunk.WIDTH),
                            0)
            );
        }
    }

    @Override
    public void spawn(EntityState entityState) {
        super.spawn(entityState);
        Main.WINDOW.onFrame(() -> {
            var game = Main.WINDOW.game;
            if (game == null || game.viewEngine == null) {
                return;
            }

            game.viewEngine.getCamera().setLocation(
                    game.viewEngine.getCamera().getLocation().add(
                            Main.WINDOW.gameInput.horizontal * Main.WINDOW.deltaTime * 10,
                            Main.WINDOW.gameInput.vertical * Main.WINDOW.deltaTime * 10
                    )
            );
        });
    }
}
