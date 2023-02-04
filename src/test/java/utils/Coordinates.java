package utils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This is the custome coordinates class used to support the generation of the
 * RoomBuilder class
 */
public class Coordinates {
    private final int x;
    private final int y;
    private final JSONArray array = new JSONArray();

    /**
     * Constructor method for custom class coordinates, take in x and y int
     * and populate the JSONArray coordination used for the RoomBuilder class
     * @param x
     * @param y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.array.put(x);
        this.array.put(y);
    }

    /**
     * Constructor method for custom class coordinates, take in a
     * String of coordination e.g 1,2 to populate the coordinates array
     * @param coordinateText
     */
    public Coordinates(String coordinateText) {
        List<String> coorList = new ArrayList<String>(Arrays.asList(coordinateText.split(",")));
        this.x = Integer.parseInt(coorList.get(0).toString());
        this.y = Integer.parseInt(coorList.get(1).toString());
        this.array.put(x);
        this.array.put(y);
    }

    /**
     * Constructor method for custom class coordinates, take in an int for
     * size limit and generate a random combination of coordinate between 0 and size
     * NOTE: This method works best when size is at least 10, anything bellow 10 has
     * a very high chance of duplication for the coordinates being generated
     * @param size
     */
    public Coordinates(int size){
        Random rand = new Random();
        this.x = rand.nextInt(size);
        this.y = rand.nextInt(size);
        this.array.put(x);
        this.array.put(y);
    }

    /**
     * Get method return the coordinate array
     * @return array
     */
    public JSONArray get(){
        return this.array;
    }

    /**
     * This method returns the x of the coordinate
     * @return x
     */
    public int getX(){
        return this.x;
    }

    /**
     * THis method return the y of the coordinate
     * @return y
     */
    public int getY(){
        return this.y;
    }
}
