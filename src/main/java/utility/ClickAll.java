package utility;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ClickAll extends TestBase {

    public ClickAll(String locator) throws IOException {
        List<WebElement> checkBoxList = getdriver.get().findElements(By.className(Utility.fetchLocator(locator)));
        checkBoxList.forEach(WebElement::click);
    }
}
