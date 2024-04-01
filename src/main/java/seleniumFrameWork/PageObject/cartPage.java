package seleniumFrameWork.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class cartPage extends AbstractComponents {
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "[class='cartSection'] h3")
	List<WebElement> cartItems;
	
	@FindBy(css = ("[class='totalRow']:nth-child(3) button"))
	WebElement checkOutButton;
		
	public boolean verifyItem(String productName) {
		
		boolean match = cartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public placeOrderPage checkOut() {
		
		checkOutButton.click();
		placeOrderPage pop = new placeOrderPage(driver);
		return pop;
	}
}