Feature: Room Dimensions
  Room dimensions as X and Y coordinates, identifying the top right corner of the room rectangle.
  This room is divided up in a grid based on these dimensions;
  a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions.
  The bottom left corner is the point of origin for our coordinate system, so as the room contains
  all coordinates its bottom left corner is defined by X: 0 and Y: 0.

  Scenario: Verify that the room can be created and the bot cans hover across all defined tiles
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 0,0          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [4,4]  | 0      |

  Scenario: Verify negative coordinates for room creation
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | -5,5           | 0,0          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User receives error code