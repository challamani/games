package com.practice.game.snake;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FoodLocation {

    private int x;
    private int y;
    private final int xLimit;
    private final int yLimit;


    public FoodLocation(int xBoundary, int yBoundary) {
        xLimit = xBoundary;
        yLimit = yBoundary;
    }

    public void produceFood() {
        x = ThreadLocalRandom.current()
                .nextInt(100, xLimit - 100);
        y = ThreadLocalRandom.current()
                .nextInt(100, yLimit - 100);
    }

    public void consumeFood() {
        produceFood();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
