package StepDefinitions;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import interactions.configProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class login {
	
	@Given("Login into OTM as {string}")
	public void login_into_otm_as(String string) throws InvalidFormatException, IOException {
		System.out.println("login step");
		new configProvider().readConfigData();
		System.out.println("used link:"+ configProvider.getColumnURL());
		System.out.println("used username:" + configProvider.getColumnUsername());
		System.out.println("used password:" + configProvider.getColumnPassword());
	}

	@Then("Wait for {string} seconds")
	public void wait_for_seconds(String string) {
		System.out.println("waiting");
	}

	@Then("Attach ScreenShot")
	public void attach_screen_shot() {
		System.out.println("attach screenshot");
	}

	@Then("Logout from OTM")
	public void logout_from_otm() {
		System.out.println("logout step");
	}

}
