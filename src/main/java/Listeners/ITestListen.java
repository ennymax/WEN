package Listeners;

import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class ITestListen implements ITestListener {

    @SneakyThrows
    public void onStart(ITestContext context) {
    }

    @SneakyThrows
    public void onFinish(ITestContext context) {
        TimeUnit.SECONDS.sleep(2);
    }

    @SneakyThrows
    public void onTestStart(ITestResult result) {
    }

    public void onTestSuccess(ITestResult result) {

    }

    @SneakyThrows
    public void onTestFailure(ITestResult result) {

    }

    @SneakyThrows
    public void onTestSkipped(ITestResult result) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
}