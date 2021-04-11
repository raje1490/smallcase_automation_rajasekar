package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class PropLoad  {
	 
	 
	public static String getProperties(String PropertyName) {
		String PropertyText= "";
		File file = new File(System.getProperty("user.dir")+"/TestData/TestData.properties");
		  
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();}
		if (PropertyName.equals("AmazonUrl")) {
			PropertyText=prop.getProperty("AmazonUrl");
		}
		else if (PropertyName.equals("FlipkartUrl")) {
			PropertyText=prop.getProperty("FlipkartUrl");

		}
		else if (PropertyName.equals("SearchItem")) {
			PropertyText=prop.getProperty("SearchItem");

		}
		
		return PropertyText;
		}		
	
	public static void takeScreenshot(WebDriver driver) throws Exception {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//The below method will save the screen shot in d drive with name "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		screenShotName = new File(System.getProperty("user.dir")+"/Screenshots"+timeStamp+".png");
		FileUtils.copyFile(scrFile, screenShotName);
		 
		String filePath = screenShotName.toString();
		String path =System.getProperty("user.dir")+"/Screenshots";
		Reporter.log(path);
		 
		}
}