package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.Coordinates;
import utils.HttpBuilder;
import utils.RoomBuilder;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Assignment_StepDefinition {
    HttpBuilder builder;
    JSONObject output;
    Coordinates roomDimension;
    Coordinates startingCoord;
    JSONArray dirtPatches = new JSONArray();
    int numberOfRandomDirtPatches = 0;
    RoomBuilder room;
    private final static Logger logger = Logger.getLogger(Assignment_StepDefinition.class);



    public Assignment_StepDefinition() throws IOException {
        this.builder = new HttpBuilder("localhost:8080/v1/cleaning-sessions", "http");
    }

    @When("User define the room dimension and starting position")
    public void userEnterRoomDimensionAndStartingPosition(DataTable roomSpec){
        List<Map<String,String>> data = roomSpec.asMaps(String.class,String.class);
        this.roomDimension = new Coordinates(data.get(0).get("roomDimension"));
        this.startingCoord = new Coordinates(data.get(0).get("startingCoord"));
    }

    @And("User defined {int} Dirt Patches are randomly created for the application")
    public void dirtPatchesAreRandomlyCreatedForTheApllication(int numberOfRandomDirstPatches) throws IOException {
        this.numberOfRandomDirtPatches = numberOfRandomDirstPatches;
    }

    @And("User defined dirt patches to be created for the application")
    public void userDefinedDirtPatcToBeCreatedForTheApplication(DataTable dirtPatches){
        JSONArray dirtPatch;
        List<Map<String, String>> data = dirtPatches.asMaps(String.class, String.class);
        for (int a =0;a<data.size();a++) {
            dirtPatch = new Coordinates(data.get(a).get("dirtPatches")).get();
            this.dirtPatches.put(dirtPatch);
        }
    }

    @When("User upload default data for the hoover service")
    public void userUploadDefaultDataForTheHooverService () throws IOException {
        if (this.numberOfRandomDirtPatches != 0) {
            this.room = new RoomBuilder(roomDimension, startingCoord, numberOfRandomDirtPatches, "NNESEESWNWW");
        } else {
            this.room = new RoomBuilder(roomDimension, startingCoord, dirtPatches, "NNESEESWNWW");
        }
        String projectString = room.build();
        output = builder.sendRequest("post", "", projectString);
    }
}
