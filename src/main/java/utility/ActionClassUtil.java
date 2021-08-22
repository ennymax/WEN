package utility;

import Base.TestBase;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ActionClassUtil extends TestBase {

    public static void DoSendKeysWithMoveToElement(WebElement locator, String value) {
        Actions action = new Actions(getdriver.get());
        action.moveToElement(locator).sendKeys(value).build().perform();
    }

    public static void DoActionSendKeys(WebElement locator) {
        Actions action = new Actions(getdriver.get());
        Faker faker= new Faker();
        action.sendKeys(locator, faker.name().firstName()).perform();
    }

    public static void DoSendKeysActionClass(WebElement locator, String actualText) throws IOException {
        Actions ac = new Actions(getdriver.get());
        ac.sendKeys((locator), actualText).perform();
    }

    public static void DoClickWithMoveToElement(WebElement locator){
        Actions action = new Actions(getdriver.get());
        action.moveToElement(locator).click().build().perform();
    }

    public static void DoDoubleClickAction(WebElement locator){
        Actions action = new Actions(getdriver.get());
        action.moveToElement(locator).doubleClick().perform();
    }

    public static void DoClickActionClass(WebElement locator) {
        Actions ac = new Actions(getdriver.get());
        ac.click(locator).perform();
    }

    public static void DoClearActionClass(WebElement locator) {
        Actions ac = new Actions(getdriver.get());
        ac.sendKeys(locator, Keys.DELETE).perform();
    }

    public static void DoHoverEffectClick(WebElement locator){
        Actions actions = new Actions(getdriver.get());
        actions.moveToElement((locator));
        actions.click().build().perform();
    }

}
