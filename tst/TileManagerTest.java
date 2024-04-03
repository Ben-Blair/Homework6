import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.List;

/**
 * Add comments
 */
public class TileManagerTest {
    //MUST PROPERLY TEST ALL PUBLIC METHODS (except drawAll) in TileManager class
    //MUST CALL getTiles method to test the internal state of tiles in TileManager class
    //MUST RUN TEST WITH COVERAGE and SHOW minimum of 85% COVERAGE for TileManager to get the full credit in testing

    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void constructorTest() { //Example test provided by the instructor
        TileManager tileManager = new TileManager();
        List<Tile> tileList = tileManager.getTiles(); //use getTiles method to get the state of your tiles
        assertTrue(tileList.isEmpty(), "The constructor should initialize an empty list");
    }

    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void addTest() { //Example test provided by instructor (uncomment below after you implement addTile method)
//        TileManager tileManager = new TileManager();
//        Tile tile = new Tile(100, 100, 50, 20, Color.RED);
//        Tile tile2 = new Tile(35, 50, 20, 20, Color.blue);
//        Tile tile3 = new Tile(45, 170, 30, 90, Color.DARK_GRAY);
//        List<Tile> tileList = tileManager.getTiles();
//
//        tileManager.addTile(tile);
//        tileManager.addTile(tile2);
//        tileManager.addTile(tile3);
//
//        // asserts can be run in sequence because tiles were added in sequence
//        // if the first test fails then subsequence tests should not be run (no need for assertAll)
//        assertEquals(tile, tileList.get(0), "Tiles should be added to the end of the list");
//        assertEquals(tile2, tileList.get(1), "Tiles should be added to the end of the list");
//        assertEquals(tile3, tileList.get(2), "Tiles should be added to the end of the list");
    }

    /**
     * Add comments
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void test1() {
        //Implement your code here
    }

    /**
     * Add comments
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void test2() {
        //Implement your code here
    }

    //ADD MORE TESTS HERE
}