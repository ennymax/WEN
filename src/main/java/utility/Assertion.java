package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;
import static org.testng.AssertJUnit.assertEquals;
import static utility.ExcelUtil.DoFluentWait;

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

    public void DoCheckAlert(int timeout) {
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


    public static void DoAssertContain(String locator, String Containstext, String Passmassage, String Failedmessage, int Retry, int timeOut) throws IOException{
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
            test.log(Status.PASS, Utility.fetchLocator(Passmassage));
        } else {
            test.log(Status.FAIL, Utility.fetchLocator(Failedmessage));
        }
    }

    public static void DoAssertCheckBoxSelected(String locator,String DisplayPassmsg, String DisplayFailmsg, int timeOut) throws IOException {
        WebDriverWait wait = new WebDriverWait(getdriver.get(), timeOut);
        WebElement check_box1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Utility.fetchLocator(locator))));
        if(check_box1.isSelected()) {
            test.log(Status.PASS, Utility.fetchLocator(DisplayPassmsg));
        } else {
            check_box1.click();
            test.log(Status.PASS, Utility.fetchLocator(DisplayPassmsg));
        }
    }


    public static void DoAssertXpathPresent(String locator, String DisplayPassmessage, String DisplayFailmessage, int timeOut) throws IOException, InterruptedException {
        WebDriverWait wait = new WebDriverWait(getdriver.get(), timeOut);
        if (wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(Utility.fetchLocator(locator)))).isDisplayed()) {
            test.log(Status.PASS, Utility.fetchLocator(DisplayPassmessage));
        } else {
            test.log(Status.FAIL, Utility.fetchLocator(DisplayFailmessage));
        }
    }

    public static void DoAssertEqual(String locator, String assertionString, int timeOut) throws IOException {
        try {
            assertEquals(Utility.fetchLocator(assertionString), DoFluentWait(locator, timeOut).getText());
            test.log(Status.PASS, "Element is Present in the DOM");
        } catch (Throwable e) {
            System.out.println(getdriver.get().findElement(By.xpath(Utility.fetchLocator(locator))).getText());
            test.log(Status.FAIL, "Element is not Present in the DOM");
        }
    }
}
