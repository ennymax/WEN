package pages;

import Base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.ActionClassUtil.DoClickActionClass;

public class LoginPage extends TestBase {

    @FindBy(xpath = "//input[@placeholder='Your domain']")
    private WebElement ShopName;

    @FindBy(xpath = "//input[@name='merchantEmail']")
    private WebElement Email;

    @FindBy(xpath = "//input[@name='merchantPassword']")
    private WebElement Password;

    @FindBy(xpath = "//div[@id='tab1default']//button[@type='submit'][normalize-space()='Login']")
    private WebElement LoginButton;

    @FindBy(xpath = "//p[normalize-space()='Sales']")
    private WebElement CheckLogin;

    public LoginPage() {
        PageFactory.initElements(getdriver.get(),this);
    }

    public LoginPage EnterShopMail(String MerchantEmail) {
        Email.clear();
        Email.sendKeys(MerchantEmail);
        return this;
    }

    public LoginPage EnterPassword(String MerchantPassword) {
        Password.clear();
        Password.sendKeys(MerchantPassword);
        return this;
    }

    public LoginPage EnterShopName(String MerchantID) {
        ShopName.clear();
        ShopName.sendKeys(MerchantID);
        return this;
    }

    public void ClickSignIn() {
        DoClickActionClass(LoginButton);
    }

    public boolean CheckLogin() {
        return CheckLogin.isDisplayed();
    }
}
