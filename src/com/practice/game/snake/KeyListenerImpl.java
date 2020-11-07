package com.practice.game.snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerImpl implements KeyListener {

    private SnakeArena snakeScreen;
    public KeyListenerImpl(SnakeArena sc){
        snakeScreen = sc;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snakeScreen.setIncY(-1 * snakeScreen.getRadius());
                snakeScreen.setIncX(0);
                break;
            case KeyEvent.VK_DOWN:
                snakeScreen.setIncY(snakeScreen.getRadius());
                snakeScreen.setIncX(0);
                break;
            case KeyEvent.VK_LEFT:
                snakeScreen.setIncX(-1 * snakeScreen.getRadius());
                snakeScreen.setIncY(0);
                break;
            case KeyEvent.VK_RIGHT:
                snakeScreen.setIncX(snakeScreen.getRadius());
                snakeScreen.setIncY(0);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
