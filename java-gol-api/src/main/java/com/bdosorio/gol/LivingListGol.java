package com.bdosorio.gol;

import java.util.ArrayList;
import java.util.List;

public class LivingListGol implements GameOfLife {

    private final List<Cell> cellList = new ArrayList<Cell>();
    private final List<Cell> removeList = new ArrayList<Cell>();
    private int xMin = 0;
    private int yMin = 0;
    private int xMax = 0;
    private int yMax = 0;
    private int lastSpecies = 0;

    @Override
    public int getNumberLiving() {
        return cellList.size();
    }

    @Override
    public void addCellAt(int x, int y) {
        final Cell cell = new Cell(x,y);
        cellList.add(cell);
        updateEdgeValue(cell);
    }

    private void updateEdgeValue(Cell cell) {
        if (cellList.size() == 1){
            xMin = cell.getX();
            xMax = cell.getX();
            yMin = cell.getY();
            yMax = cell.getY();
        }
        else  {
            if (cell.getX() < xMin) xMin = cell.getX();
            if (cell.getX() > xMax) xMax = cell.getX();
            if (cell.getY() < yMin) yMin = cell.getY();
            if (cell.getY() > yMax) yMax = cell.getY();
        }
    }

    @Override
    public void step() {
        //check living
        for (Cell cell : cellList) {
            // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
            // And any live cell with more than three live neighbours dies, as if by overcrowding.
            if (getNeighborCountOf(cell.getX(), cell.getY()) < 2 || getNeighborCountOf(cell.getX(), cell.getY()) > 3) {
                removeList.add(cell);
            }

        }
        //check dead
        for (Cell cell : getDeadList()) {
            // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
            if (getNeighborCountOf(cell.getX(), cell.getY()) == 3) {
                cell.setSpecies(lastSpecies++);
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
            cell.setAge(cell.getAge() +1);
        }
    }

    @Override
    public List<Cell> getDeadList() {
        final List<Cell> deadList = new ArrayList<Cell>();
        if (cellList.size() > 0) {
            for (int x = xMin - 1; x <= xMax + 1; x++) {
                for (int y = yMin - 1; y <= yMax + 1; y++) {
                    if (!cellLivesAt(x, y)) {
                        deadList.add(new Cell(x, y));
                    }
                }
            }
        }
        return deadList;
    }

    private void updateEdgeValues() {
        if (cellList.size() > 0) {
            xMin = cellList.get(0).getX();
            yMin = cellList.get(0).getX();
            yMax = cellList.get(0).getX();
            xMax = cellList.get(0).getX();
        }
        for (Cell cell : cellList) {
            updateEdgeValue(cell);
        }
    }

    @Override
    public boolean cellLivesAt(int x, int y) {
        return cellList.contains(new Cell(x, y));
    }

    @Override
    public int getNeighborCountOf(int x, int y) {
        int count = 0;
        if (cellLivesAt(x, y + 1)) {
            count++;
        }
        if (cellLivesAt(x, y - 1)) {
            count++;
        }
        if (cellLivesAt(x - 1, y)) {
            count++;
        }
        if (cellLivesAt(x - 1, y + 1)) {
            count++;
        }
        if (cellLivesAt(x - 1, y - 1)) {
            count++;
        }
        if (cellLivesAt(x + 1, y)) {
            count++;
        }
        if (cellLivesAt(x + 1, y + 1)) {
            count++;
        }
        if (cellLivesAt(x + 1, y - 1)) {
            count++;
        }
        return count;
    }

    @Override
    public int getXMin() {
        return xMin;
    }

    @Override
    public int getYMin() {
        return yMin;
    }

    @Override
    public int getXMax() {
        return xMax;
    }

    @Override
    public int getYMax() {
        return yMax;
    }

    @Override
    public List<Cell> getLivingList() {
        return cellList;
    }

    public int cellAgeAt(int x, int y) {
        if(cellLivesAt(x, y)){
            return cellList.get(cellList.indexOf(new Cell(x,y))).getAge();
        }
        return 0;
    }

    public int cellSpeciesAt(int x, int y) {
        if(cellLivesAt(x, y)){
            return cellList.get(cellList.indexOf(new Cell(x,y))).getSpecies();
        }
        return 0;
    }
}
