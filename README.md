# Introduction
This is the test suite utilized Cucumber and Junit to perform automation testing on the pltsci-sdet-assignment 
at Platform Science. The suite took 12 hours of coding to build (close to 14 hours if included planning, documentation, 
and application set up).

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

### Set up the project and environment

1. Go to the GitHub repo at: https://github.com/htran9695/SDET-PlatformScienceAssigmentAutomationSuite-HaiTran.git
2. Clone the project
3. There are two branches, ideally you want to be on master
4. Build the project, depends on the IDE you used, here are a couple reference link:
- Setup for Eclipse: [Eclipse](https://www.lagomframework.com/documentation/1.6.x/java/EclipseMavenInt.html)
- Setup for IntelliJ: [IntelliJ](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/importing-a-project/)
5. With the pltsci-sdet-assignment application running, run the ServiceSpecification feature and verify that output matches
with what outlined in the challenge pdf
>If you follow the instruction in the PDF and the docker gives you a not found run.sh 
> add this to the end of the DockerFile helps 
> - CMD ["bash", "run.sh"]

### Execute the test suite and reading the result
The test suite can be run in two ways: individual feature or as a suite using CucumberRunner. Reference on how to run 
Cucumber/Junit test can be found here:
- [Eclipse-Cucumber](https://www.way2automation.com/cucumber-setup-in-eclipse/)
- [IntelliJ-Cucumber](https://www.jetbrains.com/idea/guide/tutorials/working-with-maven/importing-a-project/)
> Due to the software under test unable to remove dirt patches after hovering, it's ideal to execute
> each of the feature file separately and re-launch the application or docker instance after each file to ensure
> the correct test execution status.

The TracibilityandDefectSheet contains all the features derived from the requirements given by the pdf. From there it broke 
each requirement into a scenario. When a scenario failed, there will be a defect associated with it.