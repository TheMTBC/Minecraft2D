package com.github.Laefye.Minecraft2D.frames;


import com.github.Laefye.Minecraft2D.game.Camera;
import com.github.Laefye.Minecraft2D.game.Register;
import com.github.Laefye.Minecraft2D.game.World;
import com.github.Laefye.Minecraft2D.game.entities.Player;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.types.Location;
import com.github.Laefye.Minecraft2D.Main;
import com.github.Laefye.Minecraft2D.game.Chunk;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel {
    public Game() {
        super();
        setDoubleBuffered(true);
        viewEngine = new ViewEngine(this, new Location(0, 128));
        world = new World();
        world.addEntity(Main.REGISTER.getEntity(new Identifier("minecraft2d", "player")));
    }

    public ViewEngine viewEngine;
    public World world;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        world.countChunk(chunk -> {
            chunk.countBlock(blockState -> {
                var block = blockState.getParent();
                var l = viewEngine.translateGameToScreen(blockState.getLocation().toBlock());
                blockState.getParent().draw(g, l.getBlockX(), l.getBlockY(), blockState);
            });
        });
        // FPS Meter
        g.drawString(Double.toString(1 / Main.WINDOW.deltaTime), 10, 20);
        g.drawString(Double.toString(1 / world.tps), 10, 50);
    }
}
