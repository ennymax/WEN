import Base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.ExcelUtil;

public class Demotest extends TestBase {

    @Description("login")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 1, dataProvider = "data-set", dataProviderClass = ExcelUtil.class)
    public void login(String shopnamee, String Emaill, String Passwordd){
        loginPage.EnterShopName(shopnamee).EnterShopMail(Emaill).EnterPassword(Passwordd).ClickSignIn();
        Assert.assertTrue(loginPage.CheckLogin());
    }

    @Description("Dashboard")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void DashBoard(){
        Assert.assertTrue(loginPage.CheckLogin());
    }
}
