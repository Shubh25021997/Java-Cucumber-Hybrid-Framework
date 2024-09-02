package StepDefinitions;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;

import Bindings.DataProvider;
import Bindings.DriverProvider;
import Bindings.ReportProvider;
import interactions.Interactions;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.core.gherkin.Pickle;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.TestCase;


public class Hooks {
	
	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

	/**
	 * Returns RownNumber for a corresponding TestCaseID
	 * 
	 * @param value: value of TestCaseID
	 */
//	public static Integer getrowNumForATestCaseID(String value) {
//		for (Integer rowNum : Application_main.tagNameAsPerSequence().keySet()) {
//			if (TestSuite.getTagName().get(rowNum).equals(value)) {
//				return rowNum;
//			}
//		}
//		return null;
//	}
	
	/**
	 * Before scenario set up
	 * 
	 * @param scenario
	 */
	@Before
	public void beforeScenario(Scenario scenario)  {
		ReportProvider.createReport("HTML_Report.html");
		List<String> tags = (List<String>) scenario.getSourceTagNames();
		ReportProvider.createTest(tags.get(0).replace("@", "") + " " + scenario.getName());
//		ConfigProvider.readExecutionData(getrowNumForATestCaseID(tags.get(0).replace("@", "")));
//		ConfigProvider.readEnvironmentData();
//		ReportProvider.createDetailsNode();
//		DataProvider.instantiateRestObjectsDataMap();
		DataProvider.addGlobalData(DataProvider.STEPNUMBER, String.valueOf(0));
//		DataProvider.addGlobalData(DataProvider.REPLACE_WORKBENCH_IND, DataProvider.FALSE);
//		DataProvider.addGlobalData(DataProvider.NAVIGATED_MENU_LEVEL1, "Level1");
//		DataProvider.addGlobalData(DataProvider.NAVIGATED_MENU_LEVEL2, "Level2");
		DriverProvider.createDriver(DataProvider.getGlobalData(DataProvider.BROWSER));
//		if("Y".equalsIgnoreCase(ConfigProvider.getIsexternaldatasheetaccessible())) {
//			ConfigProvider.readExpectedDataSheet();
//		}
//		ReportProvider.createCapturedDataNode();
//		ReportProvider.createSQLDataNode();
		logger.info("Starting Test Case: {}", tags.get(0).replace("@", "") + " " + scenario.getName());
	}

	/**
	 * Before step setup
	 * 
	 * @param scenario
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws IOException
	 */
	@BeforeStep
	public void beforeStep(Scenario scenario) throws NoSuchFieldException, IllegalAccessException {
		Field dlg = scenario.getClass().getDeclaredField("delegate");
		dlg.setAccessible(true);
		TestCaseState delegate = (TestCaseState) dlg.get(scenario);

		Field tc = delegate.getClass().getDeclaredField("testCase");
		tc.setAccessible(true);
		TestCase testCase = (TestCase) tc.get(delegate);

		Field pckl = testCase.getClass().getDeclaredField("pickle");
		pckl.setAccessible(true);
		Pickle pickle = (Pickle) pckl.get(testCase);

		int stepNumber = Integer.valueOf(DataProvider.getGlobalData(DataProvider.STEPNUMBER));

		ReportProvider.createTestStep(pickle.getSteps().get(stepNumber).getLine(),
				pickle.getSteps().get(stepNumber).getKeyword(), pickle.getSteps().get(stepNumber).getText());
		logger.info("Line-{} Test Step: {}{}", pickle.getSteps().get(stepNumber).getLine(),
				pickle.getSteps().get(stepNumber).getKeyword(), pickle.getSteps().get(stepNumber).getText());

		DataProvider.addGlobalData(DataProvider.TEST_CASE_ERROR, "N.A.");

	}

	/**
	 * After test step tear down
	 * 
	 * @throws IOException
	 */
	@AfterStep
	public void afterStep() {
		DataProvider.addGlobalData(DataProvider.STEPNUMBER,
				String.valueOf(Integer.valueOf(DataProvider.getGlobalData(DataProvider.STEPNUMBER)) + 1));
//		if (ConfigProvider.getDebugRun().equalsIgnoreCase(DataProvider.TRUE))
//			new Interactions().takeScreenShot();
	}

	/**
	 * After scenario tear down
	 * 
	 * @param scenario
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	@After
	public void afterScenario(Scenario scenario) throws NoSuchFieldException, SecurityException,
	IllegalArgumentException, IllegalAccessException, IOException {
//		if (!DataProvider.getValuesFromOutputDataMap().isEmpty()) {
//			ConfigProvider
//					.writeOutputData(getrowNumForATestCaseID(DataProvider.getGlobalData(DataProvider.TEST_CASE_ID)));
//		}
//		ReportProvider.addSQLDataNode();
		if (scenario.isFailed()) {
			Field dlg = scenario.getClass().getDeclaredField("delegate");
			dlg.setAccessible(true);
			TestCaseState delegate = (TestCaseState) dlg.get(scenario);

			Field tc = delegate.getClass().getDeclaredField("stepResults");
			tc.setAccessible(true);
//			ReportProvider.info("Test Case failed due to: "+ result.getThrowable()); can be use to throw Exception but not working.
			ReportProvider.fail(DataProvider.getGlobalData(DataProvider.TEST_CASE_ERROR));

			new Interactions().takeScreenShot();
		}
		DriverProvider.getCurrentDriver().quit();
	}
	
	/**
	 * After suite setup
	 * To clear JVM memory
	 * 
	 */
	@AfterClass
	public void afterClass () {
		System.gc();
	}


}
