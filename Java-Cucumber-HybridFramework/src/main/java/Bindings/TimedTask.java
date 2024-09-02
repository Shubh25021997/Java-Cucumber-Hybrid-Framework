package Bindings;

import java.util.TimerTask;

public class TimedTask extends TimerTask {

	@Override
	public void run() {
		
		new Execution().initTestSuite();
		new Execution().run("@FO_001");
	}

}
