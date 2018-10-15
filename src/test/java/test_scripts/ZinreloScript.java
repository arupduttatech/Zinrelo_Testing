package test_scripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import org.apache.commons.lang.RandomStringUtils;

import pom_scripts.POMScript;
import utilities.ConfigProperties;
import utilities.ReportListener;

/**
 * Unit test for simple App.
 */
@Listeners(ReportListener.class)
public class ZinreloScript {
	
	WebDriver driver;
	POMScript dataPOM;
	Logger log = Logger.getLogger(ZinreloScript.class);
	ConfigProperties prop = new ConfigProperties();
	WebDriverWait wait;
	
	@BeforeMethod(alwaysRun = true)
	public void setupBrowser(){
		
		System.setProperty("webdriver.chrome.driver", prop.getProperty("driverPath"));
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));	
		dataPOM = new POMScript(driver);
		wait = new WebDriverWait(driver, 3000);
	}
	
	//test to verify the full success journey
	@Test
	public void testSuccessJourney() throws InterruptedException {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addSearchMember(RandomStringUtils.randomAlphabetic(5) + "@yy.cc", RandomStringUtils.randomAlphabetic(4), RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomNumeric(5));
		log.info("New Member added successfully");
		
	}
	
	@Test
	public void testInvalidLogin() {
		
		//test to verify login with invalid credentials
		dataPOM.invalidLogin("unregisteredDataLogin");
		
		//test to verify login with blank email ID
		dataPOM.invalidLogin("blankID");
		
		//test to  verify login with blank password
		dataPOM.invalidLogin("blankPassword");
		
		//test to verify login with no credentials
		dataPOM.invalidLogin("blankCredentials");
		
		//test to verify login with special characters in email ID
		dataPOM.invalidLogin("specialCharsID");
		
		//test to verify login with space as email ID
		dataPOM.invalidLogin("spaceAsID");
		
	}
	
	//test to verify search with invalid data
	@Test
	public void testSearchWithInvalidData() throws InterruptedException {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addSearchInvalidMember(RandomStringUtils.randomAlphabetic(5) + "@yy.cc", RandomStringUtils.random(4), RandomStringUtils.random(5), RandomStringUtils.randomNumeric(5));
	}
	
	//test to verify search with space contained in Fullname
	@Test
	public void testSearchWithSpace() throws InterruptedException {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addSearchInvalidMember(RandomStringUtils.randomAlphabetic(5) + "@ddd.wfe", RandomStringUtils.random(4), RandomStringUtils.random(5), " ");
	}
	
	//test to verify search with a special character contained in email ID
	@Test
	public void testSearchWithSpecialChars() throws InterruptedException {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addSearchInvalidMember(RandomStringUtils.randomAlphabetic(5) + "@yy.cc", RandomStringUtils.random(4), RandomStringUtils.random(5), "@");
	}
	
	@Test
	public void testAddMemberWithSpecialChars() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember("^%#&^@%^$.%^$^", "%$^%", "&^%&^");
		
	}
	
	@Test
	public void testAddEmptyMember() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember("", "", "");
		
	}
	
	@Test
	public void testAddNoEmailMember() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember("", RandomStringUtils.randomNumeric(4), RandomStringUtils.randomNumeric(4));
	}
	
	@Test
	public void testAddNoFirstNameMember() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember(RandomStringUtils.randomNumeric(4) + "@" + RandomStringUtils.randomNumeric(3) + "." + RandomStringUtils.randomNumeric(3), "", RandomStringUtils.randomNumeric(4));
		
	}
	
	@Test
	public void testAddNoLastNameMember() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember(RandomStringUtils.randomNumeric(4) + "@" + RandomStringUtils.randomNumeric(3) + "." + RandomStringUtils.randomNumeric(3), RandomStringUtils.randomNumeric(4), "");
		
	}
	
	@Test
	public void testAddNoNameMember() {
		
		dataPOM.login(prop.getProperty("userID"), prop.getProperty("userPassword"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='welcome-user-message-div']")));
		log.info("User Logged In Successfully");
		
		dataPOM.addInvalidMember(RandomStringUtils.randomNumeric(4) + "@" + RandomStringUtils.randomNumeric(3) + "." + RandomStringUtils.randomNumeric(3), "", "");
		
	}
	
	
	@AfterMethod(alwaysRun = true)
	public void quitBrowser(){
		driver.close();
	}	
}