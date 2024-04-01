package seleniumFrameWork.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class OrderHistroyPage extends AbstractComponents {
	WebDriver driver;

	public OrderHistroyPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//tr/td[2]")
	List<WebElement> OrderNames;
	
	
	public boolean verifyOrderNames(String productName) {
		
		boolean match = OrderNames.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}