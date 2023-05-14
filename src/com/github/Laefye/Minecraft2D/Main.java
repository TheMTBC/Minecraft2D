package com.github.Laefye.Minecraft2D;

import com.github.Laefye.Minecraft2D.game.Register;

public class Main {
    public static Window WINDOW;
    public static final Register REGISTER = new Register();

    public static void main(String[] args) {
        WINDOW = new Window();
        WINDOW.run();
    }
}
