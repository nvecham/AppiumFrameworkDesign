package com.nikhila.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.nikhila.utils.AndroidActions;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidActions{

	AndroidDriver driver;
	
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	private WebElement checkbox;
	
	
	public List<WebElement> getProductList() {
		return productList;
	}
	
	public double getProductsSum() {
		int count = productList.size();
		double totalSum = 0;
		
		for(int i=0;i<count;i++) {
			
			String amountString = productList.get(i).getText();
			double price = getFormattedAmount(amountString);
			totalSum = totalSum + price;
			
		}
		
		return totalSum;
	}
	
	

	public double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}
	
	public void acceptTermsandCOnditions() {
		longPressAction(terms);
		acceptButton.click();
	}
	
	public void submitOrder() {
		
		checkbox.click();
		proceedButton.click();
		
	}
	
	
	
}
