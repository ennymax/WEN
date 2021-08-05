package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class ExcelUtil extends TestBase {

    public static void DoSendKey(String locator, String actualText, int timeOut) throws IOException {
        DoFluentWait(locator, timeOut).clear();
        DoFluentWait(locator, timeOut).sendKeys(Utility.fetchLocator(actualText));
        test.log(Status.PASS, locator);
    }

    public static void DoSendKeysEmail(String locator, int timeOut) {
        DoFluentWait(locator, timeOut).clear();
        DoFluentWait(locator, timeOut).sendKeys();
        test.log(Status.PASS, locator);
    }

    public static void DoSendKeysRandomNumber(String locator, int timeOut) {
        DoFluentWait(locator, timeOut).clear();
        DoFluentWait(locator, timeOut).sendKeys();
        test.log(Status.PASS, locator);
    }

    public static void DoClick(String locator, int timeOut) throws IOException {
        DoFluentWait(locator, timeOut).click();
        test.log(Status.PASS, Utility.fetchLocator(locator));
    }

    public static void DoClickFromList(WebDriver driver, String locator, String linkText) throws IOException {
        List<WebElement> footerList = driver.findElements(By.xpath(Utility.fetchLocator(locator)));
        for (int i = 0; i < footerList.size(); i++) {
            String text = footerList.get(i).getText();
            if (text.equals(linkText)) {
                footerList.get(i).click();
                break;
            }
        }
    }

    public static void DoSelectValuesByVisibleText(String locator, String value, int timeOut) throws IOException {
        Select select = new Select(DoFluentWait(locator, timeOut));
        select.selectByVisibleText(Utility.fetchLocator(value));
        test.log(Status.PASS, locator);
    }

    public static void DoSelectValuesByIndex(String locator, int index, int timeOut) {
        Select select = new Select(DoFluentWait(locator, timeOut));
        select.selectByIndex(index);
        test.log(Status.PASS, locator);
    }

    public static WebElement DoFluentWait(final String locator, int timeOut) {
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

    public static String Password(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial) {
        Faker faker = new Faker();
        if (includeSpecial) {
            char[] password = faker.lorem().characters(minimumLength, maximumLength, includeUppercase).toCharArray();
            char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
            for (int i = 0; i < faker.random().nextInt(minimumLength); i++) {
                password[faker.random().nextInt(password.length)] = special[faker.random().nextInt(special.length)];
            }
            return new String(password);
        } else {
            return faker.lorem().characters(minimumLength, maximumLength, includeUppercase);
        }
    }

    public static void DoSendKeyEnter(String locator, String actualText, int timeOut) throws IOException {
        DoFluentWait(locator, timeOut).clear();
        DoFluentWait(locator, timeOut).sendKeys(Utility.fetchLocator(actualText) + Keys.ENTER + Keys.ENTER + Keys.ENTER);
        test.log(Status.PASS, locator);
    }

    public static void CheckBusinessNameExist() throws IOException {
        if (getdriver.get().findElements(By.xpath(Utility.fetchLocator("usenamechecker_XPATH"))).size() != 0) {
            Faker faker = new Faker();
            String BusinessName = faker.name().firstName() + faker.name().lastName();

            System.out.println("New Business Name : " + BusinessName);
            //  DoSendkeyFluentWait("Businessname_XPATH", BusinessName, 2, 20);
            //  DoClickFluentWait("NextButton_XPATH", 2, 20);
        } else {
            System.out.println("Business Name Doesn't Exist");
        }
    }

    public void selectAllCheckboxes() throws IOException {
        List<WebElement> checkboxes = getdriver.get().findElements(By.xpath(Utility.fetchLocator("usenamechecker_XPATH")));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    /**
     * Verify all available checkboxes are checked. If at least one unchecked,
     * return false
     */
    public boolean AreAllCheckboxesChecked() throws IOException {
        List<WebElement> checkboxes = getdriver.get().findElements(By.xpath(Utility.fetchLocator("usenamechecker_XPATH")));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                return false;
            }
        }
        return true;
    }

}
