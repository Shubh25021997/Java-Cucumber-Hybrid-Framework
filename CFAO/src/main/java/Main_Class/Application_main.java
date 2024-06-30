package Main_Class;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Bindings.Execution;
import Bindings.TimedTask;

public class Application_main {
	
	private static final Logger logger = LoggerFactory.getLogger(Application_main.class); //we need to have slf4j for this (not log4j required) 
	
	static Execution ExecutionClass = new Execution();

	
	public static void main(String[] args) {
//		System.out.println("main class");
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
		
		ExecutionClass.printIntro();
		new Execution().initTestSuite();
		new Execution().run("@FO_001");
		
//		Timer t = new Timer();
//		t.scheduleAtFixedRate(new TimedTask(), 0, 100001);

		
	}

}
