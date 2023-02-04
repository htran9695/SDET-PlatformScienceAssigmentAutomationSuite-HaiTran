Feature: Patches of Dirts
  locations of patches of dirt, also defined by X and Y coordinates identifying
  the bottom left corner of those grid positions.

  Scenario: Verify that patches of dirt can be created
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 90,76         | 0,0          |
    And User defined 2 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [89,75]  | 2      |

  Scenario: Verify that the room can be created and the bot cans hover across all defined tiles
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 0,0          |
    And User defined dirt patches to be created for the application
      | dirtPatches |
      | -1,0         |
      | 2,-2         |
      | -2,-3         |
    And User upload data for the hoover service
    Then User receives error code

  Scenario: Verify that patches of dirt cannot be outside of the room dimensions
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 0,0          |
    And User defined dirt patches to be created for the application
      | dirtPatches |
      | 8,9         |
      | 21,11         |
      | 23,16         |
    And User upload data for the hoover service
    Then User receives error code