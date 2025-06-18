package com.qa.hippo.utilities.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.hippo.utilities.ExtentReporterNG;
import com.qa.hippo.utilities.HTPLLogger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

    ExtentReports extentReports = ExtentReporterNG.getReportObject();

    // Use ThreadLocal for safe parallel execution
    public static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        extentTestThread.set(test);
        HTPLLogger.setLogger(result.getTestClass().getRealClass(), test);
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThread.get().log(Status.PASS, "Test Passed!!");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThread.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThread.get().log(Status.SKIP, "Test Skipped!!");
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}
