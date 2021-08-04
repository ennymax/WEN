package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import static utility.ActionsClass.DoFluentWait;

public class JavaScriptUtil extends TestBase {

    public static boolean DoIsElementPresent(String locator, int timeOut) {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        try {
            Object obj = jse.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;",
                    DoFluentWait(locator, timeOut));
            if (obj.toString().contains("true")) {
                System.out.println("Element is not Present in the DOM");
                return true;
            } else {
                System.out.println("Element is Present in thd DOME");
            }

        } catch (NoSuchElementException | IOException | InterruptedException e) {
            System.out.println("isElementPresentCheckUsingJavaScriptExecutor: FAIL");
        }
        return false;
    }

    public static void DOGetPageLoadTime() {
        final JavascriptExecutor js = (JavascriptExecutor) getdriver.get();
        double loadTime = (Double) js.executeScript("return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
        System.out.print(loadTime + " seconds");
    }

    public static void DoScrollIntoView(String locator, int timeOut) throws IOException, InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("arguments[0].scrollIntoView();", DoFluentWait(locator, timeOut));
        test.log(Status.PASS, locator);
    }

    public static void DoClick(String locator, int timeOut) throws IOException, InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) getdriver.get();
        executor.executeScript("arguments[0].click();", DoFluentWait(locator, timeOut));
        test.log(Status.PASS, locator);
    }

    public static void DoZoomPercentage(int Percentage) {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("document.body.style.zoom = '" + Percentage + "%" + "';");
    }

    public static void DoSendKeys(String locator, String value, int timeOut) throws IOException, InterruptedException {
        WebElement element = getdriver.get().findElement(By.xpath(Utility.fetchLocator(locator)));
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("arguments[0].value=" + value + ";", DoFluentWait(locator, timeOut));
    }
}
