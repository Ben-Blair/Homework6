import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ben Blair
 * April 2, 2024
 * Created with OpenAI Chat-GPT
 * This class manages a collection of Tile objects in a graphical application.
 * It allows for the manipulation of Tile objects through various methods
 * that handle adding, drawing, and interacting with tiles through mouse events.
 */

public class TileManager {
    // List to store the Tile objects
    private List<Tile> tiles;

     //Constructs a new TileManager instance.
     //Initializes the list of tiles as an empty ArrayList.

    public TileManager() {
        tiles = new ArrayList<>();
    }

    /**
     * Adds a Tile object to the collection.
     */
    public void addTile(Tile rect) {
        tiles.add(rect);
    }


     // Draws all Tile objects in the collection.

    public void drawAll(Graphics g) {
        for (Tile tile : tiles) {
            tile.draw(g);
        }
    }


     //Finds the topmost Tile that contains the specified (x, y) point.

    private Tile findTile(int x, int y) {
        for (int i = tiles.size() - 1; i >= 0; i--) {
            Tile tile = tiles.get(i);
            if (x >= tile.getX() && x < tile.getX() + tile.getWidth() &&
                    y >= tile.getY() && y < tile.getY() + tile.getHeight()) {
                return tile;
            }
        }
        return null;
    }


    // Moves the topmost Tile that contains the specified (x, y) point to the top of the z-order.

    public void raise(int x, int y) {
        Tile tile = findTile(x, y);
        if (tile != null) {
            tiles.remove(tile);
            tiles.add(tile);
        }
    }


     // Moves the topmost Tile that contains the specified (x, y) point to the bottom of the z-order.

    public void lower(int x, int y) {
        Tile tile = findTile(x, y);
        if (tile != null) {
            tiles.remove(tile);
            tiles.add(0, tile);
        }
    }


     // Deletes the topmost Tile that contains the specified (x, y) point.

    public void delete(int x, int y) {
        Tile tile = findTile(x, y);
        if (tile != null) {
            tiles.remove(tile);
        }
    }

     // Deletes all Tiles that contain the specified (x, y) point.

    public void deleteAll(int x, int y) {
        tiles.removeIf(tile -> x >= tile.getX() && x < tile.getX() + tile.getWidth() &&
                y >= tile.getY() && y < tile.getY() + tile.getHeight());
    }

     // Shuffles the order of Tiles and assigns each Tile a new random position.

    public void shuffle(int width, int height) {
        Collections.shuffle(tiles);
        for (Tile tile : tiles) {
            int newX = (int) (Math.random() * (width - tile.getWidth()));
            int newY = (int) (Math.random() * (height - tile.getHeight()));
            tile.setX(newX);
            tile.setY(newY);
        }
    }

    //*** FOR TESTING PURPOSE ONLY ****//
    //SHOULD USE THIS METHOD ONLY IN J-UNIT TEST CODE
    //DO NOT MODIFY THIS METHOD
    public List<Tile> getTiles() {
        return tiles;
    }
}