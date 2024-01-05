package com.nikhila;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;
import com.nikhila.testUtils.AndroidBaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class eCommerce_tc_1 extends AndroidBaseTest {
	
	@BeforeMethod
	public void preSetup() {
		
		
		//Activity activity = new Activity("package","activity");
        //((AndroidDriver) driver).startActivity("package", "activity");
		//‘mobile: startActivity’
		//screen to home page
		//Activity activity = new Activity("com.androidsample.generalstore","com.androidsample.generalstore.MainActivity");
		//(driver).startActivity(activity);
		//((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
		driver.executeScript("mobile: startActivity",  ImmutableMap.of("intent","com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity"));
	}
	
	@Test
	public void fillForm_ErrorValidation() {
		
		//driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nikhila Vecham");
		driver.hideKeyboard();
		
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		String toastMessage = driver.findElement(By.xpath("//(android.widget.Toast)[1]")).getAttribute("name");
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
	
	@Test
	public void fillForm_PositiveFlow() {
		
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Nikhila Vecham");
		driver.hideKeyboard();
		
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))"));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Argentina']")).click();
		
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		Assert.assertTrue(driver.findElements(By.xpath("//(android.widget.Toast)[1]")).size()<1);
		
		
	}

}
