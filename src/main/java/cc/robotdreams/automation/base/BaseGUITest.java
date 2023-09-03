package cc.robotdreams.automation.base;

import cc.robotdreams.automation.Config;
import cc.robotdreams.automation.Session;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseGUITest extends BaseTestNG {
    private static WebDriver driver; // Статичний, щоб зберегти одну сесію WebDriver для всього набору тестів
    private WebDriverWait wait; // Змінна для часу очікування

    protected WebDriver wd() {
        return Session.get().webdriver();
    }

    protected WebDriverWait getWait() {
        return wait;
    }
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Ініціалізація сесії та відкриття посилання один раз перед всім набором тестів
        if (driver == null) {
            Session session = Session.get();
            driver = session.webdriver();
            driver.get(String.format("http://%s", Config.HTTP_BASE_URL.value));
        }

        // Змінна для часу очікування
        Duration waitTime = Duration.ofSeconds(5);
        wait = new WebDriverWait(driver, waitTime);
    }

    @AfterMethod(alwaysRun = true)
    public void endSession() {
        // Закриття сесії один раз після всього набору тестів
        if (driver != null) {
            Session.get().close();
            driver = null;
        }
    }
}