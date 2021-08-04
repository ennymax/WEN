package utility;


import Base.TestBase;

public class WindowHandler extends TestBase {

    public static String switchToWindowTest(String title) {

        return getdriver.get().getWindowHandles()
                .stream()
                .map(handler -> getdriver.get().switchTo().window(handler).getTitle())
                .filter(ele -> ele.contains(title))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("No Such window");
                });
    }
}
