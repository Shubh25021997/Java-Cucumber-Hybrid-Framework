package Bindings;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

import interactions.ConfigProvider;

public class Report {
	
	private static final Logger logger = LoggerFactory.getLogger(Report.class);
	private ExtentReports extent;
	private ExtentSparkReporter reporter;
	private ExtentTest test;
	
	private Map<String, ExtentTest> scenarioMap = new HashMap<>();
	private Map<String, ExtentTest> stepMap = new HashMap<>();	

	private static final String currentScenario = "CURRENTSCENARIO";
	private static final String currentStep = "CURRENTSTEP";
	private static final String passLabel = "PASS";
	private static final String failLabel = "FAIL";
	private static final String skipLabel = "SKIP";

	/**
	 * create extent report for a test execution.
	 * 
	 * @param reportName
	 */
	public void createReport(String reportName) {
		reporter =  new ExtentSparkReporter(ConfigProvider.getCurrentExecutionResultsFolderPath()+ reportName);
		extent = new ExtentReports();
		reporter.config().setDocumentTitle("Test Execution Report");
		reporter.config().setEncoding("utf-8");
		reporter.config().setProtocol(Protocol.HTTPS);
		reporter.config().setReportName("TEST EXECUTION REPORT");
		reporter.config().setTheme(Theme.STANDARD);
		reporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("BROWSER_NAME", DataProvider.getGlobalData(DataProvider.BROWSER));
		logger.info("HTML Reproter initiated. Filename: {}", reportName);
//		extent.createTest("F002 Access");
//		saveReport();
	}

	/**
	 * get the reporter object
	 */
	public ExtentSparkReporter getReporter() {
		return reporter;
	}

	/**
	 * create method for test in report.
	 * @param scenarioName
	 */
	public void createTest(String scenarioName) {
		ExtentTest scenario = extent.createTest(scenarioName);
		scenarioMap.put(currentScenario, scenario);
		saveReport();
	}
	
	/**
	 * getter method for test.
	 */
	public ExtentTest getcurrentTest() {
		return scenarioMap.get(currentScenario);
	}

	/**
	 * create method for test step inside test.
	 * 
	 * @param lineNumber
	 * @param gherkinKeyword
	 * @param testStepName
	 */
	public void createTestStep(int lineNumber, String gherkinKeyword, String testStepName) {
		ExtentTest testStep = getcurrentTest().createNode(
				"Line - " + lineNumber + "<br><b style=\"green\">" + gherkinKeyword + "</b> " + testStepName);
		stepMap.put(currentStep, testStep);
		saveReport();
	}
	
	/**
	 * getter method for test step.
	 */
	public ExtentTest getcurrentTestStep() {
		return stepMap.get(currentStep);
	}
	
	/**
	 * save report.
	 */
	public void saveReport() {
		extent.flush();
	}

	/**
	 * log pass event for test.
	 * 
	 * @param result
	 */
	public void pass(String result) {
		getcurrentTestStep().log(Status.PASS, result);
		saveReport();
	}

	/**
	 * log fail event for test.
	 * 
	 * @param result
	 */
	public void fail(String result) {
		getcurrentTestStep().log(Status.FAIL, result);
		saveReport();
	}

	/**
	 * log skip event for test.
	 * 
	 * @param result
	 */
	public void skip(String result) {
		getcurrentTestStep().log(Status.SKIP, result);
		saveReport();
	}

	/**
	 * log info event for test.
	 * 
	 * @param result
	 */
	public void info(String result) {
		getcurrentTestStep().log(Status.INFO, result);
		saveReport();
	}

	/**
	 * take screenshot and attach to report
	 * 
	 * @throws IOException
	 */
	public void attachScreenshot(String Path) {
		getcurrentTestStep().info("ScreenShot: ", MediaEntityBuilder.createScreenCaptureFromBase64String(Path).build());
		saveReport();
	}


}
