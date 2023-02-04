Feature: Testing a REST API
  Users should be able to submit POST requests to a web service

  Scenario: Retrieving default json payload
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined dirt patches to be created for the application
      | dirtPatches |
      | 1,0         |
      | 2,2         |
      | 2,3         |
    And User defined 3 Dirt Patches are randomly created for the application

