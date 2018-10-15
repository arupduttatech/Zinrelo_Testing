package pom_scripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import utilities.ConfigProperties;


public class POMScript {
	
	WebDriver driver;
	WebDriverWait wait;
	
	Logger log = Logger.getLogger(POMScript.class);
	ConfigProperties prop = new ConfigProperties();
	
	@FindBy(xpath="//input[contains(@id,'email')]")
	public WebElement userEmailID;
	
	@FindBy(xpath="//input[contains(@id,'password')]")
	public WebElement userPassword;
	
	@FindBy(xpath="//button[contains(@id,'login_button')]")
	public WebElement loginButton;
	
	@FindBy(xpath="//a[contains(@id,'customers_tab')]")
	public WebElement membersTab;
	
	@FindBy(xpath="//i[contains(@id,'add')]")
	public WebElement addMemberIcon;
	
	@FindBy(xpath="//input[contains(@id,'user_email')]")
	public WebElement addEmail;
	
	@FindBy(xpath="//input[contains(@id,'first_name')]")
	public WebElement firstName;
	
	@FindBy(xpath="//input[contains(@id,'last_name')]")
	public WebElement lastName;
	
	@FindBy(xpath="//input[contains(@id,'merchant_user_id')]")
	public WebElement memberID;
	
	@FindBy(xpath="//a[contains(@id,'enroll_user_proceed')]")
	public WebElement addMemberButton;
	
	@FindBy(xpath="//span[contains(text(),'has been enrolled successfully')]")
	public WebElement addMemberConfirmationMessage;
	
	@FindBy(xpath = "//a[contains(@id,'enroll_user_success')]")
	public WebElement okButton;
	
	@FindBy(xpath = "//input[contains(@class,'search')]")
	public WebElement searchBox;
	
	@FindBy(xpath = "//i[@class='sprite search']")
	public WebElement searchIcon;
	
	@FindBy(xpath = "//table//tr[@class='user_row']//td/a[@class='profile_link']")
	public List<WebElement> profileLink;
	
	@FindBy(xpath = "//div[contains(@id,'alert')]/span[contains(@id,'alert')]")
	public List<WebElement> invalidLoginAlert;
	
	@FindBy(xpath = "//div[contains(@id,'alert_box')]/span[@id='alert_msg']")
	public List<WebElement> invalidAddAlert;
	
	@FindBy(xpath = "//button[@id='modal_close']")
	public WebElement closeAlert;
	
	public POMScript(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 3000);

        //This initElements method will create all WebElements

    }
	
	public void login(String email, String password) {
		
		userEmailID.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		
	}
	
	public void addSearchMember(String email, String firstname, String lastname, String memberId) throws InterruptedException {
		addMember(email, firstname, lastname);
		
		searchMember(firstname, lastname, email);
		
		String fullName = firstname + " " + lastname;
		Assert.assertEquals(fullName, profileLink.get(0).getText());
	}
	
	public void addSearchInvalidMember(String email, String firstname, String lastname, String searchText) throws InterruptedException {
		
		addMember(email, firstname, lastname);
		searchMember(firstname, lastname, searchText);
		Assert.assertTrue(!profileLink.isEmpty());
	}
	
	public void addMember(String email, String firstname, String lastname) throws InterruptedException {
		membersTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[contains(@id,'add')]")));
		addMemberIcon.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		addEmail.clear();
		Thread.sleep(2000);
		addEmail.sendKeys(email);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		firstName.sendKeys(firstname);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		lastName.sendKeys(lastname);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		addMemberButton.click();
		String confirmationText = firstname + " " + lastname + " (" + email + ") has been enrolled successfully.";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'has been enrolled successfully')]")));
		Assert.assertEquals(confirmationText , addMemberConfirmationMessage.getText());
		okButton.click();
	}
	
	public void addInvalidMember(String email, String firstname, String lastname) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@id,'customers_tab')]")));
		membersTab.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[contains(@id,'add')]")));
		addMemberIcon.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		addEmail.click();
		addEmail.sendKeys(email);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		firstName.click();
		firstName.sendKeys(firstname);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		lastName.click();
		lastName.sendKeys(lastname);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@id,'user_email')]")));
		addMemberButton.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id,'alert_box')]/span[@id='alert_msg']")));
		Assert.assertTrue(!invalidAddAlert.isEmpty());
		closeAlert.click();
	}
	
	public void searchMember(String firstname, String lastname, String searchText) {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//i[@class='sprite search']")));
		searchBox.clear();
		searchBox.sendKeys(searchText);
		searchIcon.click();
		
	}
	
	public void invalidLogin(String scenarioName) {
		userEmailID.clear();
		userPassword.clear();
		login(scenarioName + "ID", scenarioName + "password");
		
		ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@id,'alert')]/span[contains(@id,'alert')]"));
		Assert.assertTrue(!invalidLoginAlert.isEmpty());
	}
}
