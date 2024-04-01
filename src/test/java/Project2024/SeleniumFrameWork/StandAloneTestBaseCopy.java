package Project2024.SeleniumFrameWork;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.MemberSubstitution.Argument;

public class StandAloneTestBaseCopy {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get("https://rahulshettyacademy.com/client");

		// to Login
		driver.findElement(By.id("userEmail")).sendKeys("madhus149@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Madhu@1991");
		driver.findElement(By.id("login")).click();

		//WebElement successmsg = driver.findElement(By.cssSelector("#toast-container"));
		//wait.until(ExpectedConditions.visibilityOf(successmsg));
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		
		// to select product
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*='mb-3']")));
		List<WebElement> products = driver.findElements(By.cssSelector("[class*='mb-3']"));

		WebElement prod = products.stream().filter(prodItems -> prodItems
				.findElement(By.cssSelector("[class*='card-body'] h5")).getText().equalsIgnoreCase("ZARA COAT 3"))
				.findFirst().orElse(null);

		// To click on add to cart button
		prod.findElement(By.cssSelector("[class*='btn w-10 rounded']")).click();

		// #ng-animating -- loading screen element
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Wait till success appears and disappears

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfAllElements(successmsg));

		driver.findElement(By.cssSelector("[routerlink = '/dashboard/cart']")).click();

		List<WebElement> cartItems = driver.findElements(By.cssSelector("[class='cartSection'] h3"));

		boolean itempresent = cartItems.stream().anyMatch(Item -> Item.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(itempresent);

		// Checkout Button
		driver.findElement(By.cssSelector("[class='totalRow']:nth-child(3) button")).click();

		// AutoSuggestion handling using actions class

		Actions act = new Actions(driver);
		act.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();

		// [class*='ta-results']

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*='ta-results']")));
		// Thread.sleep(2000);
		driver.findElement(By.cssSelector("[class*='ta-item']:nth-of-type(2)")).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)");
//
		// Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".btnn.action__submit")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit")));
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();

		// from Qa section -- working also
//		WebElement e = driver.findElement(By.cssSelector(".btnn"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click(0);", e);		

		String actualString = driver.findElement(By.cssSelector("[class='hero-primary']")).getText();
		Assert.assertEquals(actualString, "THANKYOU FOR THE ORDER.");
		driver.close();

	}

}
