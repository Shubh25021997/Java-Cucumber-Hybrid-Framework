package interactions;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import Bindings.DriverProvider;
import Bindings.ReportProvider;
import Bindings.waits;

public class Interactions {
	
	private waits wait;

	public Interactions() {
		this.wait = new waits(DriverProvider.getCurrentDriver());
	}

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

	/**
	 * Click on Webelements using javascript executor
	 * 
	 * @param locator
	 * @return Interactions object
	 */
//	@Override
	public Interactions clickWithJSE(String locator) {
		WebElement element = wait.tillElementIsVisible(locator);
		((JavascriptExecutor) DriverProvider.getCurrentDriver()).executeScript("arguments[0].click();",
																							element);
		return this;
	}

}
