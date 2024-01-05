package com.nikhila.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.nikhila.pageObjects.android.FormPage;
import com.nikhila.utils.AppiumUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class IOSBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass
	public void configureAppium() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\nikhila\\resources\\data.properties");
		prop.load(fis);
		
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
	
		service = startAppiumServer(ipAddress,Integer.parseInt(port));
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("C:\\Users\\NikhilaVecham\\Appium Apps\\chromedriver_win32\\chromedriver.exe");
		options.setApp(System.getProperty("user.dir")+"\\src\\test\\java\\com\\nikhila\\resources\\General-Store.apk");
		//options.setCapability("appPackage","com.androidsample.generalstore");
		//options.setCapability("appActivity", "com.androidsample.generalstore.MainActivity");
		
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		formPage = new FormPage(driver);

	}

	
	
	@AfterClass
	public void tearDown() {

		driver.quit();
		service.stop();
	}
}
