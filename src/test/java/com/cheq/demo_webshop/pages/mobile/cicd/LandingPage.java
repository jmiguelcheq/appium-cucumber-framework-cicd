package com.cheq.demo_webshop.pages.mobile.cicd;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import com.cheq.demo_webshop.utils.*;

public class LandingPage {
	
	private AndroidDriver driver;
	private ElementActionUtils elementActionUtils;
	
    public LandingPage(AndroidDriver driver) {
		this.driver = driver;
        this.elementActionUtils = new ElementActionUtils(driver);
    }

    private final By HEADER_TITLE_LBL = By.xpath("//h1[normalize-space()='Simple Calculator App']");
    private final By NUM_ONE_TXT = By.xpath("//input[@id='a']");
    private final By NUM_TWO_TXT = By.xpath("//input[@id='b']");
    private final By OPERATION_TXT = By.xpath("//select[@id='op']");
    private final By COMPUTE_BTN = By.xpath("//button[@id='compute']");
    private final By RESULT_LBL = By.xpath("//div[@id='result']");
    

    public void isHeaderTextVisible() {
    	elementActionUtils.verifyElementVisible(HEADER_TITLE_LBL);
    }
    
    public void isNumberOneElementVisible() {
    	elementActionUtils.verifyElementVisible(NUM_ONE_TXT);
    }
    
    public void isNumberTwoElementVisible() {
    	elementActionUtils.verifyElementVisible(NUM_TWO_TXT);
    }
    
    public void isOperationElementVisible() {
    	elementActionUtils.verifyElementVisible(OPERATION_TXT);
    }
    
    public void isComputeButtonElementVisible() {
    	elementActionUtils.verifyElementVisible(COMPUTE_BTN);
    }
    
    public void isResultLabelElementVisible() {
    	elementActionUtils.verifyElementVisible(RESULT_LBL);
    }
    
    public void inputNumberOne(String numberOne) {
    	elementActionUtils.enterText(NUM_ONE_TXT, numberOne);
    }
    
    public void inputNumberTwo(String numberTwo) {
    	elementActionUtils.enterText(NUM_TWO_TXT, numberTwo);
    }
    
    public void selectOperation(String operation) {
      String value;
      
      switch (operation) {
          case "add": value = "add"; break;
          case "subtract": value = "sub"; break;
          case "multiply": value = "mul"; break;
          case "divide": value = "div"; break;
          default: throw new IllegalArgumentException("Unknown operation: " + operation);
      }
      
      elementActionUtils.selectDropdown(OPERATION_TXT, value);
    }
    
    public void clickCompute() {
    	elementActionUtils.tap(COMPUTE_BTN);
    }
    
    public Double getResult() {
    	WebElement resultElem =  elementActionUtils.findElement(RESULT_LBL);
    	
    	String resultText = resultElem.getText().replace("Result:", "").trim();
    	
    	return Double.parseDouble(resultText);
    }
    
//  // read result element
//  WebElement resultElem = driver.findElement(By.id("result"));
//  String resultText = resultElem.getText().replace("Result:", "").trim();
//  System.out.println("Displayed result text: " + resultText);
//
//  // parse number from "Result: X"
//  try {
//      actualResult = Double.parseDouble(resultText);
//  } catch (NumberFormatException e) {
//      throw new AssertionError("Invalid result text: " + resultText);
//  }
//
//  // validate numeric equality (allow minor floating differences)
//  assertThat(actualResult)
//          .as("Verify calculator result equals expected")
//          .isCloseTo(expected, within(0.001));
}
