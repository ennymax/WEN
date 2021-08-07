import Base.TestBase;
import Listeners.ExtentReportListener;
import Listeners.RetryListener;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static utility.BrokenLink.BrokenLink;
import static utility.ExcelUtil.DoClick;

public class Demotest extends TestBase {

    @Description("login")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void logina() throws Exception {
        //BrokenLink();
    }

    @Description("login")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority =3)
    public void login() throws Exception {
        DoClick("lbtn_XPATH", 5);
    }
}
