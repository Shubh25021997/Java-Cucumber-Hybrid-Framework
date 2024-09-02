package Bindings;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.core.cli.Main;

public class Execution {
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); //use of getClass -> non static declaration required. 

//	public Execution(Set<Integer> testCases, Map<Integer, String> SequenceWiseTagName) {
//		this.testCases = testCases;
//		this.SequenceWiseTagName = SequenceWiseTagName;
//	} we can use constructor as well to get the values.

	
	public void printIntro() {
		logger.info("                                                              ");
		logger.info("                               %%%                            ");
		logger.info("                             %%%%%%%                          ");
		logger.info("                          %%%%%%%%%%%%                        ");
		logger.info("                        %%%%%%%%%%%%%%  %                     ");
		logger.info("                     %%%%%%%%%%%%%%%   %%%%%                  ");
		logger.info("                   %%%%%%%%%%%%%%%   %%%%%%%%%                ");
		logger.info("                %%%%%%%%%%%%%%%    %%%%%%%%%%%%%              ");
		logger.info("               %%%%%%%%%%%%%%       %%%%%%%%%%%%%%            ");
		logger.info("              %%%%%%%%%%%%             %%%%%%%%%%%%           ");
		logger.info("             %%%%%%%%%%%%               %%%%%%%%%%%%          ");
		logger.info("             %%%%%%%%%%%                 %%%%%%%%%%%          ");
		logger.info("             %%%%%%%%%%%                 %%%%%%%%%%%          ");
		logger.info("             %%%%%%%%%%%%               %%%%%%%%%%%%          ");
		logger.info("              %%%%%%%%%%%%%           %%%%%%%%%%%%%           ");
		logger.info("               %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            ");
		logger.info("                 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%              ");
		logger.info("                   %%%%%%%%%%%%%%%%%%%%%%%%%%%                ");
		logger.info("                      %%%%%%%%%%%%%%%%%%%%%                   ");
		logger.info("                            %%%%%%%%%                         ");
		logger.info("");
		logger.info("==============================================================");
		logger.info("               STARTING TEST SUITE EXECUTION                  ");
		logger.info("==============================================================");
	}
	
	public void initTestSuite() {	
//	DriverProvider.chromeDriverSupplier();
//		DriverProvider.createDriver(DataProvider.getGlobalData(DataProvider.BROWSER));
		new FolderStructureProvider().createFolderStructure();
//		ReportProvider.createReport("HTML_Report");
	}

	public void run(String tagID) {
        // Specify the arguments to pass to Cucumber
        String[] cucumberArgs = {"--glue", "StepDefinitions", // Specify the package where your step definitions are located
        		"--tags", tagID, 							// Specify the tag of the scenario to run
        		"-m","FeatureFiles" 								// Specify the path to your feature files
        		};
		logger.info("Starting Test Case Execution");
		logger.info("Starting Test Case: {}", tagID.replace("@", ""));
//		String[] params = new String[] { "--threads", "1", "-g", "StepDefinitions", "-t",
//				String.join(" or ", tagID), "-m", "FeatureFiles" };
        Main.run(cucumberArgs, Thread.currentThread().getContextClassLoader());
        
	}

}
