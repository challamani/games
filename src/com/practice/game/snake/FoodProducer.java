package com.practice.game.snake;


public class FoodProducer extends  Thread {

    private final FoodLocation foodLocation;
    private int wait;

    public FoodProducer(FoodLocation foodLocation, int wait) {
        this.foodLocation = foodLocation;
        this.wait = wait;
    }

    public void run() {
        try {
            synchronized (foodLocation) {
                foodLocation.produceFood();
                foodLocation.wait(wait);
            }
        } catch (InterruptedException ie) {
            System.out.println("failed at food generation:"
                    + ie.getMessage());
        }
    }
}
