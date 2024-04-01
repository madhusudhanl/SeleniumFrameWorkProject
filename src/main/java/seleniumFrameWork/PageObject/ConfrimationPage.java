package seleniumFrameWork.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class ConfrimationPage extends AbstractComponents{

	WebDriver driver;

	public ConfrimationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "[class='hero-primary']")
	WebElement message;
	
	public String getMessage() {
		
		String actualString = message.getText();
		return actualString;
	}
}
