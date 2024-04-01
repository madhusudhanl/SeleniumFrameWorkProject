package StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.baseClass;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameWork.PageObject.ConfrimationPage;
import seleniumFrameWork.PageObject.LandingPage;
import seleniumFrameWork.PageObject.ProdCatalog;
import seleniumFrameWork.PageObject.cartPage;
import seleniumFrameWork.PageObject.placeOrderPage;

public class StepDefinitionImpl extends baseClass {
	
	public LandingPage lp;
	public ProdCatalog prodCatalog;
	public cartPage cp;
	public placeOrderPage pop;
	public ConfrimationPage conPage;
	
	
	@Given("I landed on application")
	public void i_landed_on_application() throws IOException {
	
		lp = startApplication();
	}

	@Given("^I want to login with (.+) and (.+)$")
	public void i_want_to_login_with_username_and_password(String UserName, String Password) {
		
		
		prodCatalog = lp.loginApplication(UserName, Password);
	}
	
	@When("^I want select the(.+)and add to cart$")
	public void i_want_select_product_and_add_to_cart(String productName) {
		
		
		List<WebElement> products = prodCatalog.getProductsList();
		prodCatalog.addToCart(productName.trim());
		
	}
	
	@When("^Verify the cart page for (.+) and checkout$")
	public void verify_the_cart_page_for_product_and_checkout(String productName) {
		
		cp = prodCatalog.goToCartPage();
		boolean match = cp.verifyItem(productName.trim());
		Assert.assertTrue(match);
		pop = cp.checkOut();
		
	}
	
	@Then("^I Complete the payment by selecting (.+)$")
	public void i_complete_the_payment_by_seleting_country(String country) throws InterruptedException {
		
		pop.selectCountry(country);
		conPage = pop.submitOrder();

	}
	
	@Then("^check for confirmation message.(.+)$")
	public void check_for_confrimation_message(String outputString) {
		
		String actualString = conPage.getMessage();
		Assert.assertEquals(actualString, outputString);      
		//driver.close();
	}
	
	//When I check for error message "Incorrect email or password."
	
	@When("^I check for error message(.+)$")
	public void iCheckForErrorMessage(String message){
		
		String actualmsg = lp.getErrorMessage();
		//System.out.println(actualmsg);
		Assert.assertEquals(actualmsg, message.trim());
		
	}
	
	@After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

