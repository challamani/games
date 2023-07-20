package com.practice.game.arrays;

import java.util.Scanner;

public class RotateArray {

    public  static String GREEN_COLOR="\u001B[32m";

    //Rotate array in brute force approach
    public void rotateArray(int[] input, int n){

        int index=0;
        while(index < n){
            int temp = input[0];
            for(int i=1; i< input.length; i++){
                input[i-1] = input[i];
            }
            input[input.length-1] = temp;
            index++;
            printArray(input);
        }
    }

    //Rotate array in efficient approach
    public void optimalArrayRotation(int[] input, int n){
        //n: no of rotations from left -> right
        int index=0;
        while(index < (n % input.length)){
            int baseValue = input[0];
            for(int ind=1; ind< input.length; ind++){
                input[ind - 1] = input[ind];
            }
            input[input.length-1] = baseValue;
            index++;
            printArray(input);
        }
    }

    protected void printArray(int[] array) {
        clearScreen();
        System.out.println("\n\n");
        System.out.printf("{");
        for (int i = 0; i < array.length; i++) {
            System.out.printf(GREEN_COLOR + " [%d] ", array[i]);
        }
        System.out.printf("}");
    }
    protected void clearScreen() {
        try {
            Thread.sleep(100);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }

    public static void main(String[] args) {

        RotateArray rotateArray = new RotateArray();
        Scanner scanner = new Scanner(System.in);
        char ch;
        do {
            int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            System.out.println("Choose array rotation approach: \n 1. Efficient approach \n 2. Brute force approach");
            int option = scanner.nextInt();
            System.out.println("Enter number for rotations left -> right : ");
            int number = scanner.nextInt();
            if (option == 1) {
                rotateArray.optimalArrayRotation(input, number);
            } else {
                rotateArray.rotateArray(input, number);
            }
            System.out.println("\n do you wanna continue (Y/N)?");
            ch = scanner.next().charAt(0);
        } while (ch == 'Y');
        System.out.println("Hope you found the difference, a good programmer always think for a better solution. \n Thank you!");
        scanner.close();
    }
}
