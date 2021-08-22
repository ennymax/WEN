package Base;

import Listeners.ExtentReport;
import Listeners.WebEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.*;
import pages.DashboardPage;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static utility.Utility.fetchvalue;

public abstract class TestBase extends ExtentReport {

    public LoginPage loginPage;
    public DashboardPage dashboardPage;

    public static ThreadLocal<WebDriver> getdriver = new ThreadLocal<>();
    EventFiringWebDriver eDriver;
    WebEventListener eventListener;


    @BeforeClass
    public void PageObject() {
        loginPage = new LoginPage();
        dashboardPage = new DashboardPage();
    }

    @BeforeTest(alwaysRun = true)
    public void SetUp() {
        switch (fetchvalue("BrowserName")) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                getdriver.set(new ChromeDriver());
                break;

            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                getdriver.set(new FirefoxDriver());
                break;

            case "ChromeHeadless":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--headless");
                getdriver.set(new ChromeDriver(chromeOptions));
                break;

            case "FirefoxHeadless":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions option = new FirefoxOptions();
                option.addArguments("--headless");
                option.addArguments("--incognito");
                getdriver.set(new FirefoxDriver(option));
                break;

            case "RemoteFirefox":
                FirefoxOptions caps = new FirefoxOptions();
                caps.setCapability("browserName", "firefox");
                caps.setCapability("enableVNC", true);
                caps.setCapability("console", true);
                caps.setCapability("visual",true);
                caps.setCapability("version","latest");

                try {
                    getdriver.set(new RemoteWebDriver(new URL(fetchvalue("RemoteURL").toString()), caps));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case "RemoteChrome":
                ChromeOptions cap = new ChromeOptions();
                cap.setCapability("browserName", "chrome");
                cap.setCapability("enableVNC", true);
                cap.setCapability("console", true);
                cap.setCapability("visual",true);
                cap.setCapability("version","latest");

                try {
                    getdriver.set(new RemoteWebDriver(new URL(fetchvalue("RemoteURL")), cap));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }

        getdriver.get().manage().window().maximize();

        eDriver = new EventFiringWebDriver(getdriver.get());
        eventListener = new WebEventListener();
        eDriver.register(eventListener);
        getdriver.set(eDriver);

        getdriver.get().get(fetchvalue("UrlPROD"));

        getdriver.get().manage().timeouts().pageLoadTimeout(Integer.parseInt((String) fetchvalue("PageLoad.wait")), TimeUnit.SECONDS);
        getdriver.get().manage().timeouts().implicitlyWait(Integer.parseInt((String) fetchvalue("implicit.wait")), TimeUnit.SECONDS);

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