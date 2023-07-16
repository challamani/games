package com.practice.game.threads;

import java.util.Arrays;

public class MergeArrays {

    public static void main(String[] args) {

        int[] array1 = {1, 3, 5, 7, 9, 11, 13, 14, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 55, 57, 59, 61, 63, 65, 67, 69, 71, 73, 75, 77, 79, 81, 83, 85, 87, 89, 91, 93, 95, 97, 99, 101};
        int[] array2 = {1, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96, 98, 100};

        ArraySource arraySource = new ArraySource(array1, array2);
        ForwardMergingThread forwardMergingThread = new ForwardMergingThread(arraySource);
        BackwardMergingThread backwardMergingThread = new BackwardMergingThread(arraySource);

        new Thread(backwardMergingThread).start();
        new Thread(forwardMergingThread).start();

        System.out.println(Arrays.toString(arraySource.resultArray));
    }

    public static void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }

    public static void printWithColor(ArraySource arraySource){

        String redColor="\u001B[31m";
        String whiteColor="\u001B[37m";
        String greenColor="\u001B[32m";

        int firstIndexOfZero=Integer.MAX_VALUE;
        StringBuilder stringBuilder =  new StringBuilder(redColor);

        for(int i=0; i<arraySource.resultArray.length;i++) {
            if (arraySource.resultArray[i] != 0) {
                stringBuilder.append(String.format("[%d]",arraySource.resultArray[i])).append(" ");
            } else {
                firstIndexOfZero = i;
                stringBuilder.append("|").append(whiteColor);
                break;
            }
        }

        int lastIndexOfZero = firstIndexOfZero;
        for(int i = firstIndexOfZero; i<arraySource.resultArray.length;i++ ){
            if(arraySource.resultArray[i] == 0){
                stringBuilder.append(arraySource.resultArray[i]).append(" ");
            }else{
                lastIndexOfZero = i;
                break;
            }
        }

        stringBuilder.append(greenColor).append("|");
        for(int i = lastIndexOfZero; i < arraySource.resultArray.length ;i++) {
            stringBuilder.append(String.format("[%d]",arraySource.resultArray[i])).append(" ");
        }
        System.out.println(stringBuilder);
    }
}

class ArraySource {
    public int[] array1;
    public int[] array2;
    public int[] resultArray;

    public ArraySource(int[] inputArray1, int[] inputArray2) {
        array1 = inputArray1;
        array2 = inputArray2;
        resultArray = new int[array1.length + array2.length];
        Arrays.fill(resultArray, 0);
    }
}

class  ForwardMergingThread implements Runnable{

    private ArraySource arraySource;
    public ForwardMergingThread(ArraySource arraySource) {
        this.arraySource = arraySource;
    }

    @Override
    public void run() {

        int limit = (arraySource.resultArray.length / 2);
        int p = 0;
        int q = 0;

        for (int i = 0; i < limit; i++) {
            try{
                Thread.sleep(100);
            }catch (InterruptedException ie){
            }
            if (arraySource.array1[p] <= arraySource.array2[q]) {
                arraySource.resultArray[i] = arraySource.array1[p];
                ++p;
                continue;
            }
            arraySource.resultArray[i] = arraySource.array2[q];
            ++q;

            MergeArrays.clearScreen();
            MergeArrays.printWithColor(arraySource);
        }
    }
}

class  BackwardMergingThread implements Runnable {
    private ArraySource arraySource;
    public BackwardMergingThread(ArraySource arraySource) {
        this.arraySource = arraySource;
    }
    @Override
    public void run() {
        int init = (arraySource.resultArray.length / 2);
        int p = arraySource.array1.length - 1;
        int q = arraySource.array2.length - 1;

        for (int i = (arraySource.resultArray.length - 1); i >= init; i--) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                System.out.println("backward merging thread interrupted : "+ie.getMessage());
            }

            if(arraySource.array1[p] >= arraySource.array2[q]){
                arraySource.resultArray[i] = arraySource.array1[p];
                --p;
                continue;
            }
            arraySource.resultArray[i] = arraySource.array2[q];
            --q;
            MergeArrays.clearScreen();
            MergeArrays.printWithColor(arraySource);
        }
    }
}




