package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.util.List;

public class DatePicker extends TestBase {

    public static void DatePickerJE(String locator, String Date) throws IOException, InterruptedException {
        WebElement select = getdriver.get().findElement(By.xpath(Utility.fetchLocator(locator)));
        JavascriptExecutor js = (JavascriptExecutor) getdriver.get();
        js.executeScript("arguments[0].type = arguments[1]", select, "text");
        select.clear();
        select.sendKeys(Date);
        Thread.sleep(500);
        test.log(Status.PASS, locator);
    }

    public static void SetDate(String Dater, String locator, String Day) {
        getdriver.get().findElement(By.xpath(Dater)).click();
        List<WebElement> list = getdriver.get().findElements(By.xpath(locator));

        for(WebElement e : list) {
            if(e.getText().equals(Day)) {
                e.click();
                break;
            }
        }
    }
}