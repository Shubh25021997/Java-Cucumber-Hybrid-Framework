/**
 * 
 */
package Bindings;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;

/**
 * @author ShubhamShinde
 *
 */
public class DriverProvider {
	
	private static Map<String, Object> chromePrefs = new HashMap<>();
	
	public static WebDriver chromeDriverSupplier(){
		ChromeOptions options= new ChromeOptions();
		String chromeDriverPath = SeleniumManager.getInstance().getDriverPath(options, false).getDriverPath();
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		System.setProperty("webdriver.chrome.silentOutput", "true");
		
//		chromePrefs.put("credentials_enable_service", false);
//		chromePrefs.put("profile.password_manager_enabled", false);
		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--start-maximized");
		options.setExperimentalOption("prefs", chromePrefs);
		
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}

}
