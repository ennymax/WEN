package utility;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

public class Iframe extends TestBase {

    public static void DoSwitchToFrame(int frameIndex, int sleepindex) throws InterruptedException {
        Thread.sleep(sleepindex);
        getdriver.get().switchTo().frame(frameIndex);
    }

    public static void IframeFinder() {
        List<WebElement> elements = getdriver.get().findElements(By.tagName("iframe"));
        int numberOfTags = elements.size();
    }
}