# Appium Cucumber Java Automation Framework


## Table of Contents
- [Introduction](#introduction)
- [Features](#key-components-of-appium-cucumber-in-java)
- [Installation](#installation)
- [Folder Structure](#folder-structure)
- [Running Tests](#running-tests-on-terminal)
- [Reporting and Logs](#reporting)
 
## Introduction
Appium Cucumber Java Framework is a mobile test automation framework designed to support Behavior-Driven Development (BDD) for Android applications. It leverages Appium for mobile automation and Cucumber for writing test scenarios in plain English using Gherkin syntax.


 
## Key Components of Appium Cucumber in Java
- **Feature Files: Contain scenarios written in Gherkin syntax to describe application behavior.​**
- **Step Definitions: Java methods that implement the steps outlined in the feature files.​**
- **Test Runner: A class that uses frameworks like JUnit to execute the tests.​**
- **Driver Factory (Appium Driver Setup): Responsible for initializing and managing the AndroidDriver with desired capabilities and configurations.**
- **Hooks (Test Lifecycle Management): Handles setup and teardown operations such as driver initialization, quitting sessions, and screenshot capturing.**
- **Utility Classes: Common reusable components such as Element interaction utilities, Logger utilities, Configuration readers**
- **Gherkin Language: A domain-specific language that uses a set of keywords to define test cases in a human-readable format.**
- **Page Object Model (POM): Encapsulates mobile UI elements and actions into reusable page classes, improving code maintainability and readability.**


 
## Installation
 **Prerequisites:**
 1. **Install Java SE 8 or higher**  
    - Recommended: JDK 11 or above
        - Add Java in System Environment Variables
        - Open System Environment Variables
        - Under System Variables, create: JAVA_HOME → *path/to/your/jdk* (e.g., C:\Program Files\Java\jdk-21)
        - Edit the Path variable and add: %JAVA_HOME%\bin
        - Save and restart terminal
    - Run the following commands in the terminal to confirm that Java is successfully installed: `java -version`
 2. **Install Apache Maven** 
    - Recommended: Maven 3.8+
    - Add Java in System Environment Variables:
        - Open System Environment Variables
        - Under System Variables, create: MAVEN_HOME → *path/to/your/maven* (e.g., C:\Program Files\Maven\apache-maven-3.9.9)
        - Edit the Path variable and add: %MAVEN_HOME%\bin
        - Save and restart terminal
    - Run the following commands in the terminal to confirm that Apache Maven is successfully installed: `mvn -v`
 3. **Install Node**
    - Recommended: v22+
    - Run the following commands in the terminal to confirm that Apache Maven is successfully installed: `node -v`
 4. **Install Appium and uiautomator2**
     - Open terminal and run: npm install -g appium
     - Run the following commands in the terminal to confirm that Appium is successfully installed: `appium -v`	
     - Open terminal and run: appium driver install uiautomator2
     - Run the following commands in the terminal to confirm that uiautomator2 is successfully installed: `appium driver list`	
 5. **Install Android Studio**
    - Download and install Android Studio
    - Search and open Android Studio
    - To create an emulator, go to Projects → More Actions →Virtual Device Manager →Create Virtual Device
 6. **Add Android SDK in System Environment Variables**
    - Open Android Studio
    - Go to Settings → Language and Frameworks → Android SDK and copy Android SDK Location (e.g., C:\Users\JunnelLMiguel\AppData\Local\Android\Sdk)
    - Open System Environment Variables
    - Under System Variables, create: ANDROID_HOME → *Android SDK Location*
    - Edit the Path variable and add: 
        - %ANDROID_HOME%\platform-tools
        - %ANDROID_HOME%\build-tools
    - Save and restart terminal
 7. **Install Eclipse IDE** 
    - Download and install Eclipse IDE for Java Developers
    - Search and Open Eclipse IDE for Java Developers

**Installation Steps:**
#### 1. Clone the Repository to Your Local Machine (Using CMD)
1. Open **Command Prompt**.
2. Navigate to the folder where you want to save the project:
```
cd path\to\your\desired\folder
```
3. Clone the repository using the following command:
```
https://github.com/jmiguelcheq/appium-cucumber-framework-cicd.git
```
#### 2. Open the Cloned Project in Eclipse IDE for Java Developers

1. Open Eclipse IDE for Java Developers
2. Select and open workspace
3. Click File->Open Projects from File System
4. Click Directory to input source and select the project
5. Click **Finish**.
6. Wait for Eclipse IDE to index the project and download dependencies

**Configuration** 
Config properties (src/test/resources/config): Use .properties files to manage different environment URL, Bwroser and Test Data Path.


## Folder Structure
  ```
Main Folder Directory
└── src/test/java
    ├── com.cheq.demo_webshop.factory              # Contains driver factory for browser initialization and element factories
    │                                              # such as DropdownFactory, RadioButtonFactory, and TextLabelFactory
    │                                              # to manage creation of element strategies
    ├── com.cheq.demo_webshop.hooks                # Contains setup and teardown hooks (e.g., @BeforeAll, @AfterEach)
    ├── com.cheq.demo_webshop.listeners            # Includes TestNG/Cucumber listeners for logging, reporting, and event handling
    ├── com.cheq.demo_webshop.manager              # Manages common resources like driver manager or configuration manager
    ├── com.cheq.demo_webshop.services             # Page Object Models (POM) representing api classes
    ├── com.cheq.demo_webshop.pages                # Page Object Models (POM) representing application pages
    │   └── components                             # Reusable UI components (e.g., header, footer, navigation menu)
    ├── com.cheq.demo_webshop.stepdefinitions      # Cucumber step definitions linking Gherkin steps to code logic
    ├── com.cheq.demo_webshop.Strategy             # Strategy design pattern implementation for UI elements
    │   ├── DropdownFactory                        # Strategies for handling dropdown elements
    │   ├── radio_button                           # Strategies for interacting with radio buttons
    │   └── text_label                             # Strategies for verifying or reading text labels
    ├── com.cheq.demo_webshop.testrunner           # Cucumber or TestNG test runners (entry point for executing tests)
    └── com.cheq.demo_webshop.utils                # Utility classes for common helpers (e.g., file reader, wait utils, logger)
└── src/test/resources
    ├── config                                     # Config files (e.g., environment settings, browser config)
    ├── feature                                    # Cucumber .feature files containing Gherkin scenarios
    └── testdata                                   # JSON file containing parameterized test data
                                                   # Used for data-driven testing and dynamic input retrieval
├──logs                                            # Log files generated during test execution
├──pom.xml                                         # Maven Project Object Model (POM) – manages dependencies and build configuration
├── README.md                                      # Project documentation providing an overview and setup instructions.
  ```


## Running Tests on Terminal

Open the Command Prompt, navigate to the project directory, and execute the following command:

To run a specific test tags, use the following command: `mvn clean test -Denv=dev -Dbrowser=chrome "-Dcucumber.filter.tags=@regression" -Dheadless=true"`

## Running Tests on Eclipse IDE for Java Developers
Open your project in Eclipse IDE for Java Developers.

- Navigate to: src/test/java/com/cheq/demo_webshop/testrunner/TestRunner.java
- Before executing, make sure to update the tags inside @CucumberOptions to match the scenarios you intend to run. For example, update **tags="@regression"** to another tag if needed, depending on the scenarios you want to execute.
- After updating the tag, right-click on the TestRunner class → Select Run As → JUnit Test. This will automatically run all scenarios that match the specified tag and feature file(s) defined in the runner configuration.


## Reporting

Open the terminal to generate an allure report, use the following command: `allure generate target/allure-results --single-file --output target/allure-report`

- Navigate to Project folder: /reports/allure-report/
- Right-click on the index.html → Select Open with (Any browsers). This will open the allure report on browser.

