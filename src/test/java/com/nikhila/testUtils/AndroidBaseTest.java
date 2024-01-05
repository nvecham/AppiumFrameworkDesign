package com.nikhila.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.nikhila.pageObjects.android.FormPage;
import com.nikhila.utils.AppiumUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AndroidBaseTest extends AppiumUtils {

	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

/*	@BeforeMethod
	public void setUpAppium() throws MalformedURLException {
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    setDesiredCapabilitiesForAndroid(capabilities);
	    driver = new AppiumDriver(new URL(APPIUM_SERVER_URL), capabilities);
	}
	*//**
	 * It will set the DesiredCapabilities for the local execution
	 *
	 * @param desiredCapabilities
	 *//*
	private void setDesiredCapabilitiesForAndroid(DesiredCapabilities desiredCapabilities) {
		String PLATFORM_NAME = PropertyUtils.getProperty("android.platform");
		String PLATFORM_VERSION = PropertyUtils.getProperty("android.platform.version");
		String APP_NAME = PropertyUtils.getProperty("android.app.name");
		String APP_RELATIVE_PATH = PropertyUtils.getProperty("android.app.location") + APP_NAME;
		String APP_PATH = getAbsolutePath(APP_RELATIVE_PATH);
		String DEVICE_NAME = PropertyUtils.getProperty("android.device.name");
		String APP_PACKAGE_NAME = PropertyUtils.getProperty("android.app.packageName");
		String APP_ACTIVITY_NAME = PropertyUtils.getProperty("android.app.activityName");
		String APP_FULL_RESET = PropertyUtils.getProperty("android.app.full.reset");
		String APP_NO_RESET = PropertyUtils.getProperty("android.app.no.reset");
		desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
		desiredCapabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE_NAME);
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY_NAME);
		desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, APP_FULL_RESET);
		desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, APP_NO_RESET);
	        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
	}*/
	
		@BeforeMethod
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
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		formPage = new FormPage(driver);
		
		

	}
	
	@AfterMethod
	public void tearDown() {

		driver.quit();
		service.stop();
	}
	
	
	
	
	
	
}
