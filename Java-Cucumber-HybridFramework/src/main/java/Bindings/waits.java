package Bindings;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.support.ui.WebDriverWait;

public class waits {
	
	private static final int defaultWaitDuration = 10;
	private static final int POLLING_TIMEOUT = 1;
	public static Map<String, Function<String, By>> locatorFunction = new ConcurrentHashMap<>();
	private WebDriverWait webDriverWait;

	static {
		locatorFunction.put("name", locator -> By.name(locator.trim()));
		locatorFunction.put("id", locator -> By.id(locator.trim()));
		locatorFunction.put("xpath", locator -> By.xpath(locator.trim()));
		locatorFunction.put("linktext", locator -> By.linkText(locator.trim()));
		locatorFunction.put("classname", locator -> By.className(locator.trim()));
	}

	public waits(WebDriver driver) {
		this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(defaultWaitDuration));
	}

	/**
	 * initiate implicit Wait -> which will be triggered before throwing NoSuchElementException
	 * 
	 * @param defaultWaitDuration
	 */
	public void initiateImplicitWait() {
		DriverProvider.getCurrentDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(defaultWaitDuration));
	}

	/**
	 * initiate explicit Wait -> which will be triggered before throwing NoSuchElementException
	 * 
	 * @param implicitWaitDuration
	 */
	public void initiateExplicitWait(WebElement webElement) {
		WebDriverWait explicitWait = new WebDriverWait(DriverProvider.getCurrentDriver(), Duration.ofSeconds(defaultWaitDuration));
				explicitWait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	/**
	 * Wait till element is visible. Element read or derived
	 * 
	 * @param elementLocator
	 */

	public WebElement tillElementIsVisible(String elementLocator) {

		WebDriver driver = DriverProvider.getCurrentDriver();
//		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(defaultWaitDuration))
//				.pollingEvery(Duration.ofSeconds(POLLING_TIMEOUT)).ignoring(NoSuchElementException.class);
		
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(defaultWaitDuration))
				.pollingEvery(Duration.ofSeconds(POLLING_TIMEOUT)).ignoring(NoSuchElementException.class);
		WebElement element = createWebElement(elementLocator);
		element = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				WebElement ele = createWebElement(elementLocator);

				return ele;

			}

		});
		return element;
	}

	/**
	 * Create webelement. For elements read or derived
	 * 
	 * @param elementLocator
	 */
	private WebElement createWebElement(String elementLocator) {
		String[] splitLocator = elementLocator.split(":=");
		WebDriver driver = DriverProvider.getCurrentDriver();
		try {
			webDriverWait.until(n -> {
				boolean found = false;
				try {
					n.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
					n.findElement(locatorFunction.get(splitLocator[0]).apply(splitLocator[1]));
					found = true;
				} catch (NoSuchWindowException | NoSuchElementException e1) {
					found = false;
				} catch (StaleElementReferenceException | JavascriptException e2) {
					found = false;
				} catch (Exception exception) {
					found = true;
				}
				return found;
			});
		} catch (RuntimeException re) {
			DataProvider.addGlobalData(DataProvider.TEST_CASE_ERROR, "Element Not Found: " + elementLocator);
			throw re;
		}
		WebElement element = driver.findElement(locatorFunction.get(splitLocator[0]).apply(splitLocator[1]));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		return element;
	}

}
