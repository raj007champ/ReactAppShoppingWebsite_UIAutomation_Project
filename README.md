# QA Assignment Of desigining E2E Automation Framework of React Based Shopping E-commerce website.

<b>This is a POC of test automation for creating E2E test automation framework that can cover basic test cases 
of all functionalities of React Based Shopping E-commerce website- https://react-shopping-cart-67954.firebaseapp.com/

This is a Maven project that implements a Page Object Model design pattern using Selenium WebDriver with TestNg written in Java and uses TestNg assertion. 

Extent Reports and TestNg have been used to generate the test report.</b>

Please Note- This project can be integrated to Jenkins CI pipeline by creating Jenkins Maven Project and adding the github url of this project.

## Table of Contents
* [Functionality-Covered Test Cases](#functionality)
* [Technologies Used](#Technologies-Used)
* [Page Object Model](#page-object-model)
* [Test Framework](#Test-Framework)
* [Jenkins CI](#Jenkins-CI)
* [How to Run the tests](#How-to-Run-the-tests)
    * [Pre-requisite](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#pre-requisite)
    * [Using Maven](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#1-using-maven)
    * [Using IDE](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#2-using-ide)   
    * [Test Result, Report and Failure Screenshots](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#3-test-result-report-and-failure-screenshots)
    * [Retry Logic of Failed test cases](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#4-retry-logic-of-failed-test-cases-)
    * [Sample report look like this](https://github.com/raj007champ/ReactAppShoppingWebsite_UIAutomation_Project#5-after-executing-the-test-cases-using-maven-or-using-testng-suite-file-sample-extent-report-looks-like-this)

# Functionality
<b>The following test cases of Shopping website has been automated:</b>
  1. Verify that user is on Demo Shop application Landing Page
  2. Verify that user is able to see all 16 products on the page.
  3. Verify that user is able filter Products as per the size.
  4. Verify that user is able to sort Product from Lowest To Highest.
  5. Verify that user is able to sort Product from Highest To Lowest.
  6. Verify that user is able to add products in Cart as per given Quantity.
  7. Verify that user is able to Check out Products.
  8. Verify that user is able to remove products from Cart.
  
# Technologies Used: 
   • Selenium WebDriver - v3.14 (Open Source)  
   • JDK 1.8 (Java Development Kit)   
   • TestNG (Test Unit Framework)   
   • Log4j (logging API)   
   • Maven (Build Automation Tool)   
   • Apache POI API (Read-Write utilities for Excel - Test Data Handling)
   • Eclipse/IntelliJ (Java Editor)     
   • Extent Report
   • Browser - Google Chrome/FF

# Page Object Model
Page Object Model, also known as POM, is a design pattern in Selenium that creates an object repository for storing all web elements. It is useful in reducing code duplication and improves test case maintenance.

In Page Object Model, consider each web page of an application as a class file. Each class file will contain only corresponding web page elements. Using these elements, user can perform operations on the website under test.

### Why Page Object Model?
    1) Helps with easy maintenance.
    2) Helps with reusing code.
    3) Readability and Reliability of scripts
    4) Provides structure to Automation Framework. 
    
# Test Framework
* Selenium is a free (open-source) automated testing framework used to validate web applications across different browsers and platforms.
* TestNG is a testing framework that is inspired by JUnit and NUnit but introducing some new functionalities that make it more powerful and easy to use. All the functional tests are created using TestNg @Test annonations in src/test/java folder.
* TestNg assertions has been used for all the test validations.
* Maven is used as build automation and project management tool. Maven is a POM (project object model) based build automation and project management tool written in Java. Maven is widely used for dependency management in Java. It also provides a predefined folder structure to write the code. We can add different plugins and JARs in our project.
* Extent Reports has been used for reporting and reports are available after every run at following Path- /MiroAssignment/test-output/Extent.html. Report can be opened in any Web Browser to see detailed results of each run.
* IRetryAnalyzer and IAnnotationTransformer interface have been used to implement retry logic for failed test cases. Currently retry count is set to 1.

# Jenkins CI
This Maven Github Project can be integrated to Jenkins CI by using following steps-
1. Install Jenkins on your local server.
2. Run Jenkins server and install follwing plugins- Maven plugins, Git Plugins, TestNg Plugins etc.
3. Create a Maven Type project item from jenkins dashboard, And give the correct github URL of this project in the project configurations and also specify the Maven build goal as clean install
4. Run the Build.
5. Observe the Build result.

*Note- Since, Jenkins can be installed on local server only, Hence it is not possible to show the jenkins integration as part of this POC. *

### Why TestNg with Selenium for framework?
		1) Easy Maintainability and Reusability
		2) Annotations are easy to understand in TestNg
		3) Easy to group test cases in TestNg
		4) Parallel testing is possible in TestNg
		5) TestNg Supports parameterized and dependency tests
		6) HTML Reports are generated by default in TestNg
		5) Selenium and Testng both are Opensource tools with huge support Online.
    
   Alternatives options available in the market for frontend automation: Cucumber with Selenium, Protractor with Jasmin, WebdriverIO, SerenityJs, Cypress etc.
   
## How to Run the tests


## Pre-requisite:
    * JDK 7 and above.
    * Chrome browser or Firefox Browser - latest version installed.
    * Maven is installed in the machine and configured properly.
    * Eclipse IDE or IntelliJ IDEA can be used to run test cases using testng.xml suite files.
  
  
## 1) Using Maven

For Windows OS- Open command prompt and Go the path where the pom.xml is placed and run following command-

*mvn clean install* 

For MacOs- Open the terminal and and Go the path where the pom.xml is placed and run following command-

*mvn clean install* 
	 
## 2) Using IDE
  	Open the cloned project in IDE. Enable Auto-Import for Maven-dependency
  	Run TestNg suite file present on /MiroAssignment using Run as TestNg Suite command. Following suites are created which can be run as per the requirement-
      1. RegressionSuites.xml- This testNg test suite can be used to run all the 17 test cases present in the framework for Product Page and Cart Pages.
      2. ProductPageTestSuite.xml- This testNg test suite can be used to run all the all the test cases of Product page of Shopping cart.
      3. SanitySuite.xml- This testNg test suite can be used to run any individual test case of framework by editing the test case name and class name in the .xml file.

## 3) Test Result, Report and Failure Screenshots
- Extent Reports has one of the best reporting and the test reports that are generated every time we execute the tests. 
- When the project is cloned into Local, test results reporting can be seen if we open **_`index.html`_** from `(/Ziegert-EverEstate/test-output/Extent.html)`.
- Failed testcases Screenshots can be seen if we navigate to following folder `(/Ziegert-EverEstate/FailedTestsScreenshots)`, Screenshot naming convention is the combination of Test Case name + TimeStamp for Eg- **_`testcasename_TimeStamp.png`_** from `(/Ziegert-EverEstate/FailedTestsScreenshots)`.

## 4) Retry Logic of Failed test cases-
  - IRetryAnalyzer and IAnnotationTransformer interface have been used to implement retry logic for failed test cases. Currently max retry count is set to 1.
  - Retry count can be increased by editing **_`maxRetryCount`_** variable present in `(/Ziegert-EverEstate/src/main/java/com/miro/qa/utill/Retry.java)` class.

## 5) After executing the test cases using Maven or Using testNg suite file, Sample Extent report looks like this 
   
![Extent Reports](ExtentReportScreenShot.png?raw=true "Extent Reports")
