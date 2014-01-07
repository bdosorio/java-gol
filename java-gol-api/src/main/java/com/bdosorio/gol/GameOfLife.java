package com.bdosorio.gol;

import java.util.ArrayList;
import java.util.List;

public class GameOfLife {

    public final List<Cell> cellList = new ArrayList<Cell>();
    public final List<Cell> removeList = new ArrayList<Cell>();
    public int xMin = 0;
    public int yMin = 0;
    public int xMax = 0;
    public int yMax = 0;
    private int lastSpecies = 1;

    public int getNumberLiving() {
        return cellList.size();
    }

    public void addCell(Cell cell) {
        cellList.add(cell);
        updateEdgeValue(cell);
    }

    private void updateEdgeValue(Cell cell) {
        if (cellList.size() == 1){
            xMin = cell.x;
            xMax = cell.x;
            yMin = cell.y;
            yMax = cell.y;
        }
        else  {
            if (cell.x < xMin) xMin = cell.x;
            if (cell.x > xMax) xMax = cell.x;
            if (cell.y < yMin) yMin = cell.y;
            if (cell.y > yMax) yMax = cell.y;
        }
    }

    public void step() {
        //check living
        for (Cell cell : cellList) {
            // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
            // And any live cell with more than three live neighbours dies, as if by overcrowding.
            if (getNeighborCountOf(cell.x, cell.y) < 2 || getNeighborCountOf(cell.x, cell.y) > 3) {
                removeList.add(cell);
            }

        }
        //check dead
        for (Cell cell : getDeadList()) {
            // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            if (getNeighborCountOf(cell.x, cell.y) == 3) {
                lastSpecies++;
                cell.setSpecies(lastSpecies);
                cellList.add(cell);
            }
        }
        //check dead
        cellList.removeAll(removeList);
        updateAges();
        updateEdgeValues();
    }

    private void updateAges() {
        for(Cell cell : cellList)
        {
            cell.age++;
        }
    }

    public List<Cell> getDeadList() {
        final List<Cell> deadList = new ArrayList<Cell>();
        if (cellList.size() > 0) {
            for (int x = xMin - 1; x <= xMax + 1; x++) {
                for (int y = yMin - 1; y <= yMax + 1; y++) {
                    if (!cellAt(x, y)) {
                        deadList.add(new Cell(x, y));
                    }
                }
            }
        }
        return deadList;
    }

    private void updateEdgeValues() {
        if (cellList.size() > 0) {
            xMin = cellList.get(0).x;
            yMin = cellList.get(0).y;
            yMax = cellList.get(0).y;
            xMax = cellList.get(0).x;
        }
        for (Cell cell : cellList) {
            updateEdgeValue(cell);
        }
    }

    public boolean cellAt(int x, int y) {
        return cellList.contains(new Cell(x, y));
    }

    public int getNeighborCountOf(int x, int y) {
        final Cell input = new Cell(x, y);
        int count = 0;
        if (cellAt(x, y + 1)) {
            count++;
        }
        if (cellAt(x, y - 1)) {
            count++;
        }
        if (cellAt(x - 1, y)) {
            count++;
        }
        if (cellAt(x - 1, y + 1)) {
            count++;
        }
        if (cellAt(x - 1, y - 1)) {
            count++;
        }
        if (cellAt(x + 1, y)) {
            count++;
        }
        if (cellAt(x + 1, y + 1)) {
            count++;
        }
        if (cellAt(x + 1, y - 1)) {
            count++;
        }
        return count;
    }

    public int getXMin() {
        return xMin;
    }

    public int getYMin() {
        return yMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public List<Cell> getLivingList() {
        return cellList;
    }

    public int cellAgeAt(int x, int y) {
        if(cellAt(x,y)){
            return cellList.get(cellList.indexOf(new Cell(x,y))).age;
        }
        return 0;
    }

    public int cellSpeciesAt(int x, int y) {
        if(cellAt(x,y)){
            return cellList.get(cellList.indexOf(new Cell(x,y))).getSpecies();
        }
        return 0;
    }
}
