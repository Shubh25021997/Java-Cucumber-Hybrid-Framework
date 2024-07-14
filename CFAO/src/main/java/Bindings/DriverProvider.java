package Bindings;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.manager.SeleniumManager;

public class DriverProvider {
		
		private static Map<String, Object> chromePrefs = new HashMap<>();
		private static final Map<String, WebDriver> driverMap = new HashMap<>();
		private static final Map<String, Supplier<WebDriver>> map = new HashMap<>();

		public static final String webDriver = "WebDriver";


		public static Supplier<WebDriver> chromeDriverSupplier = () -> {
			ChromeOptions options= new ChromeOptions();
			String chromeDriverPath = SeleniumManager.getInstance().getDriverPath(options, false).getDriverPath();
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			System.setProperty("webdriver.chrome.silentOutput", "true");
			
//			chromePrefs.put("credentials_enable_service", false);
//			chromePrefs.put("profile.password_manager_enabled", false);
			chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--start-maximized");
			options.setExperimentalOption("prefs", chromePrefs);
			
			WebDriver driver = new ChromeDriver(options);
			driverMap.put(webDriver, driver);
			return driver;
		};

		/**
		 * Fetch webdriver from driver map.
		 */
		public static WebDriver getCurrentDriver() {
//			WebDriver driver = new ChromeDriver();
//			return driver;
			return driverMap.get(webDriver);
		}

		/**
		 * add all the drivers into a map
		 */
		static {
			map.put("CHROME", chromeDriverSupplier);// REMOTE CHROME-CHROME
		}

		/**
		 * Create webdriver for test execution.
		 * 
		 * @param browserName
		 */
		public static WebDriver createDriver(String browserName) {
		// improved code readability and corrected confusing return of old value from the driverMap
		WebDriver driver = null;
		
		driver = map.get(browserName.toUpperCase()).get();
		//driverMap.put(webDriver, driver);  no need of this step as we are doing this in chromeDriverSupplier method declared above
		return driver;


		}
	}

// =========	========	=======	
// 			THIS BELOW CODE WAS WORKING BUT IT WAS OPENING BROWSER EVEN IF WE DONT GIVE ANY BROWSER NAME IN EXECUTIONSHEET.
//=========	========	=======	
//public class oldDriverProvider() {
//
//	
//	private static Map<String, Object> chromePrefs = new HashMap<>();
//	private static final Map<String, WebDriver> driverMap = new HashMap<>();
//	public static final String webDriver = "WebDriver";
//	private static final Map<String, WebDriver> map = new HashMap<>();
//
//
//	public static WebDriver chromeDriverSupplier(){
//		ChromeOptions options= new ChromeOptions();
//		String chromeDriverPath = SeleniumManager.getInstance().getDriverPath(options, false).getDriverPath();
//		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//		System.setProperty("webdriver.chrome.silentOutput", "true");
//		
////		chromePrefs.put("credentials_enable_service", false);
////		chromePrefs.put("profile.password_manager_enabled", false);
//		chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1 );
//		options.addArguments("--disable-popup-blocking");
//		options.addArguments("--start-maximized");
//		options.setExperimentalOption("prefs", chromePrefs);
//		
//		WebDriver driver = new ChromeDriver(options);
//		driverMap.put(webDriver, driver);
//		return driver;
//	}
//
//	/**
//	 * Fetch webdriver from driver map.
//	 */
//	public static WebDriver getCurrentDriver() {
////		WebDriver driver = new ChromeDriver();
////		return driver;
//		return driverMap.get(webDriver);
//	}
//
//	/**
//	 * add all the drivers into a map
//	 */
////	static {
////		map.put("CHROME", chromeDriverSupplier());// REMOTE CHROME-CHROME
////	}
//
//	/**
//	 * Create webdriver for test execution.
//	 * 
//	 * @param browserName
//	 */
//	public static WebDriver createDriver(String browserName) {
//	// improved code readability and corrected confusing return of old value from the driverMap
//	WebDriver webdriver = null;
//	if(browserName.equalsIgnoreCase("CHROME")) {
//		chromeDriverSupplier();
//	}
////	webdriver = map.get(browserName.toUpperCase());
////	driverMap.put(webDriver, webdriver);
//	return webdriver;
//
//	}
//
//}
