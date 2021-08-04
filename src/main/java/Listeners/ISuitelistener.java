package Listeners;

import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ISuitelistener implements ITestListener {

    List<ITestNGMethod> passedtests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> failedtests = new ArrayList<ITestNGMethod>();
    List<ITestNGMethod> skippedtests = new ArrayList<ITestNGMethod>();


    @Override
    public void onTestSuccess(ITestResult result) {
        passedtests.add(result.getMethod());
        System.out.println("***************************Passed********************* " + (result.getMethod().getMethodName() + " ********************Passed******************"));
        System.out.println("***************************Passed********************* " + getTime(result.getEndMillis()) + " ********************Passed****************** \n");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        failedtests.add(result.getMethod());
        System.out.println("***************************Failed********************* " + (result.getMethod().getMethodName() + " ********************Failed******************"));
        System.out.println("***************************Failed********************* " + getTime(result.getEndMillis()) + " ********************Failed****************** \n");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        skippedtests.add(result.getMethod());
        System.out.println("***************************Skipped********************* " + (result.getMethod().getMethodName() + " ********************Skipped******************"));
        System.out.println("***************************Skipped********************* " + getTime(result.getEndMillis()) + " ********************Skipped****************** \n");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {
        /*
        System.out.println("Notify by mail, TestNG is finished");

        Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
        for (ITestResult temp : failedTests) {
            ITestNGMethod method = temp.getMethod();
            if (context.getFailedTests().getResults(method).size() > 1) {
                failedTests.remove(temp);
            } else {
                if (context.getPassedTests().getResults(method).size() > 0) {
                    failedTests.remove(temp);
                }
            }
        }
         */
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}