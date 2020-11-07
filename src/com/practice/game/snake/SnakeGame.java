package com.practice.game.snake;


public class SnakeGame {

    public static void main(String[] args) {
        SnakeArena snakeArena = new SnakeArena(1000, 600);
        new Thread(snakeArena).start();
    }

}
