package interactions;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import Bindings.DataProvider;
import Bindings.ExcelProvider;

public class configProvider {
	
	private static String columnEnvironment;
	private static String columnApplication;
	private static String ColumnURL;
	private static String columnUsername;
	private static String columnPassword;
	public static final String configFileName = "ConfigDetails";
	public static final String configSheetName = "Configuration";
	public static final String executionFileName = "ExecutionDetails";
	

	private static String Environment = "Environment";
	private static String application = "Application";
	private static String url = "URL";	
	private static String username = "Username";
	private static String password = "Password";

	
	/**
	 * Setter method for environment column
	 * 
	 * @param columnEnvironment
	 */
	public static void setColumnEnvironment(String columnEnvironment) {
		configProvider.columnEnvironment = columnEnvironment;
	}

	/**
	 * Getter method for environment column
	 */
	public static String getColumnEnvironment() {
		return configProvider.columnEnvironment;
	}
	
	//------------

	public static void setColumnApplication(String columnApplication) {
		configProvider.columnApplication = columnApplication;
	}

	public static String getColumnApplication() {
		return configProvider.columnApplication;
	}

	//------------
	
	public static void setColumnURL(String ColumnURL) {
		configProvider.ColumnURL = ColumnURL;
	}
	
	public static String getColumnURL() {
		return configProvider.ColumnURL;
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
	//=============Way 2 ===============same works for sane class only. reason is we store value in 1 object 
	//but when we go to another class we create another object and that object doesnt contain that value
//	public String getColumnURL() {
//	return this.ColumnURL;
//}
//
//public void setColumnURL(String ColumnURL) {
//	this.ColumnURL = ColumnURL;
//}


	//------------
	
	public static void setColumnUsername(String columnUsername) {
		configProvider.columnUsername = columnUsername;
	}

	public static String getColumnUsername() {
		return configProvider.columnUsername;
	}

	//------------
	
	public static void setColumnPassword(String columnPassword) {
		configProvider.columnPassword = columnPassword;
	}
	
	public static String getColumnPassword() {
		return configProvider.columnPassword;
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
    System.out.println("Current working directory in Java : " + configSheetPath);

	Sheet sheet = new ExcelProvider()
			.readExcelWorkBookFile(configSheetPath, configFileName)
			.getSheet(configSheetName);
	Row headerRow = sheet.getRow(0);
	
	for (Row row : sheet) {
//		if (row.getCell(0).getStringCellValue().equalsIgnoreCase(getColumnEnvironment())
//				&& row.getCell(1).getStringCellValue()
//						.equalsIgnoreCase(getColumnApplication())) {
		if (row.getCell(0).getStringCellValue().equalsIgnoreCase("Test")
				&& row.getCell(1).getStringCellValue()
						.equalsIgnoreCase("Myntra")) {
			combinationFound = true;

			for (Cell cell : row) {
				DataProvider.addGlobalData(headerRow.getCell(cell.getColumnIndex()).getStringCellValue(),
						cell.getStringCellValue());
			}
			setColumnURL(DataProvider.getGlobalData(url));
			setColumnUsername(DataProvider.getGlobalData(username));
			setColumnPassword(DataProvider.getGlobalData(password));
//			System.out.println(getColumnURLfinal());
			System.out.println(getColumnURL());
			break;
		}
	}
	System.out.println(getColumnURL());
}

}
