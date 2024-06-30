package Bindings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.core.cli.Main;

public class Execution {
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); //use of getClass -> non static declaration required. 

	
	public void printIntro() {
		logger.info("==============================================================");
		logger.info("               STARTING TEST SUITE EXECUTION                  ");
		logger.info("==============================================================");
	}
	
	public void initTestSuite() {	
	DriverProvider.chromeDriverSupplier();
	}

	public void run(String tagID) {
        // Specify the arguments to pass to Cucumber
        String[] cucumberArgs = {"--glue", "StepDefinitions", // Specify the package where your step definitions are located
        		"--tags", tagID, 							// Specify the tag of the scenario to run
        		"-m","FeatureFiles" 								// Specify the path to your feature files
        		};
        
//		String[] params = new String[] { "--threads", "1", "-g", "StepDefinitions", "-t",
//				String.join(" or ", tagID), "-m", "FeatureFiles" };
        Main.run(cucumberArgs, Thread.currentThread().getContextClassLoader());
        
	}

}
