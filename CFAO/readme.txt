------------------------------------------------------

This is a dummy project, developed for learning purpose.

Steps for Logging 
=================
	step 1) log4j-core, log4j-api, log4j-slf4j2-impl add these 3 maven dependencies in POM.xml
	step 2) Create log4j2.xml in resources folder. -> you can configure the way your logs would be displayed
													and where to log only console or also want to log in Log file as well.

Steps for Opening browser
=========================
	1. automatically download chromedriver frome web and store in cache. (2-3 lines of code does this work.)
	2. add chrome prefs & options.arguments.
	3. create object of chromeDriver and give reference of webdriver
	4. return created object. (return type 'webdriver')
*** NOTE: Can use java 8 feature in declaration of method -> when we are having concurrent/parallel sessions/threads running.

Steps for configuring cucumber in java framework
================================================
	1. convert maven project to -> Maven cucumber project
	2. add dependency of cucumber-java, cucumber-TestNG
	3. create a folder under project -> Store feature files in that folder.
	4. create a run method and reference it to main class -> run method will contain (glue feature files to step definitions)
------------------------------------------------------
Use of Getter and Setters in Framework
======================================
	1. Create Setter method 1st and then Getter method.
	2. When we use the setter method anywhere in code, we give input value which will be set to that variable.
	3. When we want to retrieve that value we use getter method, which returns value which we have assigned/set earlier.
	4. It's very simple if you look at syntax, but .. but if you want to retrieve data in another class then
		you've to make method, variables as static & perform next actions.


Cucumber Step definition with parameter syntax
==============================================
	@Given("Login into OTM as '{string}'") 	-> only string inputs will be taken
	@Given("^Login into OTM as '(.*)'$")	-> but if we use (.*) then all type of inputs we can use.
	
DriverProvider.java
===================
	We have used Driver suppliers using Java 8 functionalities, because using simple java methods didn't worked. 
	(THIS BELOW CODE WAS WORKING BUT IT WAS OPENING BROWSER EVEN IF WE DONT GIVE ANY BROWSER NAME IN EXECUTIONSHEET.)

ConfigProvider.java
===================
	Contains methods to read ExecutionData & Configuration data from excel sheet.
	Also getters and setters are used.

Application.java
================
	1.Adds sequenceNum & testCaseIDs from execution details sheet into a Map.
	2.Then we extract sequence numbers and which are in ascending order.(as we have used TreeMap to put sequenceNum & testCaseIDs from excel sheet.)
	3.Lastly we provide TestCaseID to 'Run method' by getting value against key from map.
	
Attaching Screenshot
====================
	Output Type used while attaching screenshot.
	1.Base64: Suitable for embedding in reports, logging, and network transmission.
	2.File: Suitable for local storage, manual inspection, and integration with external systems.

ExtentReports
=============
ExtentSparkReporter -> class responsible for look of feel of report
ExtentReports		-> use to make entries of each test case in report.
ExtentTest			-> Use to provide status (Pass, Fail, skip) to the Test step.
------> EXPLAIN MORE ABOUT IT<------------

Hooks.java
==========
