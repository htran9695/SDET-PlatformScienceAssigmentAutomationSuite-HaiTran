Feature: Hover Position
  an initial hoover position (X and Y coordinates like patches of dirt)

  Scenario: Verify that initial hover position can be set up
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      |None |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [1,3]  | 0      |

  Scenario: Verify negative coordinates for initial hovering position - x equal -1
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | -1,-5        |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      |None |
    And User upload data for the hoover service
    Then User receives error code

  Scenario: Verify negative coordinates for initial hovering position - x less than -1
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | -2,-5        |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      |None |
    And User upload data for the hoover service
    Then User receives error code