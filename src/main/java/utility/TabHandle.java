package utility;


import Base.TestBase;

public class TabHandle extends TestBase {

    public static void TabHandle() throws InterruptedException {
        Thread.sleep(2000);
        String mjj = getdriver.get().getWindowHandle();
        System.out.println("The WindownHandle is " + mjj);

        Thread.sleep(4000);

        for (String childTab : getdriver.get().getWindowHandles()) {
            getdriver.get().switchTo().window(childTab);
        }

        Thread.sleep(2000);
        System.out.println(getdriver.get().getTitle());
        Thread.sleep(5000);
    }
}