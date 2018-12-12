package com.sprint;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.NetworkChannel;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class zohoTest {
	
	WebDriver driver;
	WebDriverWait wait;
	String path = "C:\\Users\\PC USER\\Selenium\\selenium-maven-zoho\\src\\test\\resources\\config.properties";
	String excelPath;
	Properties prop;
	Logger log = LogManager.getLogger(zohoTest.class.getName());
	
	
	
	
	@BeforeTest
	public void setUp() throws IOException {
		
		//webdriver setup
		WebDriverManager.chromedriver().setup();
		driver = new  ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		//properties setup
		FileInputStream ip = new FileInputStream(path);
		prop = new Properties();
		prop.load(ip);
		
		//navigate setup
		driver.get(prop.getProperty("url"));
		
	}
	
	@AfterTest
	public void close() {
		
		driver = new ChromeDriver();
		driver.quit();
	}
	
	@Test
	public void printExcel() {
		
		excelPath = "C:\\Users\\PC USER\\Desktop\\zoho.xlsx";
		Xls_Reader data = new Xls_Reader(excelPath);
		
		Select s = new Select(driver.findElement(By.id("recPerPage")));
		s.selectByIndex(3);
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#reportTab>tbody>:nth-child(20)")));
		
		List<WebElement> rows = driver.findElements(By.cssSelector("#reportTab>tbody tr"));
		
		List<WebElement> columns = driver.findElements(By.cssSelector("#reportTab>tbody>tr:nth-of-type(1)>td"));
		
		List<WebElement> id = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(1)"));
		List<WebElement> name = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(2)"));
		List<WebElement> email = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(3)"));
		List<WebElement> phone = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(4)"));
		List<WebElement> salary = driver.findElements(By.cssSelector("#reportTab>tbody>tr>:nth-child(5)"));	
		
		for(int i=0; i<rows.size(); i++) {
			data.setCellData("Sheet1","ID" , i+2, (id.get(i).getText()));
			log.debug("Writing Employee ID-" + (id.get(i).getText()));
			
			
			data.setCellData("Sheet1","NAME" , i+2, (name.get(i).getText()));
			log.debug("Writing Employee NAME-" + (name.get(i).getText()));
			
			
			data.setCellData("Sheet1","EMAIL" , i+2, (email.get(i).getText()));
			log.debug("Writing Employee EMAIL-" + (email.get(i).getText()));
			
			
			data.setCellData("Sheet1","PHONE" , i+2, (phone.get(i).getText()));
			log.debug("Writing Employee PHONE-" + (phone.get(i).getText()));
			
			
			data.setCellData("Sheet1","SALARY" , i+2, (salary.get(i).getText()));
			log.debug("Writing Employee SALARY-" + (salary.get(i).getText()));
		}
		
		
	}

}
