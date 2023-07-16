package com.practice.game.matrix;

import java.util.Scanner;

public class MagicMatrix {

    public  static String WHITE_COLOR="\u001B[37m";
    public  static String GREEN_COLOR="\u001B[32m";
    public MagicMatrix(){
    }
    public int[][] createMagicMatrix(int size){
        int[][] matrix = new int[size][size];

        int element=1;
        int maxValue = size*size;

        int row=0;
        int col = size/2;
        matrix[row][col] = element;
        while(++element <= maxValue) {
            //find a diagonal cell for next element
            row -= 1;
            col += 1;

            if(row < 0 && col >= size){ //if diagonal cell not exist
                row += 2;
                col -= 1;
            } else if(row < 0 && col < size){ //diagonal cell not exists due to row out of range
                row = size-1;
            }else if(row < size && col >= size){ //diagonal cell not exist due to column out of range
                col = 0;
            }

            //element already exists in the corresponding cell.
            if(matrix[row][col] != 0){
                row += 2;
                col -= 1;
            }
            matrix[row][col] = element;
            printMatrix(matrix, row, col);
        }
        return matrix;
    }

    protected void printMatrix(int[][] matrix, int row, int col){
        clearScreen();
        System.out.print(WHITE_COLOR);
        for(int i=0; i< matrix.length; i++){
            for(int j=0; j<matrix[i].length; j++){
                if(row == i && col == j){
                    System.out.print(GREEN_COLOR+String.format(" |%3d| ", matrix[i][j]));
                    System.out.print(WHITE_COLOR);
                }else {
                    System.out.print(String.format(" |%3d| ", matrix[i][j]));
                }
            }
            System.out.println("\n");
        }
    }

    protected void validateMatrix(int[][] matrix){
        clearScreen();
        System.out.print(WHITE_COLOR);
        int[] colSum = new int[matrix.length];

        for(int i=0; i< matrix.length; i++) {
            int rowSum = 0;
            System.out.print(WHITE_COLOR);
            for (int j = 0; j < matrix[i].length; j++) {
                colSum[j] += matrix[i][j];
                rowSum += matrix[i][j];
                System.out.print(String.format(" |%3d| ", matrix[i][j]));
            }
            System.out.print(GREEN_COLOR);
            System.out.print(String.format(" %3d", rowSum));
            System.out.println("\n");
        }

        for(int i=0; i<colSum.length; i++){
            System.out.print(String.format(" |%3d| ",colSum[i]));
        }
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
        System.out.print("Enter magic matrix size :");
        int size = scanner.nextInt();
        System.out.println(String.format("Magic Matrix size %d",size));
        MagicMatrix magicMatrix =  new MagicMatrix();
        int[][] matrix =  magicMatrix.createMagicMatrix(size);
        magicMatrix.validateMatrix(matrix);
        scanner.close();
    }
}
