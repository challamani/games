package com.practice.game.threads;

import java.util.Arrays;

public class MergeArrays {

    public static void main(String[] args) {

        Integer[] array1 = {1, 2, 3, 3, 3, 4, 4, 4, 4, 5, 6, 7, 9, 9, 99, 999, 1000, 1001};
        Integer[] array2 = {-1, 0, 0, 0, 0, 0, 4, 8, 9, 10, 11, 12, 13, 14, 44, 55, 66, 77, 8900};

        ArraySource arraySource = new ArraySource(array1, array2);
        ForwardMergingThread forwardMergingThread = new ForwardMergingThread(arraySource);
        BackwardMergingThread backwardMergingThread = new BackwardMergingThread(arraySource);

        new Thread(backwardMergingThread).start();
        new Thread(forwardMergingThread).start();

        System.out.println("Thread :: " + Thread.currentThread() + " :: " + Arrays.asList(arraySource.resultArray));
    }
}

class ArraySource {

    public Integer[] array1;
    public Integer[] array2;
    public Integer[] resultArray;

    public ArraySource(Integer[] inputArray1, Integer[] inputArray2) {
        array1 = inputArray1;
        array2 = inputArray2;
        resultArray = new Integer[array1.length + array2.length];
    }
}

class  ForwardMergingThread implements Runnable{


    private ArraySource arraySource;
    public ForwardMergingThread(ArraySource arraySource) {
        this.arraySource = arraySource;
    }

    @Override
    public void run() {

        int init = (arraySource.resultArray.length / 2);

        int p = 0;
        int q = 0;

        for (int i = 0; i < init; i++) {

            System.out.println("Thread :: "+Thread.currentThread()+ " :: "+ Arrays.asList( arraySource.resultArray));
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


        }

        System.out.println("Thread :: "+Thread.currentThread()+ " :: "+ Arrays.asList( arraySource.resultArray));
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

            System.out.println("Thread :: " + Thread.currentThread() + " :: " + Arrays.asList(arraySource.resultArray));
            try {

                Thread.sleep(100);
            } catch (InterruptedException ie) {

            }

            if (arraySource.array1[p] >= arraySource.array2[q]) {
                arraySource.resultArray[i] = arraySource.array1[p];
                --p;
                continue;
            }

            arraySource.resultArray[i] = arraySource.array2[q];
            --q;
        }
        System.out.println("Thread :: " + Thread.currentThread() + " :: " + Arrays.asList(arraySource.resultArray));
    }
}




