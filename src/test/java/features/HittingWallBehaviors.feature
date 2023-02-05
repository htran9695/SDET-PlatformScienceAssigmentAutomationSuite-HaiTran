Feature: Hitting wall behaviors
  Driving into a wall has no effect (the robot skids in place).

  Scenario: Verify robot driving into wall - North
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 0,0          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | NNNNNNNN |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [0,4]  |        |

  Scenario: Verify robot driving into wall - South
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 3,4          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | SSSSSS |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [3,0]  |        |

  Scenario: Verify robot driving into wall - East
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | EEEEEEE |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [4,3]  |        |

  Scenario: Verify robot driving into wall - West
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 4,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | WWWWWWWW |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [0,3]  |        |

  Scenario: Verify robot driving into wall - Top Right Corner
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 12,18         | 4,5          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [11,17]|     |

