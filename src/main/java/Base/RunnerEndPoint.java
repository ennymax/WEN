package main.java.Base;

import main.java.Base.TestBaseEndPoint;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static Base.Email.DeleteNewestFile;
import static Base.Email.SendReportEndPoint;

public class RunnerEndPoint extends TestBaseEndPoint {

    public static void StartComEndPoint() throws IOException, InterruptedException {

        System.out.println("###########################  TESTS STARTED   ############################ ");
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestSuites(Collections.singletonList("C:\\Automation\\Crown_Test_Framework\\testng.xml"));
        testng.addListener(tla);
        testng.run();

        if (tla.getPassedTests().size() < 200) {
            TimeUnit.MINUTES.sleep(1);
            SendReportEndPoint("Omotayo_TEXT", "COMAPI_TEXT", tla.getPassedTests().size(), tla.getFailedTests().size());
        }

        System.out.println("############################ " + tla.getPassedTests().size() + " TESTS PASSED ################### ! \n");
        System.out.println("############################ " + tla.getFailedTests().size() + " TESTS FAILED ################### ! \n");
        System.out.println("############################  END ###############################");

        TimeUnit.MINUTES.sleep(10);
        DeleteNewestFile("./Report/", "html");
    }
}
