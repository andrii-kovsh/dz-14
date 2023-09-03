import cc.robotdreams.automation.base.BaseGUITest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class ButtonClickTest extends BaseGUITest {
    @Test
    public void testButtonClick() throws URISyntaxException {
        this.wd().get(
                new URI(this.wd().getCurrentUrl())
                        .resolve("/elements").toString()
        );

        // Навігація до вкладки "Buttons"
        WebElement buttonsTab = getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[text()='Buttons']")));
        buttonsTab.click();

        // Клік на кнопку "Click Me"
        WebElement clickMeButton = getWait().until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[text()='Click Me']")));
        clickMeButton.click();

        try {
            // Очікування появи тексту на сторінці
            WebElement dynamicText = getWait().until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[text()='Click Me']//following::p")));
            String message = dynamicText.getText();
            System.out.println("Текст повідомлення: " + message);
        } catch (Exception e) {
            // Отримати текст повідомлення та вивести його в консоль
            System.out.println("Помилка: елемент не був знайдений або не став видимим");
            e.printStackTrace();
        }
    }
}