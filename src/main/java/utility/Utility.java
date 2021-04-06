package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Utility {
	
	
	public static String  getCurrentDateAndTime()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sdf.format(date);
		
	}
	
	public static String captureScreenShot(WebDriver driver,String screenShotName) {
		
		String dateTime=Utility.getCurrentDateAndTime();
		TakesScreenshot ts=(TakesScreenshot)driver;
		File srcPath=ts.getScreenshotAs(OutputType.FILE);
		String destinationPath=System.getProperty("user.dir")+"/Screenshots/"+
		screenShotName+"_"+dateTime+".png";
		File finalDestinationPath=new File(destinationPath);
		try {
			FileUtils.copyFile(srcPath,finalDestinationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationPath;
		
	}
	
	public static String generateRandomUniqueEmailAddress() {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
		String strDate=sdf.format(d);
		return "ak-"+strDate+"@gmail.com";
			
	}
}
