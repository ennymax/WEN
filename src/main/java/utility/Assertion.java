package utility;

import Base.TestBase;
import Listeners.ExtentReportListener;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;
import static org.testng.AssertJUnit.assertEquals;
import static utility.ExcelUtil.DoFluentWait;

@Listeners(ExtentReportListener.class)
public class Assertion extends TestBase {

    public static void DoCheckUrl(int timeOut, String Url){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(getdriver.get())
                    .withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.urlContains(Utility.fetchLocator(Url)));
        } catch (Exception e) {
            System.out.println("Tittle Didn't match");
        }
    }

    public static void DoCheckTittle(String tittle, int timeOut){
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(getdriver.get())
                    .withTimeout(Duration.ofSeconds(timeOut))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.titleIs(Utility.fetchLocator(tittle)));
        } catch (Exception e) {
            System.out.println("Tittle Didn't match");
        }
    }

    public static void DoCheckAlert(int timeout) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(getdriver.get())
                    .withTimeout(Duration.ofSeconds(timeout))
                    .pollingEvery(Duration.ofSeconds(2))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = getdriver.get().switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            System.out.println("Alert Not Present");
        }
    }


    public static void DoAssertContain(String locator, String Containstext, int Retry, int timeOut) throws IOException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getdriver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(Retry))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                try {
                    return getdriver.get().findElement(By.xpath(Utility.fetchLocator(locator)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        String text11 = element.getText();
        if (element.isEnabled() && text11.contains(Utility.fetchLocator(Containstext))) {
            test.get().pass("Element was Present in the Dome");
        } else {
            test.get().fail("Element Not Found in the Dome");
        }
    }

    public static void DoAssertXpathPresent(String locator, int timeOut) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(getdriver.get(), timeOut);
        if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Utility.fetchLocator(locator)))).isDisplayed()) {
            test.get().pass("Element was Present in the Dome");
        } else {
            test.get().fail("Element Not Found in the Dome");
        }
    }

    public static void DoAssertEqual(String locator, String assertionString, int timeOut) throws IOException {
        try {
            assertEquals(Utility.fetchLocator(assertionString), DoFluentWait(locator, timeOut).getText());
            test.get().pass("Element is Present in the DOM");
        } catch (Throwable e) {
            System.out.println(getdriver.get().findElement(By.xpath(Utility.fetchLocator(locator))).getText());
            test.get().fail("Element is not Present in the DOM");
        }
    }
}
