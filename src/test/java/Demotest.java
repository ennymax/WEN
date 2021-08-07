import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static utility.BrokenLink.BrokenLink;

public class Demotest extends TestBase {

    @Description("login")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void logina() throws Exception {
        BrokenLink();
    }
}
