package com.practice.game.snake;


import java.util.HashMap;
import java.util.Map;

public class SnakeGame {

    public static void main(String[] args) {

        Map<Integer, Integer> levelAndSpeed = new HashMap<>();
        levelAndSpeed.put(1, 200);
        levelAndSpeed.put(2, 150);
        levelAndSpeed.put(3, 100);
        levelAndSpeed.put(4, 50);

        SnakeArena snakeArena = new SnakeArena(900, 800,
                15, levelAndSpeed);

        snakeArena.setX(300);
        snakeArena.setY(60);
        snakeArena.setRadius(20);
        snakeArena.setIncX(20);
        snakeArena.setIncY(0);
        new Thread(snakeArena).start();
    }
}
