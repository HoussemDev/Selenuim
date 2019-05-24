package com.viasyst2.viasyst2;


import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import com.viasyst2.base.TestBase;

public class organisationPageTest extends TestBase {

	

	public organisationPageTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@BeforeMethod
	public void login() {

		initialization();		
		
		WebElement loginTextBox = driver.findElement(By.name("name"));
		WebElement passwordTextBox = driver.findElement(By.name("pass"));
		WebElement loginButton = driver.findElement(By.id("edit-submit"));

		loginTextBox.sendKeys(prop.getProperty("cbusername"));
		passwordTextBox.sendKeys(prop.getProperty("cbpassword"));
		loginButton.click();
	}

	@Test(priority = 1)
	public void clickOnOrgansiationMainMenu() {
		

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
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	

}

