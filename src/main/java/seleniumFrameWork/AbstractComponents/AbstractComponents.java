package seleniumFrameWork.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameWork.PageObject.OrderHistroyPage;
import seleniumFrameWork.PageObject.cartPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//driver.findElement(By.cssSelector("[routerlink = '/dashboard/cart']")).click();
	
	@FindBy (css =("[routerlink = '/dashboard/cart']"))
	WebElement cartPage;
	
	@FindBy (css =("[routerlink = '/dashboard/myorders']"))
	WebElement OrderPage;
	
	//
	
	public seleniumFrameWork.PageObject.cartPage goToCartPage() {
		
		cartPage.click();
		cartPage cp = new cartPage(driver);
		return cp;
	}
	
	public OrderHistroyPage gotoOrderPage() {
		OrderPage.click();
		OrderHistroyPage ohp = new OrderHistroyPage(driver);
		return ohp;
	}
	
	public void waitForElementTOAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}
	
	public void waitForWebElementTOAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementTODisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
		
	}
	
	}
