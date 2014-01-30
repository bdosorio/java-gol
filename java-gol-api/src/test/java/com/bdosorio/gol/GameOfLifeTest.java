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
        gol = new LivingListGol();
    }

    @Test
    public void testCellAt(){
        gol.addCellAt(2, 3);
        assertTrue(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testAddCell(){
        gol.addCellAt(2, 3);
        assertEquals(1, gol.getNumberLiving());
    }

    @Test
    public void testOneStarvation(){
        gol.addCellAt(2, 3);
        gol.step();
        assertFalse(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testCellStarvationWithNeighbor(){
        gol.addCellAt(2, 3);
        gol.addCellAt(2, 4);
        gol.step();
        assertFalse(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testCellLives(){
        gol.addCellAt(2, 3);
        gol.addCellAt(2, 4);
        gol.addCellAt(2, 2);
        gol.step();
        assertTrue(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testTwoNeighborCount(){
        gol.addCellAt(2, 3);
        gol.addCellAt(2, 4);
        gol.addCellAt(2, 2);
        assertEquals(2, gol.getNeighborCountOf(2,3));
    }

    @Test
    public void testCellOvercrowded(){
        gol.addCellAt(2, 3);
        gol.addCellAt(2, 4);
        gol.addCellAt(2, 2);
        gol.addCellAt(1, 3);
        gol.addCellAt(3, 3);
        gol.step();
        assertFalse(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testReproduction(){
        gol.addCellAt(2, 4);
        gol.addCellAt(1, 3);
        gol.addCellAt(3, 3);
        gol.step();
        assertTrue(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testNoReproduction(){
        gol.addCellAt(2, 4);
        gol.addCellAt(1, 3);
        gol.addCellAt(3, 3);
        gol.addCellAt(2, 2);
        gol.step();
        assertFalse(gol.cellLivesAt(2, 3));
    }

    @Test
    public void testGetDeadList(){
        gol.addCellAt(1, 1);
        assertEquals(8, gol.getDeadList().size());
    }

    @Test
    public void testGetMinXOnEmpty(){
        assertEquals(0, gol.getXMin());
    }

    @Test
    public void testGetXMin(){
        gol.addCellAt(1, 0);
        assertEquals(1, gol.getXMin());
    }

    @Test
    public void testGetYMin(){
        gol.addCellAt(0, 1);
        assertEquals(1, gol.getYMin());
    }

    @Test
    public void testGetXMax(){
        gol.addCellAt(1, 0);
        assertEquals(1, gol.getXMax());
    }

    @Test
    public void testGetYMax(){
        gol.addCellAt(0, 1);
        assertEquals(1, gol.getYMax());
    }

    @Test
    public void testGetLiving(){
        gol.addCellAt(0, 1);
        gol.addCellAt(0, 2);
        List<Cell> testList = new ArrayList<Cell>();
        testList.add(new Cell(0,1));
        testList.add(new Cell(0,2));
        assertEquals(testList, gol.getLivingList());
    }


}
