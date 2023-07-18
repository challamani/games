package com.practice.game.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class MaxChar {

    public  static String WHITE_COLOR="\u001B[37m";
    public  static String GREEN_COLOR="\u001B[32m";
    public char findMaxRepeatedChar(String input){

        int[] charCount = new int[26];
        for(char ch: input.toCharArray()){
            charCount[ch-'a']++;
            printArray(charCount);
        }
        int maxCharIndex = 0;
        int repeatedCharCnt = Integer.MIN_VALUE;
        for(int index=0; index < 26; index++){
             if(repeatedCharCnt < charCount[index]){
                 repeatedCharCnt = charCount[index];
                 maxCharIndex = index;
             }
        }
        return (char)(maxCharIndex+'a');
    }

    protected void printArray(int[] charCount) {
        clearScreen();
        System.out.println(WHITE_COLOR);
        for (int index = 0; index < 25; index++) {
            System.out.printf(WHITE_COLOR+"  char[%c] = ", (char) (index + 'a'));
            System.out.print(GREEN_COLOR + charCount[index]);
            index += 1;
            System.out.printf(WHITE_COLOR+"  char[%c] = ", (char) (index + 'a'));
            System.out.print(GREEN_COLOR + charCount[index]);
            System.out.println("\n");
        }
        System.out.println(WHITE_COLOR);
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
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        MaxChar maxChar = new MaxChar();
        char ch = maxChar.findMaxRepeatedChar(input);
        System.out.println("Max repeated char is: '"+ch+"'");
        scanner.close();
    }
}
