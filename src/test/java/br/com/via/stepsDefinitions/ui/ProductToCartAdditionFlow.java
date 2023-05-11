package br.com.via.stepsDefinitions.ui;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.via.Product;
import br.com.via.pageObjects.HomePage;
import br.com.via.pageObjects.ProductPage;
import br.com.via.stepsDefinitions.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductToCartAdditionFlow {

	private HomePage homePage;
	private ProductPage productPage;
	private WebDriverFactory driverFactory;
	private List<Product> productList;

	@DataTableType
	public Product getProductFromDataTable(Map<String, String> entry) {
		return new Product(
				entry.get("product"), 
				entry.get("size"),
				entry.get("colour"),
				Integer.valueOf(entry.get("quantity")));
	}

	@Before
	public void setup() {
		this.driverFactory = new WebDriverFactory();
		this.homePage = new HomePage(this.driverFactory.getDriver(), this.driverFactory.getDriverWait());
		this.productPage = new ProductPage(this.driverFactory.getDriver(), this.driverFactory.getDriverWait());
		
		System.out.println("-----------------Start of Scenario-----------------");
	}

	@Given("I'm on the selling homepage")
	public void init() {
		this.homePage.navigate();
	}

	@When("I click on the buying menu option")
	public void clickOnBuyingMenuOption() throws InterruptedException {
		this.productPage.clickBuyMenuOption();
	}

	@Then("A new page with a grid containing the products should appear")
	public void verifyProductsList() {
		Assert.assertTrue(productPage.hasProducGridAppeared());
	}

	@Given("I click on the following product with its details")
	public void clickOnProduct(List<Product> products) {
		this.productList = products;
		for (var product : products) {
			this.productPage.clickOnProduct(product.getName());
		}
	}

	@When("I add the product items to the cart")
	public void buyProduct() {
		this.productPage.buyProduct(this.productList);
	}

	@Then("I'll be able to verify if the cart is updated with my products")
	public void verifyCartDetails() {
		for (var product : productList) {
			Assert.assertTrue(this.productPage.isCartUpdated(product.getName(), product.getQuantity()));
		}
	}

	@After
	public void closeDriver() throws InterruptedException {
		System.out.println("-----------------End of Scenario-----------------");
		
		Thread.sleep(2000);
		
		if (this.driverFactory != null) {
			this.driverFactory.quitDriver();
		}
	}

}