package Bindings;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import interactions.ConfigProvider;

public class FolderStructureProvider {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
/**
 * Method to create folder structure for each test execution
 */
	public void createFolderStructure() {
	    String executionSheetPath = System.getProperty("user.dir") + File.separator + "Executions" + File.separator + "ExecutionDetails.xlsx" ;
		String testResultsFolderPath = System.getProperty("user.dir") + File.separator + "TestResults" + File.separator ;
		
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date Date = new Date();
			String dirName = dateFormat.format(Date);
		
		String timeStampFolderPath = System.getProperty("user.dir") + File.separator + "TestResults" + File.separator 
				+ dirName + File.separator ;
		
			try {
				FileUtils.forceMkdir(new File(timeStampFolderPath));
				FileUtils.copyFileToDirectory(new File(executionSheetPath), new File(timeStampFolderPath));
			//as we can see in above code FileUtils method needs input in File format so we have used casting to convert from string to File.
			} catch (IOException e) {
				logger.error("Failed to create execution folder structure", e);
				e.printStackTrace();
			}
			ConfigProvider.setCurrentExecutionResultsFolderPath(timeStampFolderPath);
	}

}
