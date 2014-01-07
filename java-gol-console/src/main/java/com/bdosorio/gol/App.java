package com.bdosorio.gol;

import java.io.IOException;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        GameOfLife gol = new GameOfLife();

        //setup
        Random rand = new Random();
        int startCount = Math.abs(rand.nextInt(35));
        int size = Math.abs(startCount/2 + rand.nextInt(startCount));
        for (int i = 0; i < startCount; i++) {
            gol.addCell(new Cell(rand.nextInt(size),rand.nextInt(size)));
        }
        //run
        int i = 0;
        while(gol.getNumberLiving() > 0){
            System.out.println("Iteration: " +i);
            System.out.println(drawBoard(gol));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gol.step();
            i++;
        }
    }

    private static String drawBoard(final GameOfLife gol) {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ");
        sb.append((gol.getXMax() - gol.getXMin()) + 1);
        sb.append(" x ");
        sb.append((gol.getYMax() - gol.getYMin()) + 1);
        sb.append("\n");
        sb.append("   ");
        for (int i = gol.getYMin(); i <= gol.getYMax(); i++){
            sb.append(i);
        }
        sb.append("\n");
        for (int x = gol.getXMin(); x <= gol.getXMax(); x++) {
            sb.append(x);
            sb.append(" |");
            for (int y = gol.getYMin(); y <= gol.getYMax(); y++) {
                if(gol.cellAt(x,y)) sb.append(drawCell(x,y,gol));
                else sb.append(drawDead(x,y,gol));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String drawDead(int x, int y, GameOfLife gol) {
        return " ";
    }

    private static String drawCell(int x, int y, GameOfLife gol) {
        //return "#";
        return String.valueOf((char) ('A' + gol.cellSpeciesAt(x,y)));
    }


}
