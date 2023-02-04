package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
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
    JSONObject responseBody;
    Coordinates roomDimension = new Coordinates(5,5);
    Coordinates startingCoord = new Coordinates(0,0);
    JSONArray dirtPatches = new JSONArray();
    String instruction = "";
    int numberOfRandomDirtPatches = 0;
    RoomBuilder room;



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
    public void userDefinedDirtPatchesToBeCreatedForTheApplication(DataTable dirtPatches){
        JSONArray dirtPatch;
        List<Map<String, String>> data = dirtPatches.asMaps(String.class, String.class);
        for (int a =0;a<data.size();a++) {
            dirtPatch = new Coordinates(data.get(a).get("dirtPatches")).get();
            this.dirtPatches.put(dirtPatch);
        }
    }

    @And("User defined the direction for the hover bot")
    public void userDefinedTheDirectionForTheHoverBot(DataTable direction){
        List<Map<String, String>> data = direction.asMaps(String.class, String.class);
        this.instruction = data.get(0).get("direction");
    }

    @And("User upload data for the hoover service")
    public void userUploadDefaultDataForTheHooverService () throws IOException {
        if (this.numberOfRandomDirtPatches != 0) {
            this.room = new RoomBuilder(roomDimension, startingCoord, numberOfRandomDirtPatches, instruction);
        } else {
            this.room = new RoomBuilder(roomDimension, startingCoord, dirtPatches, instruction);
        }
        String projectString = room.build();
        output = builder.sendRequest("post", "", projectString);
        this.responseBody = new JSONObject(output.getString("response body"));
    }

    @Then("User gets the default output")
    public void userGetsTheDefaultOutput(){
        Assert.assertTrue(this.responseBody.getJSONArray("coords").toString().equals("[1,3]"));
        Assert.assertTrue(this.responseBody.getInt("patches") == 1);
    }
}
