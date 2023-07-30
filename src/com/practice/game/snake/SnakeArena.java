package com.practice.game.snake;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Author: Challa Manikanta.
 * CreatedOn 28th Oct 2016
 * Key Listener based snake movements capturing....
 * Runnable Interface implementation for Snake Movement capturing.
 * <p>
 * All parameters depending on
 * Snake Ground(Width, Height)
 ***/

class SnakeArena extends JFrame implements Runnable {
    private int x;
    private int y;
    private int radius;
    private int size;
    private int width;
    private int height;
    private int incX;
    private int incY;
    private int[][] snake;
    private int[][] previous;
    private final FoodLocation foodLocation;
    private final Map<Integer, Integer> levelAndSpeed;
    private int level=1;
    private int score=0;
    public SnakeArena(int width, int height, int s,
                      Map<Integer, Integer> levelAndSpeed) {

        this.width = width;
        this.height = height;
        this.size = s;
        this.levelAndSpeed =  levelAndSpeed;

        previous = new int[size][2];
        snake = new int[size][2];

        foodLocation = new FoodLocation(width, height);
        setTitle("Snake Game implemented by MyTechHub3");
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
        for (int i = 0; i < size; i++) {
            snake[i][0] = x - (i * radius);
            snake[i][1] = y;
        }
    }
    public void run() {
        while (true) {
            try {
                x += (x > (width - radius)) ? (-1 * (width - 2 * radius))
                        : (x < 2 * radius) ? width - 2 * radius : incX;
                y += (y > (height - radius)) ? (-1 * (height - 2 * radius))
                        : (y < 2 * radius) ? height - 2 * radius : incY;
                repaint();
                Thread.sleep(levelAndSpeed.get(level));
            } catch (InterruptedException ie) {
                System.out.println("failed at snake auto movement delay :"
                        + ie.getMessage());
            }
        }
    }

    public void paint(Graphics g) {
        drawing(g, x, y, radius, radius);
    }

    public void drawing(Graphics g, int x, int y, int w, int h) {

        previous[0][0] = snake[0][0];
        previous[0][1] = snake[0][1];

        snake[0][0] = x;
        snake[0][1] = y;
        validateSnakePosition(g, w, h);
        g.setColor(Color.MAGENTA);
        g.drawOval(snake[0][0], snake[0][1], w, h);
        g.fillOval(snake[0][0], snake[0][1], w, h);

        for (int i = 1; i < size; i++) {
            previous[i][0] = snake[i][0];
            previous[i][1] = snake[i][1];

            snake[i][0] = previous[i - 1][0];
            snake[i][1] = previous[i - 1][1];

            g.setColor(Color.GREEN);
            g.drawOval(snake[i][0], snake[i][1], w, h);
            g.fillOval(snake[i][0], snake[i][1], w, h);
        }

        g.setColor(Color.BLACK);
        g.drawOval(snake[size - 1][0], snake[size - 1][1], w, h);
        g.fillOval(snake[size - 1][0], snake[size - 1][1], w, h);
    }

    public void validateSnakePosition(Graphics g, int w, int h) {
        int headX = x;
        int headY = y;
        int foodX = foodLocation.getX();
        int foodY = foodLocation.getY();

        if ((headX != 0 && headY != 0)
                && (headX >= foodX - 10 && headX <= foodX + 10)
                && (headY >= foodY - 10 && headY <= foodY + 10)) {

            System.out.printf("food location [%d, %d] \n" +
                            "snake head [%d, %d]",
                    foodX, foodY,
                    headX, headY);
            //calculate the score based on exact match
            calculateScoreAndLevel(g);
            //consume the food item to produce new one.
            g.setColor(Color.BLACK);
            //match with background color to clear
            g.drawOval(foodX, foodY, w, h);
            g.fillOval(foodX, foodY, w, h);
            foodLocation.consumeFood();//for new food item
        }

        //to render the food item
        g.setColor(Color.WHITE);
        //match with background color to clear
        g.drawOval(foodLocation.getX(), foodLocation.getY(), w, h);
        g.fillOval(foodLocation.getX(), foodLocation.getY(), w, h);
    }

    private void calculateScoreAndLevel(Graphics g) {
        g.setColor(Color.BLACK);
        //Match background color to clear the previous items
        g.drawString("Level: " + getLevel(), 50, 60);
        g.drawString("Score: " + getScore(), 50, 75);
        g.drawString("Speed: " + levelAndSpeed.get(getLevel()),
                50, 90);

        if (isExactMatch()) {
            score += 10;
        } else {
            score += 5;
        }

        int result = score / 50;
        if (result != 0 && result < 4) {
            setLevel(result + 1);
        }
        g.setColor(Color.WHITE);
        //Match background color to clear the previous items
        g.drawString("Level: " + getLevel(), 50, 60);
        g.drawString("Score: " + getScore(), 50, 75);
        g.drawString("Speed: " + levelAndSpeed.get(getLevel()),
                50, 90);
    }


    private boolean isExactMatch() {
        return (x == foodLocation.getX()
                && y == foodLocation.getY());
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}
