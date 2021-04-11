package SmallCaseTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import WebPages.Flipkart;
import WebPages.Amazon;
import utility.PropLoad;
import APIAutomation.APIAutomation;



public class APITest {
    Logger logger = Logger.getLogger(APITest.class.getName());
    APIAutomation api = new APIAutomation();


	 public static WebDriver driver;

	
@Test	
public void APITest() throws InterruptedException, IOException {
	/* Scenario: Navigate to each isbn id and validate the below fields data types. Url: https://bookstore.toolsqa.com/BookStore/v1/Books*/
	APIAutomation.getResponseBody();
	Reporter.log("API AUtomation and schema validation is successfull ");

		
	}
}
