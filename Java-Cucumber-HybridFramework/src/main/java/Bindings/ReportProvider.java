package Bindings;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ReportProvider {
	
	private static Map<String, Report> reportMap = new HashMap<>();
	public static String reportObject;
//	private ReportProvider() {
//	}
	
	/**
	 * Getter for Report
	 * @return Report
	 */
	public static Report getReporter() {
		return reportMap.get(reportObject);
	}

	/**
	 * create extent report for a test execution.
	 * @param reportName
	 */
	public static void createReport(String reportName) {
		if(!reportMap.containsKey(reportObject)) {
			reportMap.put(reportObject, new Report());
			getReporter().createReport(reportName);
		}
	}
	
	/**
	 * create method for test in report.
	 * @param scenarioName
	 */
	public static void createTest(String scenarioName) {
		getReporter().createTest(scenarioName);
	}

	/**
	 * create method for test step inside test.
	 * 
	 * @param lineNumber
	 * @param gherkinKeyword
	 * @param testStepName
	 */
	public static void createTestStep(int lineNumber, String gherkinKeyword, String testStepName) {
		getReporter().createTestStep(lineNumber, gherkinKeyword, testStepName);
	}

	/**
	 * log pass event for test.
	 * 
	 * @param result
	 */
	public static void pass(String result) {
		getReporter().pass(result);
	}

	/**
	 * log fail event for test.
	 * 
	 * @param result
	 */
	public static void fail(String result) {
		getReporter().fail(result);
	}

	/**
	 * log skip event for test.
	 * 
	 * @param result
	 */
	public static void skip(String result) {
		getReporter().skip(result);
	}

	/**
	 * log info event for test.
	 * 
	 * @param result
	 */
	public static void info(String result) {
		getReporter().info(result);
//		getcurrentTestStep().log(Status.INFO, result);
//		saveReport();
	}

	/**
	 * save report.
	 */
	private static void saveReport() {
		getReporter().saveReport();
	}

	/**
	 * take screenshot and attach to report
	 * 
	 * @throws IOException
	 */
	public static void attachScreenshot(String Path) throws IOException {
		getReporter().attachScreenshot(Path);
//		getcurrentTestStep().info("Screenshot: ",
//				MediaEntityBuilder.createScreenCaptureFromBase64String(location).build());
//		saveReport();
	}


}
