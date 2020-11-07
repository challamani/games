package com.practice.game.snake;

import java.util.Random;

public class FoodLocation {

    public int x;
    public int y;

    private int xLimit;
    private int yLimit;


    public FoodLocation(int xboundary, int yboundary) {
        xLimit = xboundary;
        yLimit = yboundary;
    }


    public void produceFood() {
        x = new Random().nextInt(xLimit-50) + 1;
        y = new Random().nextInt(yLimit-50) + 1;
        System.out.println("produced :: ["+ x + "," + y +" ]");
    }

    public void consumeFood() {
        System.out.println(x + " " + y + " score :: ");
        produceFood();
    }

}
