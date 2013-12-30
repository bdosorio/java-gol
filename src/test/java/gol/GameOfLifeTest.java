package gol;

import org.junit.Before;
import org.junit.Test;

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
    public void testIsCellAt(){
        gol.addCell(new Cell(2,3));
        assertTrue(gol.isCellAt(2,3));
    }

    @Test
    public void testAddCell(){
        gol.addCell(new Cell(2,3));
        assertEquals(1, gol.getLivingCells());
    }

    @Test
    public void testOneStarvation(){
        gol.addCell(new Cell(2,3));
        gol.step();
        assertFalse(gol.isCellAt(2, 3));
    }

    @Test
    public void testCellStarvationWithNeighbor(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.step();
        assertFalse(gol.isCellAt(2, 3));
    }

    @Test
    public void testCellLives(){
        gol.addCell(new Cell(2,3));
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(2,2));
        gol.step();
        assertTrue(gol.isCellAt(2, 3));
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
        assertFalse(gol.isCellAt(2, 3));
    }

    @Test
    public void testReproduction(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.step();
        assertTrue(gol.isCellAt(2,3));
    }

    @Test
    public void testNoReproduction(){
        gol.addCell(new Cell(2,4));
        gol.addCell(new Cell(1,3));
        gol.addCell(new Cell(3,3));
        gol.addCell(new Cell(2,2));
        gol.step();
        assertFalse(gol.isCellAt(2, 3));
    }

    @Test
    public void testGetDeadList(){
        gol.addCell(new Cell(1,1));
        gol.addCell(new Cell(3,3));
        assertEquals(7, gol.getDeadList().size());
    }
}
