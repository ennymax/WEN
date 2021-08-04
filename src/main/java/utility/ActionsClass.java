package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

public class ActionsClass extends TestBase {

    public static void DoSendKeysWithMoveToElement(String locator, String value, int timeOut) throws IOException, InterruptedException {
        Actions action = new Actions(getdriver.get());
        action.moveToElement(DoFluentWait(locator, timeOut)).sendKeys(Utility.fetchLocator(value)).build().perform();
        test.log(Status.PASS, locator);
    }

    public static void DoActionSendKeys(String locator, int timeOut) throws IOException, InterruptedException {
        Actions action = new Actions(getdriver.get());
        Faker faker= new Faker();
        action.sendKeys(DoFluentWait(locator, timeOut), faker.name().firstName()).perform();
        test.log(Status.PASS, locator);
    }

    public static void DoSendKeysActionClass(String locator, String actualText, String ObjectName, int timeOut) throws IOException, InterruptedException {
        Actions ac = new Actions(getdriver.get());
        ac.sendKeys((DoFluentWait(locator, timeOut)), Utility.fetchLocator(actualText)).perform();
        test.log(Status.PASS, " " + locator);
    }

    public static void DoClickWithMoveToElement(String locator, int timeOut) throws IOException, InterruptedException {
        Actions action = new Actions(getdriver.get());
        action.moveToElement(DoFluentWait(locator, timeOut)).click().build().perform();
        test.log(Status.PASS, locator);
    }

    public static void DoDoubleClickAction(String locator, int timeOut) throws IOException, InterruptedException {
        Actions action = new Actions(getdriver.get());
        action.moveToElement(DoFluentWait(locator, timeOut)).doubleClick().perform();
        test.log(Status.PASS, locator);
    }

    public static void DoClickActionClass(String locator, int timeOut) throws IOException, InterruptedException {
        Actions ac = new Actions(getdriver.get());
        ac.click(DoFluentWait(locator, timeOut)).perform();
        test.log(Status.PASS, locator);
    }

    public static void DoClearActionClass(String locator, int timeOut) throws IOException, InterruptedException {
        Actions ac = new Actions(getdriver.get());
        ac.sendKeys((DoFluentWait(locator, timeOut)), Keys.DELETE).perform();
        test.log(Status.PASS, locator);
    }

    public static void DoHoverEffectClick(String locator, int timeOut) throws IOException, InterruptedException {
        Actions actions = new Actions(getdriver.get());
        actions.moveToElement(DoFluentWait(locator, timeOut));
        actions.click().build().perform();
        test.log(Status.PASS, locator);
    }


    public static WebElement DoFluentWait(final String locator, int timeOut) throws IOException, InterruptedException {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(getdriver.get())
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(2))
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

        return element;
    }

}
