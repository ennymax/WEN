package utility;

import Base.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class JavaScriptUtil extends TestBase {

    public static boolean CheckElementPresent(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        try {
            Object obj = jse.executeScript("return typeof(arguments[0]) != 'undefined' && arguments[0] != null;",
                    element);
            if (obj.toString().contains("true")) {
                System.out.println("WebElement Found: SUCCESS");
                return true;
            } else {
                System.out.println("WebElement Not Found: FAIL");
            }

        } catch (NoSuchElementException e) {
            System.out.println("WebElement Not Found: FAIL");
        }
        return false;
    }

    public static void DOGetPageLoadTime() {
        final JavascriptExecutor js = (JavascriptExecutor) getdriver.get();
        double loadTime = (Double) js.executeScript("return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
        System.out.print(loadTime + " seconds");
    }

    public static void DoScrollIntoView(WebElement locator){
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("arguments[0].scrollIntoView();", locator);
    }

    public static void DoClick(WebElement locator) {
        JavascriptExecutor executor = (JavascriptExecutor) getdriver.get();
        executor.executeScript("arguments[0].click();", locator);
    }

    public static void DoZoomPercentage(int Percentage) {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("document.body.style.zoom = '" + Percentage + "%" + "';");
    }

    public static void DoSendKeys(String locator, String value) {
        JavascriptExecutor jse = (JavascriptExecutor) getdriver.get();
        jse.executeScript("arguments[0].value=" + value + ";", locator);
    }

    public static void DatePickerJE(WebElement locator, String Date) throws IOException, InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getdriver.get();
        js.executeScript("arguments[0].type = arguments[1]", locator, "text");
        locator.clear();
        locator.sendKeys(Date);
    }
}
