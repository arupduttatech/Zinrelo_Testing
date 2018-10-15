# Project Zinrelo

All the required dependencies are mentioned in pom.xml

reports/ folder will have the latest extent report

Eclipse IDE, JDK 1.8, Google Chrome, Apache Maven installed and configured is a prerequisite.

## Steps

1. Clone the git repository into your eclipse IDE
2. Import the project as existing Maven projects
3. Download all the pom.xml dependencies by Right Click on Project Name -> Maven -> Update Maven Projects
4. Build and Clean the project
5. Browse the project in Run Configurations and set the Maven Goal accordingly
6. After the execution ends, reports/ folder will be generated with the latest extent report

### Setting Maven Run Configuration Goals

First of all browse the workspace location, then

clean test -Dtest=classname#methodname (for running a single method)
  
clean test -Dtest=classname (for running a single class)
  
clean test (for running the entire suite)
