package utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoomBuilder {
    private final Coordinates roomDimension;
    private final Coordinates startingCoordinates;
    private final JSONArray dirtPatches;
    private final String instructions;

    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, JSONArray dirtPatches, String instruction) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = dirtPatches;
        this.instructions = instruction;
    }

    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, int dirtPatches, String instruction) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = generateRandomDirtPatches(dirtPatches);
        this.instructions = instruction;
    }

    public RoomBuilder(Coordinates roomDimension,Coordinates startingCoordinates, int dirtPatches){
        this.roomDimension = roomDimension;
        this.dirtPatches = generateRandomDirtPatches(dirtPatches);
        this.startingCoordinates = startingCoordinates;
        this.instructions = generateInstruction();
    }

    public RoomBuilder(Coordinates roomDimension, Coordinates startingCoordinates, JSONArray dirtPatches) {
        this.roomDimension = roomDimension;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = dirtPatches;
        this.instructions = generateInstruction();
    }

    private JSONArray generateRandomDirtPatches(int dirtPatches){
        JSONArray randomDirtPatches = new JSONArray();
        for (int a=0; a < dirtPatches; a++){
            randomDirtPatches.put(new Coordinates(5).get());
        }
        return randomDirtPatches;
    }

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
