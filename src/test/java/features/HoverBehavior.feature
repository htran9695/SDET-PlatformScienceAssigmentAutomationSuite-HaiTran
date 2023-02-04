Feature: Hover Behavior
  Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that
  patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.

  Scenario: Verify that patch(es) of dirt get cleaned when hovered over
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 90,76         | 0,0          |
    And User defined 2 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [89,75]  | 2      |
    And User defined 0 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [89,75]| 0      |