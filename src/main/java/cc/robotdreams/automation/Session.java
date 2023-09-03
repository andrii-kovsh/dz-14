package cc.robotdreams.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
public class Session
{
    static final private ThreadLocal<Session> _instance = new ThreadLocal<>();

    private Session() {
        Runtime.getRuntime().addShutdownHook(new Thread(Session.this::close));
    }

    static public Session get() {
        if (_instance.get() == null)
            _instance.set(new Session());
        return _instance.get();
    }

    private WebDriver _webdriver;

    public WebDriver webdriver() {
        if (this._webdriver == null) {
            if ("firefox".equalsIgnoreCase(Config.WEB_BROWSER.value)) {
                FirefoxOptions options = new FirefoxOptions();
                if (Config.WEB_BROWSER_NO_GUI.isTrue()) {
                    options.setHeadless(true);
                }
                this._webdriver = new FirefoxDriver(options);
            }

            this._webdriver.manage().window().maximize();
        }

        return this._webdriver;
    }

    public void close() {
        if (this._webdriver != null) {
            this._webdriver.quit();
            this._webdriver = null;
        }
    }
}