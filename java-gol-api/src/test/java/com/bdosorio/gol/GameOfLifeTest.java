package com.bdosorio.gol;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by brianosorio on 12/29/13.
 */
public class GameOfLifeTest {

    public GameOfLife gol;

    @Before
    public void setup(){
        gol = new GameOfLife();
    }

    @Test
    public void testCellAt(){
        gol.addCell(new Cell(2,3));
        assertTrue(gol.cellAt(2, 3));
    }

    @Test
    public void testAddCell(){
        gol.addCell(new Cell(2,3));
        assertEquals(1, gol.getNumberLiving());
    }

    @Test
    public void testOneStarvation(){
        gol.addCell(new Cell(2,3));
        gol.step();
        assertFalse(gol.cellAt(2, 3));
    }

    @Test
    public void testCellStarvationWithNeighbor(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.step();
        assertFalse(gol.cellAt(2, 3));
    }

    @Test
    public void testCellLives(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(2,2));
        gol.step();
        assertTrue(gol.cellAt(2, 3));
    }

    @Test
    public void testTwoNeighborCount(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(2,2));
        assertEquals(2, gol.getNeighborCountOf(2,3));
    }

    @Test
    public void testCellOvercrowded(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(2,2));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.step();
        assertFalse(gol.cellAt(2, 3));
    }

    @Test
    public void testReproduction(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.step();
        assertTrue(gol.cellAt(2, 3));
    }

    @Test
    public void testNoReproduction(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.addCell(new Cell(2,2));
        gol.step();
        assertFalse(gol.cellAt(2, 3));
    }

    @Test
    public void testGetDeadList(){
        gol.addCell(new Cell(1,1));
        assertEquals(8, gol.getDeadList().size());
    }

    @Test
    public void testGetMinXOnEmpty(){
        assertEquals(0, gol.getXMin());
    }

    @Test
    public void testGetXMin(){
        gol.addCell(new Cell(1, 0));
        assertEquals(1, gol.getXMin());
    }

    @Test
    public void testGetYMin(){
        gol.addCell(new Cell(0,1));
        assertEquals(1, gol.getYMin());
    }

    @Test
    public void testGetXMax(){
        gol.addCell(new Cell(1, 0));
        assertEquals(1, gol.getXMax());
    }

    @Test
    public void testGetYMax(){
        gol.addCell(new Cell(0,1));
        assertEquals(1, gol.getYMax());
    }

    @Test
    public void testGetLiving(){
        gol.addCell(new Cell(0,1));
        gol.addCell(new Cell(0,2));
        List<Cell> testList = new ArrayList<Cell>();
        testList.add(new Cell(0,1));
        testList.add(new Cell(0,2));
        assertEquals(testList, gol.getLivingList());
    }

    @Test
    public void testAge(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.step();
        assertEquals(1, gol.cellAgeAt(2,4));
    }

    @Test
    public void testSpecies(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.step();
        assertFalse(gol.cellSpeciesAt(2,4) == gol.cellSpeciesAt(2,3));
    }

}
