import cc.robotdreams.automation.base.BaseGUITest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class WebTablesTest extends BaseGUITest {

    @Test
    public void testWebTables() throws URISyntaxException {
            this.wd().get(
                    new URI(this.wd().getCurrentUrl())
                            .resolve("/webtables").toString()
            );

        // Знайти і натиснути кнопку "Add"
        WebElement addButton = wd().findElement(By.xpath("//*[@id='addNewRecordButton']"));
        addButton.click();

        // Заповнити форму додавання запису
        WebElement firstNameInput = this.wd().findElement(By.xpath("//*[@id='firstName']"));
        WebElement lastNameInput = this.wd().findElement(By.xpath("//*[@id='lastName']"));
        WebElement emailInput = this.wd().findElement(By.xpath("//*[@id='userEmail']"));
        WebElement ageInput = this.wd().findElement(By.xpath("//*[@id='age']"));
        WebElement salaryInput = this.wd().findElement(By.xpath("//*[@id='salary']"));
        WebElement departmentInput = this.wd().findElement(By.xpath("//*[@id='department']"));
        WebElement submitButton = this.wd().findElement(By.xpath("//*[@id='submit']"));

        firstNameInput.sendKeys("Ім'я");
        lastNameInput.sendKeys("Прізвище");
        emailInput.sendKeys("email@example.com");
        ageInput.sendKeys("30");
        salaryInput.sendKeys("50000");
        departmentInput.sendKeys("IT");
        submitButton.click();

        try {
            // Очікування видимості нового запису в таблиці
            WebElement newRecordRow = getWait().until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@class='rt-tr -even']//div[contains(text(), 'email@example.com')]")));
        } catch (org.openqa.selenium.TimeoutException e) {
            // Виведення помилки, якщо запис не знайдено
            System.out.println("Помилка: Запис не знайдено");
        }

        // Редагування запису через функцію редагування (Edit)
        WebElement editButton = this.wd().findElement(By.xpath("//span[@id='edit-record-4']"));
        editButton.click();

        // Зміна значення поля "Зарплата" (Salary)
        WebElement editSalaryInput = this.wd().findElement(By.xpath("//*[@id='salary']"));
        editSalaryInput.clear();
        editSalaryInput.sendKeys("60000");

        // Запис оновленого значення у таблицю
        WebElement updateButton = this.wd().findElement(By.xpath("//*[@id='submit']"));
        updateButton.click();

        // Підтвердження редагування
        try {
            // Очікування тексту в елементі
            getWait().until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[@class='rt-tr-group']//div[contains(text(), '60000')]"), "60000"));
        } catch (org.openqa.selenium.TimeoutException e) {
            // Виведення помилки, якщо текст не знайдено
            System.out.println("Текст не знайдено");
        }
    }
}