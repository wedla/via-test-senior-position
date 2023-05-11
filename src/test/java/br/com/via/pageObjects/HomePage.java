package br.com.via.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.via.utils.WebPageAddressEnum;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait driverWait;
	
	@FindBy(how = How.CLASS_NAME, using = "tbay-megamenu")
	private WebElement menuNavbar;
	
	@FindBy(how = How.ID, using = "product_cat")
	private WebElement categoryDropDownSelect;

	public HomePage(WebDriver driver, WebDriverWait driverWait) {
		this.driver = driver;
		this.driverWait = driverWait;

		PageFactory.initElements(this.driver, this);
	}

	public void navigate() {
		this.driver.manage().window().maximize();
		this.driver.get(WebPageAddressEnum.HOME_PAGE.getUrl());
		this.driverWait.until(ExpectedConditions.visibilityOf(this.menuNavbar));
	}
}
