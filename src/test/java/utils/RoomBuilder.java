package utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomBuilder {
    private final Coordinates roomDimension;
    private final Coordinates startingCoordinates;
    private final JSONArray dirtPatches;
    private final String instructions;


    /**
     * This constructor method takes in roomDimension, startingCoordinates
     * dirtPatches and instruction for the hover bot and store it
     * @param roomDimension
     * @param startingCoordinates
     * @param dirtPatches
     * @param instruction
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
     * @param roomDimension
     * @param startingCoordinates
     * @param dirtPatches
     * @param instruction
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
     * @param roomDimension
     * @param startingCoordinates
     * @param dirtPatches
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
     * @param roomDimension
     * @param startingCoordinates
     * @param dirtPatches
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
     * @param dirtPatches
     * @return randomDirtPatches
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
     *
     * This will go from the starting position 0,0 moving right (E) to x=max
     * then move up (N) and move left (W) until x=0. The process will repeat until
     * y get to y-max for odd y-max and to y-max - 1 for even y-max. The function
     * also assumed that since the starting position is always at 0,0 even line
     * will have the bot go right and odd line will have the bot go left
     *
     * NOTE: Due to the nature of the program (coordinates does not change when get
     * to the x/y limits). The method can also be used to move to the top right of the
     * room.
     * @return steps
     */
    private String generateInstruction(){
        int totalTiles = (this.roomDimension.getX() * this.roomDimension.getY()) - 1;
        String steps ="E";
        int currentx = 0;
        int currenty = 0;
        for(int a=0;a<totalTiles;a++) {
            if (currentx <= roomDimension.getX() && currenty % 2 == 0 && currenty != this.roomDimension.getY()) {
                steps += "E";
                currentx++;
            }
            if (currentx == roomDimension.getX() - 1 || currentx == 0 && currenty % 2 != 0 && currenty != this.roomDimension.getY()) {
                steps += "N";
                currenty++;
            }
            if (currenty % 2 != 0 && currenty != this.roomDimension.getY()) {
                steps += "W";
                currentx--;
            }
            if (this.roomDimension.getY() % 2 == 0) {
                if (currenty == this.roomDimension.getY() - 1) {
                    System.out.println(StringUtils.chop(steps));
                    return StringUtils.chop(steps);
                }
            } else {
                if (currenty == this.roomDimension.getY()) {
                    System.out.println(StringUtils.chop(steps));
                    return StringUtils.chop(steps);
                }
            }
        }
        System.out.println(steps);
        return steps;
    }

    /**
     * Build the JSON request based on roomDimension, startingCoordinates
     * dirtPataches and instructions
      * @return JSON Request Body
     */
    public String build(){
        System.out.println(new JSONObject()
                .put("roomSize", roomDimension.get())
                .put("coords", startingCoordinates.get())
                .put("patches",dirtPatches)
                .put("instructions",instructions)
                .toString());
        return new JSONObject()
                .put("roomSize", roomDimension.get())
                .put("coords", startingCoordinates.get())
                .put("patches",dirtPatches)
                .put("instructions",instructions)
                .toString();
    }


}
