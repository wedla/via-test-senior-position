package br.com.via.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.via.Product;
import br.com.via.utils.ButtonNameEnum;

public class ProductPage {
	private WebDriver driver;
	private WebDriverWait driverWait;
	
	@FindBy(how = How.NAME, using = "product_cat")
	private WebElement categoryDropDownSelect;
	
	@FindBy(how = How.CLASS_NAME, using = "products-grid")
	private WebElement productsGrid;
	
	@FindBy(how = How.CLASS_NAME, using = "tbay-megamenu")
	private WebElement menuNavbar;
	
	@FindBy(how = How.CLASS_NAME, using = "variations")
	private WebElement variationsTable;
	
	@FindBy(how = How.CLASS_NAME, using = "plus")
	private WebElement plusButton;
	
	@FindBy(how = How.CLASS_NAME, using = "qty")
	private WebElement quantityToAdd;
	
	@FindBy(how = How.CLASS_NAME, using = "single_add_to_cart_button")
	private WebElement buyButton;
	
	@FindBy(how = How.CLASS_NAME, using = "woocommerce-notices-wrapper")
	private WebElement productAddedOnCartAlert; 
	
	public ProductPage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;

		PageFactory.initElements(this.driver, this);
	}

	public void clickBuyMenuOption() throws InterruptedException {
		var items = this.menuNavbar.findElements(By.tagName("li"));
		
		for (var item : items) {
			if (item.getText().equals(ButtonNameEnum.BUY_BUTTON_LABEL.getLabel())) {
				item.click();
				break;
			}
		}
		
		this.driverWait.until(ExpectedConditions.invisibilityOf(this.categoryDropDownSelect));
	}

	public boolean hasProducGridAppeared() {
		try {
			this.driverWait.until(ExpectedConditions.visibilityOf(this.productsGrid));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clickOnProduct(String expectedProduct) {
		var items = this.productsGrid.findElements(By.className("name"));
		
		for (var item : items) {
			if (item.getText().equals(expectedProduct)) {
				var clickable = item.findElement(By.tagName("a"));
				clickable.click();
				break;
			}
		}
		
		this.driverWait.until(ExpectedConditions.urlContains(expectedProduct.toLowerCase().replace(" ", "-")));
	}

	public void buyProduct(List<Product> products) {
		WebElement sizeRadioButton;
		WebElement colourRadioButton;
		
		for (var product : products) {
			sizeRadioButton = this.variationsTable.findElement(By.className("button-variable-item-" + product.getSize()));
			colourRadioButton = this.variationsTable.findElement(By.className("button-variable-item-" + product.getColour()));
			
			sizeRadioButton.click();
			colourRadioButton.click();
			
			this.driverWait.until(ExpectedConditions.elementToBeClickable(plusButton));
			
			for (var i = 1; i < product.getQuantity(); i++) {
				this.plusButton.click();
			}
			
			this.buyButton.click();
			
			this.driverWait.until(ExpectedConditions.textToBePresentInElementValue(quantityToAdd, String.valueOf(product.getQuantity())));
			this.driverWait.until(ExpectedConditions.visibilityOf(this.productAddedOnCartAlert));
		}
		
	}

	public boolean isCartUpdated(String productName, int quantity) {
		return this.productAddedOnCartAlert.getText().contains(productName) &&
				this.productAddedOnCartAlert.getText().contains(String.valueOf(quantity));
	}
}
