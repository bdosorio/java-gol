package gol;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class GameOfLife {

    public final List<Cell> cellList = new ArrayList<Cell>();
    public final List<Cell> removeList = new ArrayList<Cell>();

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public int getLivingCells() {
        return cellList.size();
    }

    public void addCell(Cell cell) {
        cellList.add(cell);
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
            if(getNeighborCountOf(cell.x, cell.y) == 3)
            {
                cellList.add(cell);
            }
        }
        //check dead
        cellList.removeAll(removeList);
    }

    public List<Cell> getDeadList() {
        final List<Cell> deadList = new ArrayList<Cell>();
        if (cellList.size() > 0) {
            final Cell initialCell=cellList.get(0);
            int xMin = initialCell.x;
            int xMax = initialCell.y;
            int yMin = initialCell.x;
            int yMax = initialCell.y;
            for (Cell cell : cellList) {
                if (cell.x < xMin) xMin = cell.x;
                if (cell.x > xMax) xMax = cell.x;
                if (cell.y < yMin) yMin = cell.y;
                if (cell.y > yMax) yMax = cell.y;
            }
            for(int x = xMin; x <= xMax; x++){
                for(int y = yMin; y<=yMax; y++){
                    final Cell testCell = new Cell(x,y);
                    if(!cellList.contains(testCell)){
                        deadList.add(testCell);
                    }
                }
            }
        }
        return deadList;
    }

    public boolean isCellAt(int x, int y) {
        return cellList.contains(new Cell(x, y));
    }

    public int getNeighborCountOf(int x, int y) {
        final Cell input = new Cell(x, y);
        int count = 0;
        if (cellList.contains(new Cell(x, y + 1))) {
            count++;
        }
        if (cellList.contains(new Cell(x, y - 1))) {
            count++;
        }
        if (cellList.contains(new Cell(x - 1, y))) {
            count++;
        }
        if (cellList.contains(new Cell(x - 1, y + 1))) {
            count++;
        }
        if (cellList.contains(new Cell(x - 1, y - 1))) {
            count++;
        }
        if (cellList.contains(new Cell(x + 1, y))) {
            count++;
        }
        if (cellList.contains(new Cell(x + 1, y + 1))) {
            count++;
        }
        if (cellList.contains(new Cell(x + 1, y - 1))) {
            count++;
        }
        return count;
    }
}
