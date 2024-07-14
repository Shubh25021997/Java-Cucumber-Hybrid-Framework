package Main_Class;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Bindings.Execution;
import Bindings.TimedTask;
import interactions.ConfigProvider;

public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class); //we need to have slf4j for this (not log4j required) 
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
		new Execution().printIntro();
		
		logger.info("Getting test cases for execution");
		Map<Integer, String> tagNameAsPerSequence = new ConfigProvider().readExecutionData();
		logger.info("--------------------------------------------------------------");
		logger.info("Total Test cases: " + tagNameAsPerSequence.keySet().size());
		logger.info("--------------------------------------------------------------");

		new Execution().initTestSuite();
//		new Execution().run("@FO_001"); -> by using hardcoded execution. but below code is used and data is stored in another sheet.

		Set<Integer> sequenceIDs = tagNameAsPerSequence.keySet();
		for (Integer sequenceID : sequenceIDs) {
			String tagID = tagNameAsPerSequence.get(sequenceID);
			tagID = "@" + tagID;
			new Execution().run(tagID);
		}
//		String[] sequenceIDsArray = new String[sequenceIDs.size()];
//		sequenceIDs.toArray(sequenceIDsArray);
//			for(int i=0; i<=sequenceIDsArray.length; i++) {
//			new Execution(fl.testCases, SequenceWiseTagName);
//				String tagID = tagNameAsPerSequence.get(i);
//				new Execution().run(tagID);
//			}
			
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimedTask(), 0, 100001);
		
	}
	

}
