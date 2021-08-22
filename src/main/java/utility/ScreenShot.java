package utility;

import Base.TestBase;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScreenShot extends TestBase {

    public static void ScreenShotWebElementWebEvent(WebElement locator) throws IOException{
        Path path = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        File Dest = new File("src/../Screenshot/" + "Screenshot" + System.currentTimeMillis() + ".png");
        String extentReportImageqm11 = Dest.getAbsolutePath();
        File screenshot = ((TakesScreenshot) getdriver.get()).getScreenshotAs(OutputType.FILE);
        BufferedImage fullScreen = ImageIO.read(screenshot);
        Point location = locator.getLocation();
        int width = locator.getSize().getWidth();
        int height = locator.getSize().getHeight();

        BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(), width, height);
        ImageIO.write(logoImage, "png", screenshot);
        FileUtils.copyFile(screenshot, new File(extentReportImageqm11));
        test.get().info("Screenshot from : " + extentReportImageqm11, MediaEntityBuilder.createScreenCaptureFromPath(extentReportImageqm11).build());
    }

    public static void ScreenShotWebElement(String locator) throws IOException{
        Path path = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        File Dest = new File("src/../Screenshot/" + "Screenshot" + System.currentTimeMillis() + ".png");
        String extentReportImageqm11 = Dest.getAbsolutePath();
        WebElement logo = getdriver.get().findElement(By.xpath((locator)));
        File screenshot = ((TakesScreenshot) getdriver.get()).getScreenshotAs(OutputType.FILE);
        BufferedImage fullScreen = ImageIO.read(screenshot);
        Point location = logo.getLocation();
        int width = logo.getSize().getWidth();
        int height = logo.getSize().getHeight();

        BufferedImage logoImage = fullScreen.getSubimage(location.getX(), location.getY(), width, height);
        ImageIO.write(logoImage, "png", screenshot);
        FileUtils.copyFile(screenshot, new File(extentReportImageqm11));
        test.get().info("Screenshot from : " + extentReportImageqm11, MediaEntityBuilder.createScreenCaptureFromPath(extentReportImageqm11).build());
    }

    public static void screenShot() throws IOException, InterruptedException {
        Path path = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        File Dest = new File("src/../Screenshot/" + "Screenshot" + System.currentTimeMillis() + ".png");
        String errflpath = Dest.getAbsolutePath();
        try {
            File srcam11 = ((TakesScreenshot) getdriver.get()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcam11, new File(errflpath));
            test.get().info("Screenshot from : " + errflpath, MediaEntityBuilder.createScreenCaptureFromPath(errflpath).build());
        } catch (IOException e) {
            System.out.println("Error in the captureAndDisplayScreenShot method: " + e.getMessage());
        }

    }

    public static void takeScreenShot(String methodName) {
        Path path1 = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path1)) {
            try {
                Files.createDirectories(path1);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        File Dest = new File("src/../Screenshot/" + "Screenshot" + System.currentTimeMillis() + ".png");
        String path = Dest.getAbsolutePath();
        File scrFile = ((TakesScreenshot) getdriver.get()).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with test method name
        try {
            FileUtils.copyFile(scrFile, new File(path + methodName + ".png"));
            System.out.println("***Placed screen shot in " + path + " ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ScreenShotFullPage() throws IOException {
        Path path = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }
        File Dest = new File("src/../Screenshot/" + "Screenshot" + System.currentTimeMillis() + ".png");
        String extentReportImageqm11 = Dest.getAbsolutePath();
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(getdriver.get());
        ImageIO.write(screenshot.getImage(), "PNG", new File(extentReportImageqm11));
        test.get().info ("Screenshot from : " + extentReportImageqm11, MediaEntityBuilder.createScreenCaptureFromPath(extentReportImageqm11).build());
    }

    public static String getScreenshot() {
        Path path1 = Paths.get("./Screenshot/");
        // if directory exists?
        if (!Files.exists(path1)) {
            try {
                Files.createDirectories(path1);
            } catch (IOException e) {
                // fail to create directory
                e.printStackTrace();
            }
        }

        File src = ((TakesScreenshot)getdriver.get()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/Screenshot/" +"Screenshot"+ System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}


