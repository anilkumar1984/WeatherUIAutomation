package generics;

import java.io.File;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import dataProviders.ExcelFileReader;
import utility.Utility;

public abstract class BaseTest {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static int excelRowCount;
	public static ExcelFileReader excelFileReader;

	@BeforeSuite
	public void extentReportSetUp() {
		String currentDateAndTime = Utility.getCurrentDateAndTime();
		File reporterPath = new File("./Reports/ndtvWeather_" + currentDateAndTime + ".html");
		htmlReporter = new ExtentHtmlReporter(reporterPath);
		htmlReporter.config().setDocumentTitle("NDTV Weather Report");
		htmlReporter.config().setReportName("NDTV Weather Automation Report");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		excelFileReader=new ExcelFileReader("WeatherInfo");
		 excelRowCount=excelFileReader.getRowCount();
		System.out.println("RowCount: "+ excelRowCount);
		excelFileReader.createRow(excelRowCount);
		

	}
	
	@AfterSuite(alwaysRun=true)
	public void cleanUp() {
		extent.flush();
		excelFileReader.closeWorkBook();

	}

}
