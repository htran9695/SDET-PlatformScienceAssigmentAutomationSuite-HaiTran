Feature: Service Specification
  The goal of the service is to take the room dimensions, the locations of the dirt patches, the hoover location
  and the driving instructions as input and to then output the following:
  ● The final hoover position (X, Y)
  ● The number of patches of dirt the robot cleaned up
  Your goal is to verify whether the provided implementation behaves according to specification.

  Scenario: Retrieving default json payload
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined dirt patches to be created for the application
      | dirtPatches |
      | 1,0         |
      | 2,2         |
      | 2,3         |
    And User defined the direction for the hover bot
      |direction  |
      |NNESEESWNWW|
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [1,3]  | 1      |