package Project2024.SeleniumFrameWork;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.RetryTest;
import TestComponents.baseClass;
import seleniumFrameWork.PageObject.ConfrimationPage;
import seleniumFrameWork.PageObject.ProdCatalog;
import seleniumFrameWork.PageObject.cartPage;
import seleniumFrameWork.PageObject.placeOrderPage;

public class ErrorValidationTest extends baseClass {

	@Test(groups = {"ErrorHandling"},retryAnalyzer = RetryTest.class)

	public void submitOrderTest() throws IOException, InterruptedException  {
	
		lp.loginApplication("madhus149@gmail.com", "1Madhu@1991");
		String actualmsg = lp.getErrorMessage();
		//System.out.println(actualmsg);
		Assert.assertEquals(actualmsg, "Incosrrect email or password.");
	
	}

	@Test
	public void VerfiyCartProduct() throws IOException, InterruptedException  {
		
		
		String productName = "ZARA COAT 3";
	
		// to select product
		ProdCatalog prodCatalog = lp.loginApplication("madhus149@gmail.com", "Madhu@1991");
		List<WebElement> products = prodCatalog.getProductsList();

		// To click on add to cart button
		prodCatalog.addToCart(productName);

		// goto cart page
		cartPage cp = prodCatalog.goToCartPage();

		boolean match = cp.verifyItem(productName);
		Assert.assertTrue(match);
		//Assert.assertFalse(match);

		
	}

}


