# Introduction
This is the test suite utilized Cucumber and Junit to perform automation testing on the pltsci-sdet-assignment 
at Platform Science. The suite took 12 hours of coding to build (close to 14 hours if included planning, documentation, 
and application set up).

> Due to the software under test unable to remove dirt patches after hovering, it's ideal to execute 
> each of the feature file separately and re-launch the application or docker instance after each file to ensure 
> the correct test execution status.

Test cases, requirement linkage and defects can be found in the TracibilityandDefectSheet.xlsx

## Approach

The test suite was built to streamline the process of testing the application, the main idea is to build a custom Postman
for the pltsci-sdet-assignment. The test suite put a heavy focus on code readability and re-usability; This is reflected on
how there is only 7 steps used to build out all the test cases. Each of these step has been simplified so that even 
a business person with no technical background can look at the feature file and start creating test cases for the suite. 
These steps let the user modify the 4 input variable for the application along with two methods that helps with testing (random location generator 
for dirt patches and room scanning function for dirt patches verification).

## Instruction

### Pre-Req

- Docker v.18+
- Java IDE (The test suite was built using IntelliJ, but it should work with any Java IDE out there)
- JDK (preferably 11)
- Git Bash (Optional)

### Set up for IntelliJ

1. Go to the Github repo at: 
2. Pull the project, preferably from the dev branch
