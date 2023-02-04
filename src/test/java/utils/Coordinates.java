package utils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Coordinates {
    private final int x;
    private final int y;
    private final JSONArray array = new JSONArray();

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.array.put(x);
        this.array.put(y);
    }

    public Coordinates(String coordinateText) {
        List<String> coorList = new ArrayList<String>(Arrays.asList(coordinateText.split(",")));
        System.out.println( Integer.parseInt(coorList.get(0).toString()));
        System.out.println( Integer.parseInt(coorList.get(1).toString()));
        this.x = Integer.parseInt(coorList.get(0).toString());
        this.y = Integer.parseInt(coorList.get(1).toString());
        this.array.put(x);
        this.array.put(y);
    }

    public JSONArray get(){
        return this.array;
    }

    public Coordinates(int size){
        Random rand = new Random();
        this.x = rand.nextInt(size);
        this.y = rand.nextInt(size);
        this.array.put(x);
        this.array.put(y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
