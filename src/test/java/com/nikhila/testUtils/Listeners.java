package com.nikhila.testUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.nikhila.utils.AppiumUtils;

public class Listeners extends AppiumUtils implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		//screenshot code
		test.fail(result.getThrowable());
		//test.addScreenCaptureFromPath(getScreenShotPath(result.getMethod().getMethodName(), driver), null)
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
