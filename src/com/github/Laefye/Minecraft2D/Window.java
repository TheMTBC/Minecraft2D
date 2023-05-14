package com.github.Laefye.Minecraft2D;

import com.github.Laefye.Minecraft2D.frames.Game;
import com.github.Laefye.Minecraft2D.frames.Load;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {
    public Game game;
    private Thread graphicThread;
    public double deltaTime = 1.0 / 120.0;
    public GameInput gameInput;
    private long lastFrame;
    private ArrayList<Runnable> onFrameEvents;

    public Window() {
        super();
        setTitle("Minecraft 2D");
        setSize(1280, 720);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        gameInput = new GameInput();
        onFrameEvents = new ArrayList<>();
        addKeyListener(gameInput);
        addMouseListener(gameInput);
        addMouseMotionListener(gameInput);
    }

    public void run() {
        graphicThread = new Thread(() -> {
            while (!graphicThread.isInterrupted()) {
                try {
                    lastFrame = System.nanoTime();
                    Thread.sleep(5);
                    update();
                    for (var e : onFrameEvents) {
                        e.run();
                    }
                    deltaTime = (double) (System.nanoTime() - lastFrame) / 1000000000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        graphicThread.start();

        load();
    }

    public void onFrame(Runnable event) {
        onFrameEvents.add(event);
    }

    private void load() {
        var load = (Load) add(new Load());
        revalidate();
        update();
        try {
            load.load(() -> {
                remove(load);
                play();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        repaint();
    }

    private void play() {
        game = (Game) add(new Game());
        revalidate();
    }
}
