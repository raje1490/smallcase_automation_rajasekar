package SmallCaseTest;

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



public class WebAppTest {
    Logger logger = Logger.getLogger(WebAppTest.class.getName());
    Flipkart FlipkartPage = new Flipkart();
    Amazon AmazonPage = new Amazon();
    PropLoad scr = new PropLoad();
    APIAutomation api = new APIAutomation();


	 public static WebDriver driver;

	
	@BeforeMethod	
	public void setup() {
			

		    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		    ChromeOptions option = new ChromeOptions();
		    option.addArguments("incognito");
		    capabilities.setCapability(ChromeOptions.CAPABILITY,option);
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");  
		    driver= new ChromeDriver(capabilities);
		    driver.manage().window().maximize();
		}
@Test	
public void Scenario1() throws InterruptedException {
	/*
	 1. Go to https://www.flipkart.com/ 
	 2. Search for any item (e.g : “vu tv”) 
	 3. Click on the First Item in the list. 
	 4. Print Price of the Item. 
	 5. Add to cart in guest mode.
	 6. Go to Cart Page. 
	 7. Increase Quantity by 1.
	 8. Print the Price after addition of Quantity */
		
	Flipkart.loadFlipkartPage(driver);
		
	Flipkart.flipkartSearchProduct(driver);
	
	
	int CartPagePrice = Flipkart.flipkartGetPriceAddCart(driver);
	Reporter.log("Price in Cart page "+CartPagePrice);

	int CartPrice = Flipkart.increaseQuantityFlipkart(driver);
	Reporter.log("Price after addition of Quantity is "+CartPrice);
		
	}
@Test
public void Scenario2() throws Exception {
	
	/*1. Go to https://www.flipkart.com/ 
	 * 2. Search for any item (e.g : “VU TV”) 
	 * 3. Click on the First Item in the list. 
	 * 4. Print Price of the Item. 
	 * 5. Add to cart in guest mode. 
	 * 6. Print the Price. 
	 * 7. Go to https://www.amazon.in/ 
	 * 8. Search for any Item (e.g.”VU TV”) 
	 * 9. Click on the exact Item as in Flipkart. 
	 * 10. Print the Price. 
	 * 11. Add to cart(in guest Mode). 
	 * 12. Go to Cart. 13. Print the Price. 
	 * 14. Compare both the Prices. 
	 * 15. Print which site is giving Cheaper rates.*/
	
	Flipkart.loadFlipkartPage(driver);

	Flipkart.flipkartSearchProduct(driver);
	int flipkartPrice =Flipkart.flipkartGetPriceAddCart(driver);
	Reporter.log("Price in Flipkart "+flipkartPrice);

	System.out.println("Price in Flipkart "+flipkartPrice);
	PropLoad.takeScreenshot(driver);

	Amazon.loadAmazonPage(driver);
	 ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs_windows.get(1));
	Amazon.AmazonSearchProduct(driver);
	Amazon.amazonGetPriceAddCart(driver);
	int amazonPrice = Amazon.amazonPriceCartPage(driver);
	Reporter.log("Price in Amazon "+ amazonPrice);

	System.out.println("Price in Amazon "+ amazonPrice);
	PropLoad.takeScreenshot(driver);

		
	Amazon.CompareTwoPrices(flipkartPrice,amazonPrice);
	}

@AfterMethod
public void teardown() {
	
	driver.quit();
}
}
