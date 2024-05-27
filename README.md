# WEB AUTOMATION

* **GOAL**: Provide automated web browsing to validate the web application under test.
* **HOW**: Leverage Selenium web automation, TestNG as the execution test framework, and Maven as the build tool.

## References

* Manual Test Cases Included in Excel Spreadsheet,
  https://docs.google.com/spreadsheets/d/1P_0GLfM-NsX0eS3OBbQM84_a21xcIDYk2iHy7MpFxr4/edit?usp=sharing

### General Architecture

* Selenium v4.20
* Maven Surefire v2.19.1
* WebDriver Manager 5.8.0
* Logger -- Log4J/SLF4J v2.23
* Selenium DevTool CND v4.9.1
* Unit Framework -- TestNG v7.7.1
* Build Tool -- Maven M2E 3.8 and above
* Java -- support JDK 7, 8, 11, 17 & above
* Design -- Page Object Model Implementation
* Report Management -- Extent Reports v2.41.2
* Supported Browser -- Chrome, Firefox, Edge, Safari, etc

### Local: Environment Setup and Build Instructions

* Install Version Control: Install GIT & GIT Bash.
* IDE Setup: Install any IDE like Eclipse, IntelliJ or Visual Studio.
* Install Maven: Install Maven on your local machine and set MAVEN_HOME in the environment variable.
* Install Java: Install Java 8 or above on your local machine and set the environment variable as JAVA_HOME.

### Local Execution Instructions:

* Edit Run/Debug Configure on IDE, Edit Configuration and add TestNG, 
* Select Test Kind and provide Suite file: TestNG.xml file in suite
* Save and Run/Debug
* Executing through CMD (Commands: compile, clean install)
* Check Java: java -version
* Compile: javac -cp ".;path\to\testng-7.4.0.jar;path\to\selenium-java-4.x.x.jar" -d bin src\path\to\your\tests\*.java
* Provide TestNG: java -cp ".;path\to\testng-7.4.0.jar;path\to\selenium-java-4.x.x.jar;bin" org.testng.TestNG testng.xml
* Run Test: java -cp ".;libs\*;bin" org.testng.TestNG testng.xml


### Cloud: Environment Setup and Build Instructions

* Install Version Control: Install GIT & GIT Bash.
* IDE Setup: Install any IDE like Eclipse, IntelliJ or Visual Studio.
* Install Maven: Install Maven on your local machine and set MAVEN_HOME in the environment variable.
* Install Java: Install Java 8 or above on your local machine and set the environment variable as JAVA_HOME.

### Cloud Execution Instructions:
#### Prerequisites
* Jenkins Installation: Ensure Jenkins is installed on your local machine or a server. 
* Java Installation: Java 8 or above installed and configured. 
* Maven Installation: Maven installed and configured. 
* Git Installation: Git installed and configured, path and auth.
* WebDriver Setup: Any Browser - Chrome, Firefox, Edge installed on Cloud Machine.

#### Jenkins Configuration
* Enter the Repository URL and Credentials (if required).
* Provide path of pom.xml (Edit pom file, provide TestNG.xml file if running for single project), 
* If not providing TestNG.xml file externally for running on multiple pipelines or multi-projects, provide path.
* Click Add post-build action > Publish HTML reports. 
* Enter the path to the Extent Reports (e.g. //output/TestResult.html).
* Click Save Configuration. 

#### Jenkins Job Execution
* Click Build Now to run the job manually and ensure everything is configured correctly.
* Configure Build Triggers: Scroll to Build Triggers > Select Poll SCM and set the schedule
* On execution the Maven clean install command, which will run the tests specified in the testng.xml file.
* Logs will be available under output folder, (e.g. //output/logs.log")
