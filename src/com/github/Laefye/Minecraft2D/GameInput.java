package com.github.Laefye.Minecraft2D;

import java.awt.event.*;

public class GameInput implements KeyListener, MouseListener, MouseMotionListener {
    public double horizontal = 0;
    public double vertical = 0;
    public boolean isLeftMouseDown = false;
    public boolean isRightMouseDown = false;
    public double mouseX = 0;
    public double mouseY = 0;
    public int inventory = 1;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'd') {
            horizontal = 1;
        } else if (e.getKeyChar() == 'a') {
            horizontal = -1;
        } else if (e.getKeyChar() == 'w') {
            vertical = 1;
        } else if (e.getKeyChar() == 's') {
            vertical = -1;
        } else if (e.getKeyChar() == '1') {
            inventory = 1;
        } else if (e.getKeyChar() == '2') {
            inventory = 2;
        } else if (e.getKeyChar() == '3') {
            inventory = 3;
        } else if (e.getKeyChar() == '4') {
            inventory = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'd') {
            horizontal = 0;
        } else if (e.getKeyChar() == 'a') {
            horizontal = 0;
        } else if (e.getKeyChar() == 'w') {
            vertical = 0;
        } else if (e.getKeyChar() == 's') {
            vertical = 0;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == 1) {
            isLeftMouseDown = true;
        } else if (e.getButton() == 3) {
            isRightMouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == 1) {
            isLeftMouseDown = false;
        } else if (e.getButton() == 3) {
            isRightMouseDown = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
}
