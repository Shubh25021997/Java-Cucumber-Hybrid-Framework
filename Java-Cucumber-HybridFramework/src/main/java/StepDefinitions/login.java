package StepDefinitions;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Bindings.DriverProvider;
import Bindings.ReportProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class login {
	
	private static final Logger logger = LoggerFactory.getLogger(login.class);
	
//	@Given("Login into OTM as '{string}'") -> only string inputs will be taken but if we use (.*) then all type of inputs we can use.
	@Given("^Login into OTM as '(.*)'$")
	public void login_into_otm_as(String string) throws InvalidFormatException, IOException {
		ReportProvider.pass("login step : " + string );
//		new configProvider().readConfigData();
//		System.out.println("used link:"+ configProvider.getColumnURL());
//		System.out.println("used username:" + configProvider.getColumnUsername());
//		System.out.println("used password:" + configProvider.getColumnPassword());
	}

	@Then("Wait for {string} seconds")
	public void wait_for_seconds(String string) {
		ReportProvider.fail("waiting");
	}

	@Then("Attach ScreenShot")
	public void attach_screen_shot() {
		logger.info("attach screenshot");
	}

	@Then("Logout from OTM")
	public void logout_from_otm() {
		
		DriverProvider.getCurrentDriver().findElement(By.xpath("[@attribute='attribute']"));
		logger.info("Logged Out");
//		DriverProvider.getCurrentDriver().quit();;
	}

}
