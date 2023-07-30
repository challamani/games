package com.practice.game.misc;

import java.util.ArrayList;
import java.util.List;

public class ConsolePos {
    public static void main(String[] args) throws InterruptedException {
        clearScreen();
        char escCode = 0x1B;

        List<Pos> posList = new ArrayList<>();


        posList.add(new Pos(17,14));
        posList.add(new Pos(16,15));
        posList.add(new Pos(15,16));
        posList.add(new Pos(14,17));

        posList.add(new Pos(14,14));
        posList.add(new Pos(13,14));
        posList.add(new Pos(12,14));
        posList.add(new Pos(11,14));

        posList.add(new Pos(10,1));
        posList.add(new Pos(11,1));
        posList.add(new Pos(12,1));
        posList.add(new Pos(13,1));

        posList.add(new Pos(14,2));
        posList.add(new Pos(15,3));
        posList.add(new Pos(16,4));
        posList.add(new Pos(17,5));

        posList.add(new Pos(18,6));
        posList.add(new Pos(18,7));
        posList.add(new Pos(18,8));
        posList.add(new Pos(18,9));
        posList.add(new Pos(18,10));
        posList.add(new Pos(18,11));
        posList.add(new Pos(18,12));
        posList.add(new Pos(18,13));

        for (Pos pos: posList) {
            System.out.printf("%s[%d;%df @", escCode, pos.getRow(), pos.getCol());
            Thread.sleep(500);
        }

        System.out.println();

    }

    public static void clearScreen() {
        try {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (Exception ex) {
            System.out.println("exception : "+ex.getMessage());
        }
    }
}

class Pos {
    private int row;
    private int col;

    public Pos(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
