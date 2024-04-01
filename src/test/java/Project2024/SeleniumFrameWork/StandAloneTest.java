package Project2024.SeleniumFrameWork;

import java.io.IOException;
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
import org.testng.annotations.Test;

import TestComponents.baseClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.MemberSubstitution.Argument;
import seleniumFrameWork.PageObject.ConfrimationPage;
import seleniumFrameWork.PageObject.LandingPage;
import seleniumFrameWork.PageObject.OrderHistroyPage;
import seleniumFrameWork.PageObject.ProdCatalog;
import seleniumFrameWork.PageObject.cartPage;
import seleniumFrameWork.PageObject.placeOrderPage;

public class StandAloneTest extends baseClass {
	String productName = "ZARA COAT 3";

	@Test

	public void submitOrderTest() throws IOException, InterruptedException  {
		
		
		
		String countryName = "India";
			
		// to select product
		ProdCatalog prodCatalog = lp.loginApplication("madhus149@gmail.com", "Madhu@1991");
		List<WebElement> products = prodCatalog.getProductsList();

		// To click on add to cart button
		prodCatalog.addToCart(productName);

		// goto cart page
		cartPage cp = prodCatalog.goToCartPage();

		boolean match = cp.verifyItem(productName);
		Assert.assertTrue(match);

		// Checkout Button
		placeOrderPage pop = cp.checkOut();

		// AutoSuggestion handling using actions class

		pop.selectCountry(countryName);

		ConfrimationPage conPage = pop.submitOrder();

		String actualString = conPage.getMessage();
		Assert.assertEquals(actualString, "THANKYOU FOR THE ORDER.");
		

	}
	
	@Test(dependsOnMethods = {"submitOrderTest"})
	
	public void VerifyOrderHistroy() {
		
		ProdCatalog prodCatalog = lp.loginApplication("madhus149@gmail.com", "Madhu@1991");
		List<WebElement> products = prodCatalog.getProductsList();

		// To click on add to cart button
		prodCatalog.addToCart(productName);
		
		OrderHistroyPage ohp =prodCatalog.gotoOrderPage();
		boolean match = ohp.verifyOrderNames(productName);
		Assert.assertTrue(match);
		
	}

}
