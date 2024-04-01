package seleniumFrameWork.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class placeOrderPage extends AbstractComponents {
	
	WebDriver driver;

	public placeOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css = "[class*='ta-item']:nth-of-type(2)")
	WebElement selectCountry;
	
	@FindBy(css = ".btnn.action__submit")
	WebElement submitbtn;
	
	By results = By.cssSelector("[class*='ta-results']");
		
	
	
	public void selectCountry(String countryName) {
				
		Actions act = new Actions(driver);
		act.sendKeys(country, countryName).build().perform();
		waitForElementTOAppear(results);
		selectCountry.click();
				
	}
	
	public ConfrimationPage submitOrder() throws InterruptedException {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");
		Thread.sleep(2000);
		
		 submitbtn.click();
		ConfrimationPage conPage = new ConfrimationPage(driver);
		return conPage;
	}

}
