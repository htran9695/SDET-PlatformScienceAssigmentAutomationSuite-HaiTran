package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class RoomBuilder {
    private final Coordinates roomDimesion;
    private final Coordinates startingCoordinates;
    private final JSONArray dirtPatches;
    private final String instructions;

    public RoomBuilder(Coordinates roomDimesion, Coordinates startingCoordinates, JSONArray dirtPatches, String instruction) {
        this.roomDimesion = roomDimesion;
        this.startingCoordinates = startingCoordinates;
        this.dirtPatches = dirtPatches;
        this.instructions = instruction;
    }

    public RoomBuilder(Coordinates roomDimesion, Coordinates startingCoordinates, int dirtPatches, String instruction) {
        this.roomDimesion = roomDimesion;
        this.startingCoordinates = startingCoordinates;
        JSONArray randomDirtPatches = new JSONArray();
        for (int a=0; a < dirtPatches; a++){
            randomDirtPatches.put(new Coordinates(5).get());
        }
        this.dirtPatches = randomDirtPatches;
        this.instructions = instruction;
    }

    public String build(){
        return new JSONObject()
                .put("roomSize", roomDimesion.get())
                .put("coords", startingCoordinates.get())
                .put("patches",dirtPatches)
                .put("instructions",instructions)
                .toString();
    }

}
