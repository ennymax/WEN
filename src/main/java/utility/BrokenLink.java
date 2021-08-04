package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLink extends TestBase {

    public static void BrokenLink() throws Exception {

        Thread.sleep(3000);
        List<WebElement> links = getdriver.get().findElements(By.tagName("a"));
        System.out.println("Total links are " + links.size());
        for (int i = 0; i < links.size(); i++) {
            WebElement element = links.get(i);
            String url = element.getAttribute("href");
            verifyLink(url);
        }
    }

    public static void verifyLink(String urlLink) throws Exception {

        URL link = new URL(urlLink);
        HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
        httpConn.setConnectTimeout(2000);
        httpConn.connect();
        if (httpConn.getResponseCode() == 200) {
            test.log(Status.PASS, urlLink + " :::: is a Valid link :::: " + httpConn.getResponseMessage());
            System.out.println(urlLink + ":::: is a Valid link ::::" + httpConn.getResponseMessage());
        } else if (httpConn.getResponseCode() == 404) {
            test.log(Status.FAIL, urlLink + " :::: is a Broken link ::::" + httpConn.getResponseMessage());
            System.out.println(urlLink + " :::: is a Broken link :::: " + httpConn.getResponseCode());
        } else {
            test.log(Status.SKIP, urlLink + ":::: Other link ::::" + httpConn.getResponseMessage());
            System.out.println(urlLink + " :::: Other link :::: " + httpConn.getResponseCode());
        }

    }
}