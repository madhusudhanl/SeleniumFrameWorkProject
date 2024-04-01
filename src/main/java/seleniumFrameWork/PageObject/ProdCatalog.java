package seleniumFrameWork.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumFrameWork.AbstractComponents.AbstractComponents;

public class ProdCatalog extends AbstractComponents{

	WebDriver driver;
	
	public ProdCatalog(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//List<WebElement> products = driver.findElements(By.cssSelector("[class*='mb-3']"));
	
	@FindBy(css="[class*='mb-3']") 
	List <WebElement> products;  // because we are finding multiple elements
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector("[class*='mb-3']");
	By addToCart = By.cssSelector("[class*='btn w-10 rounded']");
	By toastMessage = By.cssSelector("#toast-container");
	//driver.findElement(By.cssSelector(".ng-animating")))
	
	public List<WebElement> getProductsList()
	{
		waitForElementTOAppear(productsBy); // we cannot use pagefactory because it needs only By locator and in page factory we 
												// send drive.findElements(By.css(loctor))
		return products;
		
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductsList().stream().filter(prodItems -> prodItems
				.findElement(By.cssSelector("[class*='card-body'] h5")).getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		
		return prod;
	}
	
	public void addToCart(String productName) {
		
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementTOAppear(toastMessage);
		waitForElementTODisappear(spinner);
		
		
	}
	
}
