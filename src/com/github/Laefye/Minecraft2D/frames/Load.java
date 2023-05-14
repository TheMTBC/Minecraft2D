package com.github.Laefye.Minecraft2D.frames;

import com.github.Laefye.Minecraft2D.game.blocks.*;
import com.github.Laefye.Minecraft2D.game.entities.Player;
import com.github.Laefye.Minecraft2D.types.Identifier;
import com.github.Laefye.Minecraft2D.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Load extends JPanel {
    public Load() {
        super();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.cyan);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);
        g.setFont(Font.getFont(Font.SANS_SERIF));
        g.drawString("Loading", 10, 20);
    }

    public void load(Runnable onLoad) throws IOException {
        Main.REGISTER.register(new Identifier("minecraft2d", "air"), new Air());
        Main.REGISTER.register(new Identifier("minecraft2d", "stone"), new Stone());
        Main.REGISTER.register(new Identifier("minecraft2d", "grass"), new Grass());
        Main.REGISTER.register(new Identifier("minecraft2d", "bedrock"), new Bedrock());
        Main.REGISTER.register(new Identifier("minecraft2d", "sand"), new Sand());
        Main.REGISTER.register(new Identifier("minecraft2d", "water"), new Water());
        Main.REGISTER.register(new Identifier("minecraft2d", "player"), new Player());
        onLoad.run();
    }
}
