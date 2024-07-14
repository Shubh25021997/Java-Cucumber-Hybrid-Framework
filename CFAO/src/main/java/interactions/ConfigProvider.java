package interactions;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import Bindings.DataProvider;
import Bindings.ExcelProvider;

public class ConfigProvider {
	
	private static String columnEnvironment;
	private static String columnApplication;
	private static String ColumnURL;
	private static String columnUsername;
	private static String columnPassword;
	private static String currentExecutionResultsFolderPath;
	public static final String configFileName = "ConfigDetails";
	public static final String configSheetName = "Configuration";
	public static final String executionFileName = "ExecutionDetails";
	public static final String executionSheetName =  "TestSuiteExecution";
	

	private static String Environment = "Environment";
	private static String application = "Application";
	private static String url = "URL";	
	private static String username = "Username";
	private static String password = "Password";
	private static String columnRunInd = "RunInd";
	private static String testSequence = "Sequence";
	private static String browserName = "Browser";
	private static String testCaseID = "TestCaseID";
	
	
	/**
	 * Setter method for environment column
	 * 
	 * @param columnEnvironment
	 */
	public static void setColumnEnvironment(String columnEnvironment) {
		ConfigProvider.columnEnvironment = columnEnvironment;
	}

	/**
	 * Getter method for environment column
	 */
	public static String getColumnEnvironment() {
		return ConfigProvider.columnEnvironment;
	}
	
	//------------

	public static void setColumnApplication(String columnApplication) {
		ConfigProvider.columnApplication = columnApplication;
	}

	public static String getColumnApplication() {
		return ConfigProvider.columnApplication;
	}

	//------------
	
	public static void setColumnURL(String ColumnURL) {
		ConfigProvider.ColumnURL = ColumnURL;
	}
	
	public static String getColumnURL() {
		return ConfigProvider.ColumnURL;
	}

	//==========Way 1 ========== But only works when we have to set & get in same class
//	public String getColumnURL() {
//	return ColumnURL;
//	}
//
//public void setColumnURL(String ColumnURL) {
//	this.ColumnURL = ColumnURL;
//	}
//	public void setColumnURLfinal(String ColumnURL) {
//		setColumnURL(ColumnURL);
//	}
//
//	public String getColumnURLfinal() {
//		return getColumnURL();
//	}
	//=============Way 2 ===============same works for same class only. reason is we store value in 1 object 
	//but when we go to another class we create another object and that object doesn't contain that value
//	public String getColumnURL() {
//	return this.ColumnURL;
//}
//
//public void setColumnURL(String ColumnURL) {
//	this.ColumnURL = ColumnURL;
//}


	//------------
	
	public static void setColumnUsername(String columnUsername) {
		ConfigProvider.columnUsername = columnUsername;
	}

	public static String getColumnUsername() {
		return ConfigProvider.columnUsername;
	}

	//------------
	
	public static void setColumnPassword(String columnPassword) {
		ConfigProvider.columnPassword = columnPassword;
	}
	
	public static String getColumnPassword() {
		return ConfigProvider.columnPassword;
	}
	
	/**
	 * Setter method for CurrentExecutionResults folder path
	 * @param currentExecutionResultsFolderPath : path where the report and execution details for
	 *                          	current execution will be stored
	 */
	public static void setCurrentExecutionResultsFolderPath(String currentExecutionResultsFolderPath) {
		ConfigProvider.currentExecutionResultsFolderPath = currentExecutionResultsFolderPath;
	}
	
	public static String getCurrentExecutionResultsFolderPath() {
		return ConfigProvider.currentExecutionResultsFolderPath;
	}
	

	/**
	 * Method for reading Configurations data
	 * 
	 * @throws IOException
	 * @throws InvalidFormatException
	 */

	public void readConfigData() throws IOException, InvalidFormatException {

	boolean combinationFound = false;
    String configSheetPath = System.getProperty("user.dir") + File.separator + "Configuration" + File.separator ;
    //System.getProperty("user.dir") == returns path where project is located. "C:\Users\ShubhamShinde\eclipseEE-workspace\CFAO"
    
	Sheet sheet = new ExcelProvider()
			.readExcelWorkBookFile(configSheetPath, configFileName)
			.getSheet(configSheetName);
	Row headerRow = sheet.getRow(0);
	
	for (Row row : sheet) {
		if (row.getCell(0).getStringCellValue().equalsIgnoreCase(getColumnEnvironment())
				&& row.getCell(1).getStringCellValue()
						.equalsIgnoreCase(getColumnApplication())) {
//		if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Test")
//				&& row.getCell(1).getStringCellValue()
//						.equalsIgnoreCase("Myntra")) {
			combinationFound = true;

			for (Cell cell : row) {
				DataProvider.addGlobalData(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
						cell.getStringCellValue());
			}
			setColumnURL(DataProvider.getGlobalData(url));
			setColumnUsername(DataProvider.getGlobalData(username));
			setColumnPassword(DataProvider.getGlobalData(password));
			break;
		}
	}
}
	/**
	 * Method for reading Configurations data
	 * @throws IOException 
	 * @throws InvalidFormatException 
	 * returns -> sequenceNum, testCaseID
	 * 
	 */

	public Map<Integer, String> readExecutionData() throws InvalidFormatException, IOException  {
	    String executionSheetPath = System.getProperty("user.dir") + File.separator + "Executions" + File.separator ;

		Sheet sheet = new ExcelProvider()
				.readExcelWorkBookFile(executionSheetPath, executionFileName)
				.getSheet(executionSheetName);
		int testCase = getColumnNumber(sheet, testCaseID, 1);
		int runInd = getColumnNumber(sheet, columnRunInd, 1);
		int Sequence = getColumnNumber(sheet, testSequence, 1);
		int Browser = getColumnNumber(sheet, browserName, 1);
		Map<Integer, String> tagNameMapAsPerTestSequence = new TreeMap<Integer, String>();
		//as we have used TreeMap here only so, here only we sort test cases according to sequence in ascending order.

		for (Row row : sheet) {
			if (row.getRowNum() < 2)
				continue;
			if (row.getCell(runInd) != null && row.getCell(runInd).getStringCellValue().equalsIgnoreCase("y")) {
				String testCaseID = row.getCell(testCase).getStringCellValue();
				Integer sequenceNum = Integer.parseInt(row.getCell(Sequence).getStringCellValue());
				tagNameMapAsPerTestSequence.put(sequenceNum, testCaseID);
				
				String browser = row.getCell(Browser).getStringCellValue();
				DataProvider.addGlobalData(DataProvider.BROWSER , browser);
			}

		}
		return tagNameMapAsPerTestSequence;
	}

	/**
	 * Getter method for column number from sheet
	 * 
	 * @param sheet      : sheet from which column number is to be returned
	 * @param columnName : column name whose number is needed
	 * @param rowNumber  : row number where search is to be done
	 */
	public static int getColumnNumber(Sheet sheet, String columnName, int rowNumber) {
		int columnNum = 0;
		Row column = sheet.getRow(rowNumber);
		for (Cell cell : column) {
			if (cell != null && cell.getStringCellValue().equalsIgnoreCase(columnName)) {
				columnNum = cell.getColumnIndex();
				break;
			}
		}
		return columnNum;
	}


}
