package Base;

import Listeners.ExtentReportListener;
import Listeners.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utility.Utility;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase extends ExtentReportListener{

    public static ThreadLocal<WebDriver> getdriver = new ThreadLocal<>();
    EventFiringWebDriver eDriver;
    WebEventListener eventListener;

    @BeforeTest(alwaysRun = true)
    public void Browser() throws IOException {
        switch (Utility.fetchProperty("BrowserName").toString()) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                getdriver.set(new ChromeDriver());
                Builder();
                break;

            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                getdriver.set(new FirefoxDriver());
                Builder();
                break;

            case "ChromeHeadless":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--incognito");
                getdriver.set(new ChromeDriver(options));
                Builder();
                break;

            case "FirefoxHeadless":
                FirefoxOptions option = new FirefoxOptions();
                option.addArguments("--headless");
                option.addArguments("--incognito");
                getdriver.set(new FirefoxDriver(option));
                Builder();
                break;

            case "RemoteFirefox":
                FirefoxOptions caps = new FirefoxOptions();
                caps.setCapability("browserName", "firefox");
                caps.setCapability("enableVNC", true);
                caps.setCapability("console", true);
                try {
                    getdriver.set(new RemoteWebDriver(new URL(Utility.fetchProperty("RemoteURL").toString()), caps));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Builder();
                break;

            case "RemoteChrome":
                ChromeOptions cap = new ChromeOptions();
                cap.setCapability("browserName", "chrome");
                cap.setCapability("enableVNC", true);
                cap.setCapability("console", true);

                try {
                    getdriver.set(new RemoteWebDriver(new URL(Utility.fetchProperty("RemoteURL").toString()), cap));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Builder();
                break;
        }
    }

    public void Builder() throws IOException {

        getdriver.get().manage().window().maximize();

        eDriver = new EventFiringWebDriver(getdriver.get());
        eventListener = new WebEventListener();
        eDriver.register(eventListener);
        getdriver.set(eDriver);

        try {
            getdriver.get().get(Utility.fetchProperty("UrlPROD").toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        getdriver.get().manage().timeouts().pageLoadTimeout(Integer.parseInt((String) Utility.fetchProperty("implicit.wait")), TimeUnit.SECONDS);
    }

    @AfterTest(alwaysRun = true)
    public void Quit() {
        try {
            if (getdriver.get() != null)
                getdriver.get().quit();
        } catch (Exception ignored) {
        }
    }
}