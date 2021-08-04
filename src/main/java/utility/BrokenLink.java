package utility;

import Base.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLink extends TestBase {

    public static void BrokenLink() throws Exception {

        Thread.sleep(1000);
        List<WebElement> links = getdriver.get().findElements(By.tagName("a"));
        System.out.println("Total links are " + links.size());
        for (WebElement element : links) {
            String url = element.getAttribute("href");
            verifyLink(url);
        }
    }

    public static void verifyLink(String urlLink) throws Exception {
        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();

            switch (httpConn.getResponseCode()) {
                case 200 -> {
                    test.log(Status.PASS, urlLink + " :::: is a Valid link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + ":::: is a Valid link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                }
                case 404 -> {
                    test.log(Status.FAIL, urlLink + " :::: is a Broken link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + " :::: is a Broken link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseCode());
                }
                default -> {
                    test.log(Status.SKIP, urlLink + ":::: Other link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + " :::: Other link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseCode());
                }
            }
        } catch (Exception ignored) {
        }
    }
}