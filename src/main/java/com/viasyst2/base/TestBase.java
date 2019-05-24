package com.viasyst2.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.viasyst2.util.WebListener;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebListener webListener;

	public TestBase() throws IOException {
		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Sony\\eclipse-workspace\\QA_First_Test\\src\\com\\demo\\config\\config.properties");
		prop.load(fis);
	}

	public void initialization() {
		
		//if (browser.equalsIgnoreCase("browser")) {
		   String Chromepath = System.getProperty("user.dir")+"\\resources\\chromedriver.exe";

	     	System.setProperty("webdriver.chrome.driver", Chromepath);
			driver = new ChromeDriver();

		//}
		
		/*else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\geckodriver.exe");
			driver = new FirefoxDriver();
		}*/
		e_driver = new EventFiringWebDriver(driver);
		webListener = new WebListener();
		e_driver.register(webListener);
		driver = e_driver;
		driver.get(prop.getProperty("URL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

}
