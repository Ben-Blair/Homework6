import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Ben Blair April 5, 2024
 * Used OpenAI ChatGPT
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

    /**
     * Verifies that tiles are added to the end of the list and are stored in the order they were added.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void addTest() { //Example test provided by instructor (uncomment below after you implement addTile method)
        TileManager tileManager = new TileManager();
        Tile tile = new Tile(100, 100, 50, 20, Color.RED);
        Tile tile2 = new Tile(35, 50, 20, 20, Color.blue);
        Tile tile3 = new Tile(45, 170, 30, 90, Color.DARK_GRAY);
        List<Tile> tileList = tileManager.getTiles();

        tileManager.addTile(tile);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // asserts can be run in sequence because tiles were added in sequence
        // if the first test fails then subsequence tests should not be run (no need for assertAll)
        assertEquals(tile, tileList.get(0), "Tiles should be added to the end of the list");
        assertEquals(tile2, tileList.get(1), "Tiles should be added to the end of the list");
        assertEquals(tile3, tileList.get(2), "Tiles should be added to the end of the list");
    }

    /**
     * Checks if the raise operation correctly moves the specified tile to the top of the list.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void raiseTest() {
        TileManager tileManager = new TileManager();
        Tile bottomTile = new Tile(10, 10, 100, 100, Color.RED);
        Tile topTile = new Tile(50, 50, 100, 100, Color.BLUE);
        tileManager.addTile(bottomTile);
        tileManager.addTile(topTile);

        // Point inside both tiles, topTile should be moved on top (already is)
        tileManager.raise(60, 60);
        List<Tile> tileList = tileManager.getTiles();
        assertEquals(topTile, tileList.get(tileList.size() - 1), "Top tile should remain on top after raise");

        // Raising bottom tile
        tileManager.raise(15, 15);
        tileList = tileManager.getTiles();
        assertEquals(bottomTile, tileList.get(tileList.size() - 1), "Bottom tile should be moved to top after raise");
    }

    /**
     * Tests that the delete operation removes the correct tile based on given coordinates.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void deleteTest() {
        TileManager tileManager = new TileManager();
        Tile tile1 = new Tile(10, 10, 100, 100, Color.RED);
        Tile tile2 = new Tile(50, 50, 100, 100, Color.BLUE);
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);

        // Delete tile at a point occupied by tile2 only
        tileManager.delete(60, 60);
        List<Tile> tileList = tileManager.getTiles();
        assertFalse(tileList.contains(tile2), "Tile2 should be deleted from the list");
        assertTrue(tileList.contains(tile1), "Tile1 should remain in the list");
    }

    /**
     * Verifies that the deleteAll operation removes all tiles that overlap the given coordinates.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void deleteAllTest() {
        TileManager tileManager = new TileManager();
        Tile tile1 = new Tile(10, 10, 100, 100, Color.RED);
        Tile tile2 = new Tile(20, 20, 80, 80, Color.GREEN);
        Tile tile3 = new Tile(30, 30, 60, 60, Color.BLUE); // Overlaps with tile1 and tile2
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // Delete all tiles at a point occupied by all three tiles
        tileManager.deleteAll(35, 35);
        List<Tile> tileList = tileManager.getTiles();
        assertTrue(tileList.isEmpty(), "All tiles overlapping at (35, 35) should be deleted");
    }

    /**
     * Ensures that the lower operation correctly moves the specified tile to the bottom of the list.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void lowerTest() {
        TileManager tileManager = new TileManager();
        // Adding three tiles with overlapping areas to test lowering functionality
        Tile tile1 = new Tile(10, 10, 100, 100, Color.RED); // Bottom tile
        Tile tile2 = new Tile(50, 50, 100, 100, Color.GREEN); // Middle tile
        Tile tile3 = new Tile(70, 70, 100, 100, Color.BLUE); // Top tile
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // Point inside all three tiles, should move tile3 to the bottom of the list
        tileManager.lower(75, 75);
        List<Tile> tileList = tileManager.getTiles();
        assertEquals(tile3, tileList.get(0), "Tile3 should be moved to the bottom of the list after lowering");
        assertEquals(tile1, tileList.get(1), "Tile1 should now be in the middle of the list");
        assertEquals(tile2, tileList.get(2), "Tile2 should now be at the top of the list");
    }

    /**
     * Tests the shuffle operation to ensure it randomizes both the order and positions of tiles within bounds.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void shuffleTest() {
        TileManager tileManager = new TileManager();
        int width = 300, height = 200; // Bounds for the shuffle
        // Adding tiles
        for (int i = 0; i < 5; i++) {
            tileManager.addTile(new Tile(i * 10, i * 20, 50, 50, new Color(i * 40, i * 40, i * 40)));
        }

        List<Tile> originalTiles = new ArrayList<>(tileManager.getTiles());
        tileManager.shuffle(width, height);

        List<Tile> shuffledTiles = tileManager.getTiles();
        assertEquals(originalTiles.size(), shuffledTiles.size(), "Shuffle should not change the number of tiles");

        for (Tile tile : shuffledTiles) {
            assertTrue(tile.getX() >= 0 && tile.getX() <= width - tile.getWidth(), "Tile X position should be within bounds after shuffle");
            assertTrue(tile.getY() >= 0 && tile.getY() <= height - tile.getHeight(), "Tile Y position should be within bounds after shuffle");
        }
        assertNotEquals(originalTiles, shuffledTiles, "The order of tiles should be different after shuffle");
    }

    /**
     * Combines multiple operations to test their interactions and effects on the tile list's state.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void combinedOperationsTest() {
        TileManager tileManager = new TileManager();
        // Bounds for the shuffle, ensuring tiles remain visible
        int width = 400, height = 300;

        // Initial setup: adding a series of tiles
        Tile tile1 = new Tile(100, 100, 30, 30, Color.RED);
        Tile tile2 = new Tile(150, 150, 30, 30, Color.GREEN);
        Tile tile3 = new Tile(200, 200, 30, 30, Color.BLUE);
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // Verify initial addition
        assertEquals(List.of(tile1, tile2, tile3), tileManager.getTiles(), "Tiles should be added in order");

        // Raise tile1 to the top
        tileManager.raise(105, 105);
        assertEquals(List.of(tile2, tile3, tile1), tileManager.getTiles(), "Tile1 should be moved to the top");

        // Delete tile2
        tileManager.delete(155, 155);
        assertEquals(List.of(tile3, tile1), tileManager.getTiles(), "Tile2 should be deleted");

        // Shuffle the remaining tiles
        tileManager.shuffle(width, height);
        List<Tile> shuffledTiles = tileManager.getTiles();
        assertTrue(shuffledTiles.containsAll(List.of(tile1, tile3)), "Shuffled tiles should contain tile1 and tile3");
        assertEquals(2, shuffledTiles.size(), "There should be exactly 2 tiles after shuffle");

        // Check that tiles are within bounds after shuffle
        for (Tile tile : shuffledTiles) {
            assertTrue(tile.getX() >= 0 && tile.getX() <= width - tile.getWidth(), "Tile X position should be within bounds after shuffle");
            assertTrue(tile.getY() >= 0 && tile.getY() <= height - tile.getHeight(), "Tile Y position should be within bounds after shuffle");
        }
    }

    /**
     * Verifies that operations targeting coordinates with no tiles do not alter the tile list.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void nonIntersectingCoordinatesOperationsTest() {
        TileManager tileManager = new TileManager();
        // Setting up with three tiles placed away from the top-left corner
        Tile tile1 = new Tile(100, 100, 40, 40, Color.RED);
        Tile tile2 = new Tile(150, 150, 40, 40, Color.GREEN);
        Tile tile3 = new Tile(200, 200, 40, 40, Color.BLUE);
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // Operations on a point not intersecting any tile
        int x = 10, y = 10; // Chosen to not intersect with any of the tiles
        tileManager.raise(x, y); // Attempting to raise a non-existent tile
        tileManager.lower(x, y); // Attempting to lower a non-existent tile
        tileManager.delete(x, y); // Attempting to delete a non-existent tile
        tileManager.deleteAll(x, y); // Attempting to delete all at a non-existent point

        // The list and order of tiles should remain unchanged after the operations
        List<Tile> expectedTiles = List.of(tile1, tile2, tile3);
        List<Tile> actualTiles = tileManager.getTiles();
        assertEquals(expectedTiles, actualTiles, "Tile list and order should remain unchanged after operations on non-intersecting coordinates");
    }

    /**
     * Tests the accuracy of getTiles after various manipulations, ensuring the state reflects changes.
     */
    @Test
    @Timeout(value = 5, unit = SECONDS)
    public void getTilesAfterManipulationsTest() {
        TileManager tileManager = new TileManager();
        // Initial setup with multiple tiles
        Tile tile1 = new Tile(10, 10, 20, 20, Color.RED);
        Tile tile2 = new Tile(30, 30, 20, 20, Color.GREEN);
        Tile tile3 = new Tile(50, 50, 20, 20, Color.BLUE);
        tileManager.addTile(tile1);
        tileManager.addTile(tile2);
        tileManager.addTile(tile3);

        // Perform a series of operations
        tileManager.raise(31, 31); // Raise tile2
        tileManager.delete(51, 51); // Delete tile3
        tileManager.lower(11, 11); // Lower tile1 (now at the bottom after deletion of tile3 and raising of tile2)

        // Get the current state and verify
        List<Tile> currentState = tileManager.getTiles();
        assertEquals(2, currentState.size(), "There should be exactly 2 tiles after the manipulations.");
        assertTrue(currentState.contains(tile1), "Tile1 should still be present.");
        assertTrue(currentState.contains(tile2), "Tile2 should still be present.");
        assertEquals(tile1, currentState.get(0), "Tile1 should be the bottom-most tile after all manipulations.");
        assertEquals(tile2, currentState.get(1), "Tile2 should be the top-most tile after all manipulations.");
    }


}