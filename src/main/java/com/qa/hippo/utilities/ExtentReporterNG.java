package com.qa.hippo.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path=System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);
        reporter.config().setReportName("POS-Frontend Automation Result");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extentReports=new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Name","Niyaz Ahmad");
        extentReports.setSystemInfo("Designation", "QA Executive");
        return extentReports;
    }

}
