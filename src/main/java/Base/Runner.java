package Base;

import Base.TestBase;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import static Base.Email.DeleteNewestFile;
import static Base.Email.SendReport;

public class Runner extends TestBase {

    public static void StartUCG() throws IOException, InterruptedException {
        System.out.println("###################  TESTS STARTED   ##################### ");
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("./src/TestNG/CICOD/UCG/testngUCG.xml"));
        testng.addListener(tla);
        testng.run();

        float Totalcount = (tla.getFailedTests().size() + tla.getPassedTests().size() + tla.getSkippedTests().size());
        float Passpercentage = (tla.getPassedTests().size() / Totalcount * 100);

        System.out.println("PASS PERCENTAGE - " + Passpercentage + "%");
        String k =" PASS PERCENTAGE = " + Passpercentage + "%";

        TimeUnit.MINUTES.sleep(1);
        SendReport("QA_TEXT", "UCG_TEXT", tla.getPassedTests().size(), tla.getFailedTests().size(), tla.getSkippedTests().size(), k);

        System.out.println(tla.getFailedTests().size() + "  TESTS FAILED ! ");
        System.out.println("###################    END    #####################");

        TimeUnit.MINUTES.sleep(10);
        DeleteNewestFile("./Report/", "html");
    }
}
