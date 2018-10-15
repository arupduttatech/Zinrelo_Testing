# Zinrelo

All the required dependencies are mentioned in pom.xml

reports/ folder will have the latest extent report

download chromedriver.exe and mention the path location in zinrelo.properties file accordingly

Eclipse IDE, JDK 1.8, Google Chrome, Apache Maven installed and configured is a prerequisite.

## Setting Maven Run Configuration Goals

First of all browse the workspace location, then

clean test -Dtest=classname#methodname (for running a single method)
  
clean test -Dtest=classname (for running a single class)
  
clean test (for running the entire suite)
