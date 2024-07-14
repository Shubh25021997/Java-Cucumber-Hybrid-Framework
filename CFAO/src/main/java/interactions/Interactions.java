package interactions;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Bindings.DriverProvider;
import Bindings.ReportProvider;

public class Interactions {
	/**
	 * Take and attach screenshot in report
	 * 
	 * @throws IOException
	 */
	public void takeScreenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) DriverProvider.getCurrentDriver();
		String source = ts.getScreenshotAs(OutputType.BASE64);
		ReportProvider.attachScreenshot("data:image/jpg;base64, " + source);
//		return this;
	}


}
