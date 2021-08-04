package Base;

import Listeners.WebEventListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.Utility;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.testng.ITestResult.SUCCESS;

public class TestBase {

    public static ThreadLocal<WebDriver> getdriver = new ThreadLocal<>();

    public static ExtentReports extent;
    public static ExtentTest test;
    EventFiringWebDriver eDriver;
    WebEventListener eventListener;

    @BeforeSuite(alwaysRun = true)
    public void setup() throws Exception {

        Path path = Paths.get("./Report/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        ExtentSparkReporter spark = new ExtentSparkReporter("./Report/" + "Report" + System.currentTimeMillis() + ".html");

        extent = new ExtentReports();
        extent.setReportUsesManualConfiguration(true);
        extent.attachReporter(spark);

        extent.setSystemInfo("Hostname", "LocalHost");
        extent.setSystemInfo("TesterName", utility.Utility.fetchProperty("Tname").toString());
        extent.setSystemInfo("OS", "Window10");
        extent.setSystemInfo("Browser", utility.Utility.fetchProperty("BrowserName").toString());
    }

    @BeforeMethod(alwaysRun = true)
    public void GetTestName(ITestResult result) throws InterruptedException {
        Thread.sleep(500);
        test = extent.createTest(result.getMethod().getMethodName().toUpperCase(), result.getMethod().getDescription());
    }

    @AfterMethod(alwaysRun = true)
    public void Reporter(ITestResult result) {
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        test.assignCategory(result.getTestContext().getSuite().getName().toUpperCase());
        test.getModel().setStartTime(getTime(result.getStartMillis()));
        test.getModel().setEndTime(getTime(result.getEndMillis()));
        test.getModel().setDescription(result.getMethod().getDescription());

        if (result.getStatus() == ITestResult.FAILURE) {
            test.getModel().setEndTime(getTime(result.getEndMillis()));
            test.fail(result.getThrowable());
            test.assignCategory(className.toUpperCase());
            test.fail(MarkupHelper.createLabel(result.getName() + " The Test Case Failed", ExtentColor.RED));
            System.out.println("***************************Failed********************* " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " ********************Failed******************"));
        } else if (result.getStatus() == SUCCESS) {
            test.getModel().setEndTime(getTime(result.getEndMillis()));
            test.assignCategory(className.toUpperCase());
            test.pass(MarkupHelper.createLabel(result.getName() + " The Test Case Passed", ExtentColor.GREEN));
            System.out.println("***************************Passed********************* " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " ********************Passed******************"));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.getModel().setEndTime(getTime(result.getEndMillis()));
            test.assignCategory(className.toUpperCase());
            test.skip(MarkupHelper.createLabel(result.getName() + " The Test Case Skipped", ExtentColor.YELLOW));
            System.out.println("***************************Skipped********************* " + (result.getMethod().getMethodName() + "  " + getTime(result.getEndMillis()) + " ********************Skipped******************"));
        }

        extent.flush();
    }

    @BeforeTest(alwaysRun = true)
    public void setUp() throws IOException, InterruptedException {
        switch (Utility.fetchProperty("BrowserName").toString()) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().setup();
                getdriver.set(new ChromeDriver());
                Builder();
            }
            case "Firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                getdriver.set(new FirefoxDriver());
                Builder();
            }
            case "ChromeHeadless" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("--incognito");
                getdriver.set(new ChromeDriver(options));
                Builder();
            }
            case "FirefoxHeadless" -> {
                FirefoxOptions option = new FirefoxOptions();
                option.addArguments("--headless");
                option.addArguments("--incognito");
                getdriver.set(new FirefoxDriver(option));
                Builder();
            }
            case "RemoteFirefox" -> {
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
            }
            case "RemoteChrome" -> {
                ChromeOptions caps = new ChromeOptions();
                caps.setCapability("browserName", "chrome");
                caps.setCapability("enableVNC", true);
                caps.setCapability("console", true);

                try {
                    getdriver.set(new RemoteWebDriver(new URL(Utility.fetchProperty("RemoteURL").toString()), caps));

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Builder();
            }
        }
    }

    public static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    public void Builder() throws IOException {

        getdriver.get().manage().deleteAllCookies();
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
        getdriver.get().manage().timeouts().implicitlyWait(Integer.parseInt((String) Utility.fetchProperty("implicit.wait")), TimeUnit.SECONDS);
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