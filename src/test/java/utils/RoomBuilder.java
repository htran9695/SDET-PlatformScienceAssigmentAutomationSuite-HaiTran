package utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This is the custom class uses to build the request body for the application
 */
public class RoomBuilder {
    private final Coordinates roomDimension;
    private final Coordinates startingCoordinates;
    private final JSONArray dirtPatches;
    private final String instructions;


    /**
     * This constructor method takes in roomDimension, startingCoordinates
     * dirtPatches and instruction for the hover bot and store it
     * @param roomDimension Coordinate that defined the dimension of the room
     * @param startingCoordinates Coordinate that defined the starting coordinate of the hover bot
     * @param dirtPatches A list of coordinates that define the position of the dirt patches
     * @param instruction A String that contains letters "N","S","E","W" that define the directions of
     *                    the hover bot
     */
    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, JSONArray dirtPatches, String instruction) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = dirtPatches;
        this.instructions = instruction;
    }

    /**
     * This constructor method takes in roomDimension, startingCoordinates and instruction
     * but generate a random number of dirt patches based on the int defined by the user
     * @param roomDimension Coordinate that defined the dimension of the room
     * @param startingCoordinates Coordinate that defined the starting coordinate of the hover bot
     * @param dirtPatches Integer that defined the number of random dirt patches that get generated
     * @param instruction A String that contains letters "N","S","E","W" that define the directions of
     *                    the hover bot
     */
    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, int dirtPatches, String instruction) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = generateRandomDirtPatches(dirtPatches);
        this.instructions = instruction;
    }

    /**
     * This constructor method takes in roomDimension and startingCoordinates
     * but generate a random number of dirt patches based on the int defined by the user
     * and instruction is to automatically advance to the top right corner of the room
     * @param roomDimension Coordinate that defined the dimension of the room
     * @param startingCoordinates Coordinate that defined the starting coordinate of the hover bot
     * @param dirtPatches Integer that defined the number of random dirt patches that get generated
     */
    public RoomBuilder(Coordinates roomDimension,Coordinates startingCoordinates, int dirtPatches){
        this.roomDimension = roomDimension;
        this.dirtPatches = generateRandomDirtPatches(dirtPatches);
        this.startingCoordinates = startingCoordinates;
        this.instructions = generateInstruction();
    }

    /**
     * This constructor method takes in roomDimension, startingCoordinate and dirPatches
     * coordinates and generate the instruction to advance to the top right corner
     * of the room
     * @param roomDimension Coordinate that defined the dimension of the room
     * @param startingCoordinates Coordinate that defined the starting coordinate of the hover bot
     * @param dirtPatches A list of coordinates that define the position of the dirt patches
     */
    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, JSONArray dirtPatches) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = dirtPatches;
        this.instructions = generateInstruction();
    }

    /**
     * Method to generate random number coordinates for dirt patches based
     * on the input int
     * @param dirtPatches Integer that defined the number of random dirt patches that get generated
     * @return randomDirtPatches JSONArray of dirt patches based on the number of int defined
     */
    private JSONArray generateRandomDirtPatches(int dirtPatches){
        JSONArray randomDirtPatches = new JSONArray();
        for (int a=0; a < dirtPatches; a++){
            randomDirtPatches.put(new Coordinates(5).get());
        }
        return randomDirtPatches;
    }

    /**
     * This method is used mainly to scan the room generated and assumed that
     * the default hovering location is at 0,0.
     * This will go from the starting position 0,0 moving right (E) to x=max
     * then move up (N) and move left (W) until x=0. The process will repeat until
     * y get to y-max for odd y-max and to y-max - 1 for even y-max. The function
     * also assumed that since the starting position is always at 0,0 even line
     * will have the bot go right and odd line will have the bot go left
     * NOTE: Due to the nature of the program (coordinates does not change when get
     * to the x/y limits). The method can also be used to move to the top right of the
     * room.
     * @return steps definition that guide the hover bot to the top right corner of the room
     */
    private String generateInstruction(){
        int totalTiles = (this.roomDimension.getX() * this.roomDimension.getY()) - 1;
        StringBuilder steps = new StringBuilder("E");
        int currentx = 0;
        int currenty = 0;
        for(int a=0;a<totalTiles;a++) {
            if (currentx <= roomDimension.getX() && currenty % 2 == 0 && currenty != this.roomDimension.getY()) {
                steps.append("E");
                currentx++;
            }
            if (currentx == roomDimension.getX() - 1 || currentx == 0 && currenty % 2 != 0 && currenty != this.roomDimension.getY()) {
                steps.append("N");
                currenty++;
            }
            if (currenty % 2 != 0 && currenty != this.roomDimension.getY()) {
                steps.append("W");
                currentx--;
            }
            if (this.roomDimension.getY() % 2 == 0) {
                if (currenty == this.roomDimension.getY() - 1) {
                    System.out.println(StringUtils.chop(steps.toString()));
                    return StringUtils.chop(steps.toString());
                }
            } else {
                if (currenty == this.roomDimension.getY()) {
                    System.out.println(StringUtils.chop(steps.toString()));
                    return StringUtils.chop(steps.toString());
                }
            }
        }
        System.out.println(steps);
        return steps.toString();
    }

    /**
     * Build the JSON request based on roomDimension, startingCoordinates
     * dirtPatches and instructions
      * @return JSON Request Body
     */
    public String build(){
        return new JSONObject()
                .put("roomSize", roomDimension.get())
                .put("coords", startingCoordinates.get())
                .put("patches",dirtPatches)
                .put("instructions",instructions)
                .toString();
    }


}
