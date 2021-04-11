package WebPages;

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


public class Flipkart{

	static String AmazonSearchBox= "//input[@id='twotabsearchtextbox']";
	static String FlipkartSearchBox= "//input[contains(@title,'Search for products')]";
	static String FlipkartUserName= "//div[@class='IiD88i _351hSN']/input[@type='text']";
	static String FlipkartPassword= "//div[@class='IiD88i _351hSN']/input[@type='password']";
	static String FlipkartLogin= "//button[@class='_2KpZ6l _2HKlqd _3AWRsL']";
	static String FlipkartSearchButton= "//button[@type='submit']";
	static String FlipkartClickFirstProduct= "//div[@class='_1YokD2 _3Mn1Gg'][2]/div[@class='_1AtVbE col-12-12'][1]";

	static String FlipkartProductprice="//div[@class='_30jeq3 _16Jk6d']";
	static String FlipkartAddToCart="//button[@class='_2KpZ6l _2U9uOA _3v1-ww']";
	static String FlipkartIncreaseQuantitybutton="//button[@class='_23FHuj'][2]";
	static String FlipkartCartPagePrice="//div[@class='Ob17DV _3X7Jj1']/span";

public static void loadFlipkartPage(WebDriver driver) {

	try {
			driver.get(PropLoad.getProperties("AmazonUrl"));
			WebDriverWait wait = new WebDriverWait(driver, 30);
			
//		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartUserName))).sendKeys("raje1490@yahoo.in");
//		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartPassword))).sendKeys("9176706664");
//		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartLogin))).click();
		    Thread.sleep(2000);

		    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2KpZ6l _2doB4z']"))).click();
		}
    catch (Exception e) {
		e.printStackTrace();
	}
}

public static void flipkartSearchProduct(WebDriver driver) {
	
	try {
		  String parentWindow = driver.getWindowHandle();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartSearchBox))).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartSearchBox))).sendKeys(PropLoad.getProperties("SearchItem"));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartSearchButton))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartClickFirstProduct))).click();
		
		Set<String> allWindows = driver.getWindowHandles();
		  //No of windows=2
		  //removing parent window, 
		  allWindows.remove(parentWindow);

		  Iterator<String> ite = allWindows.iterator();
		  //So now Set contains only new tab window only,so switch to it
		  driver.switchTo().window((String) ite.next());
		
	}
	 catch (Exception e) {
			e.printStackTrace();
		}
	
}

public static int flipkartGetPriceAddCart(WebDriver driver) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, 30);

	String flipkartPrice = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartProductprice))).getText();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartAddToCart))).click();
	String flipkartCartPagePrice=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartCartPagePrice))).getText();
	
	
	String price = (flipkartCartPagePrice.substring(1)).replace(",", "");
	int flipkartprice=Integer.parseInt(price);
	
//	System.out.println("Price of the product in cart page "+ flipkartCartPagePrice.substring(1));
	return flipkartprice;
}
	
public static int increaseQuantityFlipkart(WebDriver driver) throws InterruptedException {
	WebDriverWait wait = new WebDriverWait(driver, 30);

	/*Increase the quantity*/
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartIncreaseQuantitybutton))).click();
	
	Thread.sleep(3000);
	
	String flipkartCartPagePrice=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(FlipkartCartPagePrice))).getText();
	
//	System.out.println("Price after addition of Quantity is "+ flipkartCartPagePrice.substring(1));
	String price = (flipkartCartPagePrice.substring(1)).replace(",", "");
	int flipkartprice=Integer.parseInt(price);
	
	return flipkartprice;
	
}
public static void RemoveItem(WebDriver driver) throws InterruptedException {
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Remove')]"))).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='_3dsJAO _24d-qY FhkMJZ']"))).click();
    Thread.sleep(3000);

	
}

}
