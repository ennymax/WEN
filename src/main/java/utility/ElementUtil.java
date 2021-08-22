package utility;

import Base.TestBase;
import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ElementUtil extends TestBase {

    public static String getPageUrl() {
        return getdriver.get().getCurrentUrl();
    }

    public static boolean isAlertPresent() {
        try {
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public static void AcceptAlert() {
        WebDriver drv = getdriver.get();
        Wait<WebDriver> wait = new WebDriverWait(drv, 10, 2);
        try {
            wait.until((Function<WebDriver, Object>) dr -> isAlertPresent());
            drv.switchTo().alert().accept();
            System.out.println("Alert was Present");
            TimeUnit.SECONDS.sleep(3);

        } catch (Exception e) {
            System.out.println("Alert was Absent");
        }
    }

    public static void CancelAlert() {
        WebDriver drv = getdriver.get();
        Wait<WebDriver> wait = new WebDriverWait(drv, 10, 2);
        try {
            wait.until((Function<WebDriver, Object>) dr -> isAlertPresent());
            drv.switchTo().alert().dismiss();
            System.out.println("Alert was Present");
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            System.out.println("Alert was Absent");
        }
    }


    public static void DoSelectValuesByVisibleText(WebElement locator, String value) throws IOException, InterruptedException {
        Select select = new Select(locator);
        select.selectByVisibleText(value);
    }

    public static void DoSelectValuesByIndex(WebElement locator, int index) throws IOException, InterruptedException {
        Select select = new Select(locator);
        locator.click();
        select.selectByIndex(index);
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

    public static void DoSendKeyEnter(WebElement locator, String actualText){
        locator.sendKeys(actualText + Keys.ENTER);
    }

    public static void CheckBusinessNameExist() throws IOException {
        if (getdriver.get().findElements(By.xpath(("usenamechecker_XPATH"))).size() != 0) {
            Faker faker = new Faker();
            String BusinessName = faker.name().firstName() + faker.name().lastName();

            System.out.println("New Business Name : " + BusinessName);
            //  DoSendkeyFluentWait("Businessname_XPATH", BusinessName, 2, 20);
            //  DoClickFluentWait("NextButton_XPATH", 2, 20);
        } else {
            System.out.println("Business Name Doesn't Exist");
        }
    }

    public static String GetPageTittle(){
        return getdriver.get().getTitle();
    }

}
