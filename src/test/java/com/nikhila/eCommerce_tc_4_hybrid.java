package com.nikhila;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nikhila.pageObjects.android.CartPage;
import com.nikhila.pageObjects.android.ProductCatalogue;
import com.nikhila.testUtils.AndroidBaseTest;

public class eCommerce_tc_4_hybrid extends AndroidBaseTest {

	@Test(dataProvider = "getData")
	public void fillForm(HashMap<String, String> input) throws InterruptedException {

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));

		ProductCatalogue productCatalogue = formPage.submitForm();

		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);

		CartPage cartPage = productCatalogue.goToCartPage();
		// Thread.sleep(2000);

		double totalSum = cartPage.getProductsSum();
		double displayFormattedSum = cartPage.getTotalAmountDisplayed();

		Assert.assertEquals(totalSum, displayFormattedSum);

		cartPage.acceptTermsandCOnditions();
		cartPage.submitOrder();

		/*
		 * Thread.sleep(6000);
		 * 
		 * Set<String> contexts = driver.getContextHandles();
		 * 
		 * for(String contextName : contexts) { System.out.println(contextName); }
		 * 
		 * driver.context("WEBVIEW_com.androidsample.generalstore");
		 * driver.findElement(By.name("q")).sendKeys("Nikhila Vecham");
		 * driver.findElement(By.name("q")).sendKeys(Keys.ENTER); driver.pressKey(new
		 * KeyEvent(AndroidKey.BACK)); driver.context("NATIVE_APP");
		 */
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//com//nikhila//testData//eCommerce.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

	/*
	 * @BeforeMethod public void preSetup() {
	 * 
	 * 
	 * //driver.activateApp("com.androidsample.generalstore");
	 * 
	 * driver.runAppInBackground(Duration.ofSeconds(20));
	 * driver.activateApp("com.androidsample.generalstore");
	 * 
	 * }
	 */

}
