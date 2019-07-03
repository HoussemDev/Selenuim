package com.viasyst2.viasyst2;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.viasyst2.base.TestBase;

public class organisationPageTest extends TestBase {
	
	//builds a new report using the html template 
    ExtentHtmlReporter htmlReporter;
    
    ExtentReports extent;
    //helps to generate the logs in test report.
    ExtentTest test;

	@FindBy(name = "name")
	WebElement loginTextBox;
	@FindBy(name = "pass")
	WebElement passwordTextBox;
	@FindBy(id = "edit-submit")
	WebElement loginButton;

	public organisationPageTest() throws IOException {

		initialization();


		PageFactory.initElements(driver, this);

	}

	
    @BeforeTest
    public void startReport() {
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
    }
      
	@BeforeMethod
	public void login() {

		loginTextBox.sendKeys(prop.getProperty("cbusername"));
		passwordTextBox.sendKeys(prop.getProperty("cbpassword"));
		loginButton.click();
	}

	@Test(priority = 1)
	public void clickOnOrgansiationMainMenu() {
        test = extent.createTest("Test Case Add Organisation");

		WebElement organisationButton = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[2]/a"));
		organisationButton.click();
		
		WebElement addOrganisationButton = driver.findElement(
				By.cssSelector("#page-wrapper > div > div:nth-child(2) > section > div > div.row > div > ul > li > a"));
		addOrganisationButton.click();
		WebElement organisationName = driver.findElement(By.xpath("//input[@id='edit-title-0-value']"));
		// WebElement adress =
		// driver.findElement(By.xpath("//*[@id='cke_edit-field-cl-address-0-value']"));
		WebElement city = driver.findElement(By.xpath("//textarea[@id='edit-field-cl-city-0-value']"));
		Select drpcountry = new Select(driver.findElement(By.xpath("//*[@id=\"edit-field-cl-country\"]")));
		WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"edit-submit\"]"));
		organisationName.sendKeys("Test Auto org");

		WebElement editorFrame = driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe"));

		driver.switchTo().frame(editorFrame);

		WebElement body = driver.findElement(By.tagName("body"));

		body.clear();
		body.sendKeys("some text");

		driver.switchTo().defaultContent();

		WebElement CheckBoxFamiQs = driver.findElement(By.xpath("//*[@id=\"edit-field-cl-ref-no-2426807\"]"));
		CheckBoxFamiQs.click();

		city.sendKeys("Test Auto City");
		drpcountry.selectByVisibleText("ALBANIA");
		saveButton.click();

	}

	/*@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Test Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(LogStatus.FAIL, "Test FAILED");
		} else if (result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "Test Skipped");
		}

		driver.quit();
	}*/

	
	
	  @AfterMethod
	    public void getResult(ITestResult result) {
	        if(result.getStatus() == ITestResult.FAILURE) {
	            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
	            test.fail(result.getThrowable());
	        }
	        else if(result.getStatus() == ITestResult.SUCCESS) {
	            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
	        }
	        else {
	            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
	            test.skip(result.getThrowable());
	        }
	    }
	     
	    @AfterTest
	    public void tearDown() {
	    	//to write or update test information to reporter
	        extent.flush();
	    }
		
	
}




