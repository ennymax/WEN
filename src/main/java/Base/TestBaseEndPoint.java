package main.java.Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static Base.TestBase.getTime;
import static org.testng.ITestResult.SUCCESS;

public class TestBaseEndPoint {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static void TokenMan() throws Exception {
      //  WriteExcelData("./Config/TestData.xlsx", LoginSSO_Prod.GetAuthToken("tenantID_Text", "email_Text", "password_Text"));
        TimeUnit.SECONDS.sleep(1);
    }

    @AfterMethod(alwaysRun = true)
    public void wait(ITestResult result) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
    }

    @BeforeSuite(alwaysRun = true)
    public void ExtentSetUp() throws Exception {
        TokenMan();
        TimeUnit.SECONDS.sleep(1);

        Path path = Paths.get("./Report/");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ExtentSparkReporter spark = new ExtentSparkReporter("./Report/" + "Report" + System.currentTimeMillis() + ".html");

        extent = new ExtentReports();
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(spark);

        extent.setSystemInfo("Hostname", "LocalHost");
        extent.setSystemInfo("TesterName", utility.Utility.fetchProperty("Tname").toString());
        extent.setSystemInfo("OS", "Window10");
        extent.setSystemInfo("Browser", utility.Utility.fetchProperty("browserName").toString());
    }

    @BeforeMethod(alwaysRun = true)
    public void GetTestName(ITestResult result){
        test = extent.createTest(result.getMethod().getMethodName().toUpperCase(), result.getMethod().getDescription());
    }

    @AfterMethod(alwaysRun = true)
    public void Reporter(ITestResult result){
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        test.assignCategory(result.getTestContext().getSuite().getName().toUpperCase());
        test.getModel().setStartTime(getTime(result.getStartMillis()));
        test.getModel().setEndTime(getTime(result.getEndMillis()));
        test.getModel().setDescription(result.getMethod().getDescription());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.getModel().setEndTime(getTime(result.getEndMillis()));
            test.fail(result.getThrowable());
            test.assignCategory(className.toUpperCase());
            test.fail(MarkupHelper.createLabel(result.getName().toUpperCase() + " TEST CASE FAILED", ExtentColor.RED));
            System.out.println("*************Failed*************** " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " **********Failed**********"));
        } else if (result.getStatus() == SUCCESS) {
            test.getModel().setEndTime(getTime(result.getEndMillis()));
            test.assignCategory(className.toUpperCase());
            test.pass(MarkupHelper.createLabel(result.getName().toUpperCase() + " TEST CASE PASSED", ExtentColor.GREEN));
            System.out.println("*************Passed*************** " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " *********Passed*************"));
        }
        extent.flush();
    }
}