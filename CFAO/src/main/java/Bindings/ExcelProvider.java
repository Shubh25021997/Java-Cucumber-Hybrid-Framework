package Bindings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelProvider {

	/**
	 * Non static method to read excel file
	 * 
	 * @param envDataPath
	 * @param envFileName
	 * @throws InvalidFormatException
	 * @throws IOException
	 */
	public Workbook readExcelWorkBookFile(String envDataPath, String envFileName)
			throws InvalidFormatException, IOException {
		FileInputStream excelFile = new FileInputStream(envDataPath + envFileName + ".xlsx");
		Workbook wb=WorkbookFactory.create(excelFile);
//		excelFile.close();
		return wb;
		
	}
	
	/**
	 * Non static method to open an excel file for writing data in it
	 * 
	 * @param envDataPath
	 * @param envFileName
	 * @throws FileNotFoundException
	 */
	public FileOutputStream writeExcelWorkBookFile(String envDataPath, String envFileName) throws FileNotFoundException {
		FileOutputStream fileOut = new FileOutputStream(envDataPath + envFileName + ".xlsx");
		return fileOut;
	}


}
