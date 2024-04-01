package seleniumFrameWork.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy (css =("[class*='flyInOut']"))
	WebElement loginErrorMessage;
	
	By toaster = By.cssSelector("#toast-container");
	
	//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
	
	public ProdCatalog loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		waitForElementTOAppear(toaster);
		ProdCatalog prodCatalog = new ProdCatalog(driver); // creating new object in page object to reduce the over heading in test case 
		return prodCatalog;
		
	}
	public void goTo() {
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		
		waitForWebElementTOAppear(loginErrorMessage);
		String errorMsg = loginErrorMessage.getText();
		return errorMsg;
		
	}
	
}
