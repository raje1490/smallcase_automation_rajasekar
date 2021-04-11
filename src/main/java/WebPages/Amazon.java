package WebPages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.PropLoad;
import org.openqa.selenium.Keys;


public class Amazon{

	static String AmazonSearchBox= "//input[@id='twotabsearchtextbox']";

	static String AmazonSearchButton= "//input[@id='nav-search-submit-button']";
	static String AmazonClickFirstProduct= "//span[@data-cel-widget='MAIN-SEARCH_RESULTS-0']//*[@class='a-link-normal a-text-normal']";
	static String AmazonProductprice="//span[@id='priceblock_ourprice']";
	static String AmazonAddToCart="//input[@id='add-to-cart-button']";
	static String AmazonCartPagePrice="//span[@id='sc-subtotal-amount-activecart']/span";

public static void loadAmazonPage(WebDriver driver) {

	try {

		driver.get(PropLoad.getProperties("AmazonUrl"));
		Thread.sleep(2000);
		}
    catch (Exception e) {
		e.printStackTrace();
	}
}

public static void AmazonSearchProduct(WebDriver driver) {
	
	try {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonSearchBox))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonSearchBox))).sendKeys(PropLoad.getProperties("SearchItem"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonSearchButton))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonClickFirstProduct))).click();
		
		ArrayList<String> tabs_windows = new ArrayList<String> (driver.getWindowHandles());
		int tabCount= tabs_windows.size()-1;
	    driver.switchTo().window(tabs_windows.get(tabCount));
		  //So now Set contains only new tab window only,so switch to it		
	}
	 catch (Exception e) {
			e.printStackTrace();
		}
	
}
public static void amazonGetPriceAddCart(WebDriver driver) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, 30);

	String AmazonProductPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonProductprice))).getText();
	String price =AmazonProductPrice.substring(1);
	String FinalPrice=price.substring(0, price.length() - 3);

//	System.out.println("Price of the Single Quantity product "+ FinalPrice.trim());
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonAddToCart))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='a-link-normal close-button']"))).click();
	Thread.sleep(3000);

	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='nav-cart']"))).click();
	Thread.sleep(3000);
	
}
	
public static int amazonPriceCartPage(WebDriver driver) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, 30);
	String AmazonProductPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(AmazonCartPagePrice))).getText();
//	float price=Float.parseFloat(flipkartPrice.substring(1));

	String FinalPrice=AmazonProductPrice.substring(0, AmazonProductPrice.length() - 3);

//	System.out.println("Price of the product in quote page "+ FinalPrice.trim());
	
	String price = (FinalPrice.trim()).replace(",", "");
	int amazonprice=Integer.parseInt(price);
	
	return amazonprice;
		
}
public static void CompareTwoPrices(int FlipkartPrice, int AmazonPrice) {
	
	int retResult =  Integer.compare(FlipkartPrice, AmazonPrice);    
    if(retResult > 0) {  
       System.out.println("Amazon price is Cheaper than flipkart price");  
    } else if(retResult< 0) {  
       System.out.println("Flipkart price is Cheaper than Amazon price");  
    } else {  
       System.out.println("Both Amazon and Flipkart Prices are equal");  
    }  
}

public static void RemoveItem(WebDriver driver) throws InterruptedException {
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Remove')]"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='_3dsJAO _24d-qY FhkMJZ']"))).click();
    Thread.sleep(3000);

	
}

}
