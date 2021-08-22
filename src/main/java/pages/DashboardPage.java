package pages;

import Base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static utility.JavaScriptUtil.CheckElementPresent;


public class DashboardPage extends TestBase {

    public static final String Chatwithus = "or visit CICOD Support for help articles and resources";

    @FindBy(xpath = "//p[contains(text(),'or visit')]")
    private WebElement chatwithus;

    @FindBy(xpath = "//p[normalize-space()='Customer Order Management']")
    private WebElement combuttom;

    @FindBy(xpath = "//img[@alt='UNIFIED COLLECTIONS GATEWAY']")
    private WebElement ucgbutton;

    @FindBy(xpath = "//p[normalize-space()='Operations']")
    private WebElement wfmbuttom;

    @FindBy(xpath = "//img[@alt='INVENTORY MANAGMENT SYSTEM']")
    private WebElement imsbutton;

    public DashboardPage() {
        PageFactory.initElements(getdriver.get(),this);
    }

    public boolean ComBoundle() {
        return CheckElementPresent(combuttom);
    }

    public boolean UcgBoundle() {
        return CheckElementPresent(ucgbutton);
    }

    public boolean WfmBoundel() {
       return CheckElementPresent(wfmbuttom);
    }

    public boolean ImsBoundle() {
        return CheckElementPresent(imsbutton);
    }

    public boolean chatwithus(String value) {
        return CheckElementPresent(chatwithus) && chatwithus.getText().contains(value);
    }
}
