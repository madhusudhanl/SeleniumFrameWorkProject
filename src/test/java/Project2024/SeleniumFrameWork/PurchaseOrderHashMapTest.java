package Project2024.SeleniumFrameWork;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
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
import org.testng.annotations.DataProvider;
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

public class PurchaseOrderHashMapTest extends baseClass {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "sendHashMapData",groups = {"PurchaseOrderHashMap"})

	public void submitOrderTest(HashMap<String,String> input) throws IOException, InterruptedException  {
		
		
		
		String countryName = "India";
			
		// to select product
		ProdCatalog prodCatalog = lp.loginApplication(input.get("email"), input.get("pass"));
		List<WebElement> products = prodCatalog.getProductsList();

		// To click on add to cart button
		prodCatalog.addToCart(input.get("productName"));

		// goto cart page
		cartPage cp = prodCatalog.goToCartPage();

		boolean match = cp.verifyItem(input.get("productName"));
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
	
//	@DataProvider                              // General way doing dataProvider
//	public Object[][] getData() {
//		Object[][] Data = new Object[2][3]; 
//		
//		Data[0][0] = "madhus149@gmail.com";
//		Data[0][1] = "Madhu@1991";
//		Data[0][2] = "ZARA COAT 3";
//			
//		Data[1][0] = "madhus149@gmail.com";
//		Data[1][1] = "Madhu@1991";
//		Data[1][2] = "ADIDAS ORIGINAL";
//		return Data;
//			
//	}
//	
//	@DataProvider										// Json type DataProvider
//	public Object[][] sendData() {
//		return new Object[][] {
//			
//			{"madhus149@gmail.com","Madhu@1991","ZARA COAT 3"} ,
//			{"madhus149@gmail.com","Madhu@1991","ADIDAS ORIGINAL"}
//			
//			};
//	}
	
//	@DataProvider										//Hash Map DataProvider
//	public Object[][] putData() {
//		HashMap<Object,Object> map = new HashMap<Object,Object>();
//		map.put("email", "madhus149@gmail.com");
//		map.put("pass", "Madhu@1991");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<Object,Object> map2 = new HashMap<Object,Object>();
//		map2.put("email", "madhus149@gmail.com");
//		map2.put("pass", "Madhu@1991");
//		map2.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map2}};
//	}

	@DataProvider										//Hash Map DataProvider from Json file
	public Object[][] sendHashMapData() throws IOException {
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\Data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
