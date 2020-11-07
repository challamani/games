package com.practice.game.snake;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Challa Manikanta.
 * CreatedOn 28th Oct 2016
 * Key Listener based snake movements capturing....
 * Runnable Interface implementation for Snake Movement capturing ..
 * <p>
 * All parameters depends on
 * Snake Ground(Width, Height)
 ***/

class SnakeArena extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private int x = 300;
    private int y = 50;
    private int radius = 10;
    private int snakeSize = 15;
    private int width;
    private int height;
    private int incX = 10;
    private int incY = 0;

    private int[][] snakePosition;
    private int[][] previousPosition;

    FoodLocation foodLocation;

    public SnakeArena(int width, int height) {

        this.width = width;
        this.height = height;

        snakePosition = new int[2][snakeSize];
        previousPosition = new int[2][snakeSize];

        foodLocation = new FoodLocation(width, height);

        setTitle("Snake Game...");
        setSize(this.width, this.height);
        setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(new KeyListenerImpl(this));

        initSnakePosition();
        foodLocation.produceFood();
    }

    public int getRadius() {
        return radius;
    }


    public void setIncX(int incX) {
        this.incX = incX;
    }


    public void setIncY(int incY) {
        this.incY = incY;
    }

    private void initSnakePosition() {
        for (int i = 0; i < snakeSize; i++) {
            snakePosition[0][i] = x - (i * radius);
            snakePosition[1][i] = y;
        }
    }

    public void run() {

        while (true) {

            try {

                x += (x > (width - radius)) ? (-1 * (width - 2 * radius)) : (x < 2 * radius) ? width - 2 * radius : incX;
                y += (y > (height - radius)) ? (-1 * (height - 2 * radius)) : (y < 2 * radius) ? height - 2 * radius : incY;
                repaint();
                Thread.sleep(100);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        drawing(g, x, y, radius, radius);
    }

    public void drawing(Graphics g, int x, int y, int w, int h) {

        previousPosition[0][0] = snakePosition[0][0];
        previousPosition[1][0] = snakePosition[1][0];
        snakePosition[0][0] = x;
        snakePosition[1][0] = y;

        {
            if ( (foodLocation.x - 5 <= x && x <= foodLocation.x + 5) && (foodLocation.y - 5 <= y && y <= foodLocation.y + 5)) {
                System.out.println("food consumed ...");
                g.setColor(Color.BLACK);
                g.drawOval(foodLocation.x, foodLocation.y, w, h);
                g.fillOval(foodLocation.x, foodLocation.y, w, h);
                foodLocation.consumeFood();
            } else {
                g.setColor(Color.GREEN);
                g.drawOval(foodLocation.x, foodLocation.y, w, h);
                g.fillOval(foodLocation.x, foodLocation.y, w, h);
            }
        }

        g.setColor(Color.RED);
        g.drawOval(snakePosition[0][0], snakePosition[1][0], w, h);
        g.fillOval(snakePosition[0][0], snakePosition[1][0], w, h);


        for (int i = 1; i < snakeSize; i++) {
            previousPosition[0][i] = snakePosition[0][i];
            previousPosition[1][i] = snakePosition[1][i];

            snakePosition[0][i] = previousPosition[0][i - 1];
            snakePosition[1][i] = previousPosition[1][i - 1];
            g.setColor(Color.MAGENTA);
            g.drawOval(snakePosition[0][i], snakePosition[1][i], w, h);
            g.fillOval(snakePosition[0][i], snakePosition[1][i], w, h);
        }

        g.setColor(Color.BLACK);
        g.drawOval(previousPosition[0][snakeSize - 1], previousPosition[1][snakeSize - 1], w, h);
        g.fillOval(previousPosition[0][snakeSize - 1], previousPosition[1][snakeSize - 1], w, h);
    }

}
