package com.bdosorio.gol;

import java.util.List;

public interface GameOfLife {
    int getNumberLiving();

    void addCellAt(int x, int y);

    void step();

    List<Cell> getDeadList();

    boolean cellLivesAt(int x, int y);

    int getNeighborCountOf(int x, int y);

    int getXMin();

    int getYMin();

    int getXMax();

    int getYMax();

    List<Cell> getLivingList();
}
