package utility;

import Base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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

    public static void verifyLink(String urlLink) {
        try {
            URL link = new URL(urlLink);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            httpConn.setConnectTimeout(2000);
            httpConn.connect();

            switch (httpConn.getResponseCode()) {
                case 200 :
                    test.get().pass(urlLink + " :::: is a Valid link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + ":::: is a Valid link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    break;

                case 404:
                    test.get().fail(urlLink + " :::: is a Broken link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + " :::: is a Broken link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseCode());
                    break;

                default :
                    test.get().info(urlLink + ":::: Other link ::::" + httpConn.getResponseCode() + " " + httpConn.getResponseMessage());
                    System.out.println(urlLink + " :::: Other link :::: " + httpConn.getResponseCode() + " " + httpConn.getResponseCode());
                    break;
            }
        } catch (Exception ignored) {
        }
    }
}