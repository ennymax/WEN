import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import static pages.DashboardPage.Chatwithus;
import static utility.Utility.fetchvalue;

public class DashBoardTest extends TestBase {

    @Description("login")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1)
    public void login(){
        loginPage.EnterShopName(fetchvalue("shopname")).EnterShopMail(fetchvalue("email"))
                .EnterPassword(fetchvalue("password")).ClickSignIn();
        Assert.assertTrue(loginPage.CheckLogin());
    }

    @Description("Dashboard")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void DashBoard(){
        Assert.assertTrue(dashboardPage.ComBoundle());
        Assert.assertTrue(dashboardPage.UcgBoundle());
        Assert.assertTrue(dashboardPage.WfmBoundel());
        Assert.assertTrue(dashboardPage.ImsBoundle());
    }

    @Description("Dashboard Chat With US")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 3)
    public void DashBoardChatWithUs(){
        Assert.assertTrue(dashboardPage.chatwithus(Chatwithus));
    }
}
