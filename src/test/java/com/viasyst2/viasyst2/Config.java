package com.viasyst2.viasyst2;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.viasyst2.base.TestBase;

public class Config extends TestBase {

	//builds a new report using the html template 
    ExtentHtmlReporter htmlReporter;
    
    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;

	public Config() throws IOException {
		super();
	}

	@BeforeSuite
	public void start() {

		
	 	// initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/TestReport/testReport.html");
        
        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        //To add system or environment info by using the setSystemInfo method.
     //   extent.setSystemInfo("OS", Windows);
       // extent.setSystemInfo("Chrome", Chrome);
        
        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
  
        
		
		
	/*	extent = new ExtentReports("C:\\Users\\Sony\\eclipse-workspace\\viasyst2\\TestReport\\index.html", true);
		extent.addSystemInfo("OS", "Windows");
		extent.addSystemInfo("Owner", "Houssem");
		extent.addSystemInfo("Test Name", "ViaSyst Test");
		extent.addSystemInfo("Language", "Java");
		extent.addSystemInfo("Framework Design", "Page Object");

	*/}

	@AfterSuite
	public void end() {
		extent.flush();
	}

}
