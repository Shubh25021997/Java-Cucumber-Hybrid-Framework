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

