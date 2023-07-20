package com.practice.game.misc;

import java.util.Scanner;

public class NumberUtil {

    //The brute force approach
    public boolean isPrimeBruteForceApproach(int number){
        boolean isPrime=true;
        for(int i=2; i< number; i++){
            if(number % i == 0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    //The efficient approach
    public boolean isPrime(int number){
        boolean isPrime=true;
        if(number % 2 ==0){
            return false;
        }

        for(int i=3; i<= Math.sqrt(number); i+= 2){
            if(number % i == 0){
                isPrime=false;
                break;
            }
        }
        return isPrime;
    }
    public static void main(String[] args) {
        NumberUtil numberUtil = new NumberUtil();
        Scanner scanner = new Scanner(System.in);
        char ch;
        do {
            System.out.println("Enter a number: ");
            int number = scanner.nextInt();
            boolean isPrime = numberUtil.isPrime(number);
            System.out.printf("Number %d is %s \n", number, (isPrime ? "a prime number" : "not a prime number"));

            System.out.println("\ndo you wanna continue (Y/N)?");
            ch = scanner.next().charAt(0);
        } while (ch == 'Y');
        scanner.close();
    }
}
