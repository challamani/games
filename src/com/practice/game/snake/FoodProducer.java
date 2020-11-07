package com.practice.game.snake;


public class FoodProducer extends  Thread {

    private FoodLocation foodLocation;

    public FoodProducer(FoodLocation fm) {
        foodLocation = fm;
    }

    public void run() {

        try {

            synchronized (foodLocation) {
                foodLocation.produceFood();
                foodLocation.wait(8000);
            }

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
