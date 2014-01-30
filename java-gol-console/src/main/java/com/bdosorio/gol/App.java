package com.bdosorio.gol;

import com.google.common.base.Strings;
import java.util.Random;

/**
 * Standard console runner for java-gol-api
 */
public class App {


    private static final int COLUMN_LABEL_LENGTH = 3;
    private static final int DEFAULT_START_MAX_COUNT = 35;
    public static void main(String[] args) {
        LivingListGol gol = new LivingListGol();

        //setup
        Random rand = new Random();
        int startCount = Math.abs(rand.nextInt(DEFAULT_START_MAX_COUNT));
        int size = Math.abs((startCount / COLUMN_LABEL_LENGTH) + rand.nextInt(startCount));
        for (int i = 0; i < startCount; i++) {
            gol.addCellAt(rand.nextInt(size), rand.nextInt(size));
        }
        //run
        int i = 0;
        while (gol.getNumberLiving() > 0 && i < 50) {
            System.out.println("Iteration: " + i);
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

    private static String drawBoard(final LivingListGol gol) {
        StringBuilder sb = new StringBuilder();
        sb.append("Size: ");
        sb.append((gol.getXMax() - gol.getXMin()) + 1);
        sb.append(" x ");
        sb.append((gol.getYMax() - gol.getYMin()) + 1);
        sb.append("\n");
        sb.append(Strings.padEnd("\n", COLUMN_LABEL_LENGTH + 2, ' '));
        for (int y = gol.getYMin(); y <= gol.getYMax(); y++) {
            sb.append(Strings.padStart(String.valueOf(y), COLUMN_LABEL_LENGTH, ' '));
        }
        sb.append("\n    ");
        sb.append(Strings.padStart("\n", COLUMN_LABEL_LENGTH * (gol.getYMax() - gol.getYMin() +1 ) +1, '-'));
        for (int x = gol.getXMin(); x <= gol.getXMax(); x++) {
            sb.append(Strings.padStart(String.valueOf(x), COLUMN_LABEL_LENGTH, ' '));
            sb.append("|");
            for (int y = gol.getYMin(); y <= gol.getYMax(); y++) {
                if (gol.cellLivesAt(x, y)) sb.append(drawCell(x, y, gol));
                else sb.append(drawDead());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String drawDead() {
        final String deadValue = " ";
        return Strings.padStart(deadValue, COLUMN_LABEL_LENGTH, ' ');
    }

    private static String drawCell(int x, int y, LivingListGol gol) {
//        final String cellValue = String.valueOf(gol.cellSpeciesAt(x, y));
        final String cellValue = String.valueOf(gol.cellAgeAt(x, y));
        return Strings.padStart(cellValue, COLUMN_LABEL_LENGTH, ' ');
    }


}
