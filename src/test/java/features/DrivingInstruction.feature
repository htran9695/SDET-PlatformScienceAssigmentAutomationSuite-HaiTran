Feature: Driving Instruction
  driving instructions (as cardinal directions where e.g. N and E mean "go north" and "go
  east" respectively)

  Scenario: Verify that driving instructions can be issued - E
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | E |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [2,3]  | 0      |

  Scenario: Verify that driving instructions can be issued - N
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | N |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [1,4]  | 0      |

  Scenario: Verify that driving instructions can be issued - S
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | S |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [1,2]  | 0      |

  Scenario: Verify that driving instructions can be issued - W
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | W |
    And User upload data for the hoover service
    Then User gets the expected output
      | coords | patches|
      | [0,3]  | 0      |

  Scenario: Verify that only valid driving instruction can be included - Lower/ Upper Case
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | nNsSwWeE |
    And User upload data for the hoover service
    Then User receives error code


  Scenario: Verify that only valid driving instruction can be included - Other letters
    When User define the room dimension and starting position
      | roomDimension | startingCoord|
      | 5,5           | 1,3          |
    And User defined 0 Dirt Patches are randomly created for the application
    And User defined the direction for the hover bot
      |direction  |
      | ABCD |
    And User upload data for the hoover service
    Then User receives error code