package com.cheq.demo_webshop.utils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Arrays;

public class ElementActionUtils {
	
	private AndroidDriver driver;
	private WaitUtil wait;
	private final long visibilityTimeout = Long.parseLong(ConfigReader.get("visibilityTimeout"));
	private final long clickableTimeout = Long.parseLong(ConfigReader.get("clickableTimeout"));
	private final long fluentTimeout = Long.parseLong(ConfigReader.get("fluentTimeout"));
	private final long pollingInterval = Long.parseLong(ConfigReader.get("pollingInterval"));
	private static final Logger logger = LoggerUtil.getLogger(ElementActionUtils.class);
	
	public ElementActionUtils(AndroidDriver driver) {
		this.driver = driver;
		this.wait = new WaitUtil(driver);
	}
	
//	public void clickElement(By locator) {
	public void tap(By locator) {
		try {
			WebElement element = wait.waitForElementClickable(locator, clickableTimeout);
			element.click();
			logger.info("Clicked on element: {}", locator.toString());
			
		} catch (Exception e ) {
			logger.error("Failed to click on element: {}", locator.toString(), e);
			throw e;
		}
	}
	
//	public void clickFluentElement(By locator) {
	public void tapWithFluentElement(By locator) {
		try {
			WebElement element = wait.fluentWait(locator, fluentTimeout, pollingInterval);
			element.click();
			logger.info("Clicked on element: " + locator.toString());
			
		} catch (Exception e ) {
			logger.error("Failed to click on element: " + locator.toString());
			throw e;
		}
	}
	
//	public void inputElement(By locator, String text) {
	public void enterText(By locator, String text) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			element.clear();
			element.sendKeys(text);
			logger.info("Entered text in element: " + locator.toString() + " - Text: " + text);
			
		} catch (Exception e) {
			logger.error("Failed to enter text in element: " + locator.toString() + " - Text: " + text);
			throw e;
		}
	}
	
//	public void verifyDisplayed(By locator) {
	public void verifyElementVisible(By locator) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			element.isDisplayed();
			logger.info("Element is displayed: " + locator.toString());
			
		} catch (Exception e) {
			logger.error("Element is not displayed: " + locator.toString());
			throw e;
		}
	}
	
//	public void getTextAndCompare(By locator, String expectedText) {
	public void verifyText(By locator, String expectedText) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			if (element.getText().equals(expectedText)) {
				logger.info("Text matches expected: " + expectedText);
			} else {
				logger.error("Text does not match expected. Found: " + element.getText() + ", Expected: " + expectedText);
				throw new AssertionError("Text does not match expected.");
			}
		} catch (Exception e) {
			logger.error("Failed to get text or compare: " + locator.toString());
			throw e;
		}
	}
	
	public void selectDropdown(By locator, String text) {
		try {
			WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
			
		    Select select = new Select(element);
		    select.selectByValue(text);
			logger.info("Select in the dropdown: " + locator.toString() + " - Text: " + text);
		} catch (Exception e) {
			logger.error("Failed to enter text in element: " + locator.toString() + " - Text: " + text);
			throw e;
		}
	}
	
	public WebElement findElement(By locator) {
        try {
            WebElement element = wait.waitForElementVisible(locator, visibilityTimeout);
            return element;
        } catch (Exception e) {
			logger.error("Failed to find on element: " + locator.toString());
			throw e;
        }
	}

	public void swipe(String direction, int times) {
		for (int i = 0; i < times; i++) {

			Dimension size = driver.manage().window().getSize();
			int startX = size.width / 2;

			int startY;
			int endY;

			if (direction.equalsIgnoreCase("up")) {
				startY = (int) (size.height * 0.8);
				endY   = (int) (size.height * 0.2);
			} else if (direction.equalsIgnoreCase("down")) {
				startY = (int) (size.height * 0.2);
				endY   = (int) (size.height * 0.8);
			} else {
				throw new IllegalArgumentException("Direction must be 'up' or 'down'");
			}

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence swipe = new Sequence(finger, 1);

			// move to start position
			swipe.addAction(finger.createPointerMove(Duration.ZERO,
					PointerInput.Origin.viewport(), startX, startY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

			// swipe move
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
					PointerInput.Origin.viewport(), startX, endY));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Arrays.asList(swipe));
		}
	}

}
