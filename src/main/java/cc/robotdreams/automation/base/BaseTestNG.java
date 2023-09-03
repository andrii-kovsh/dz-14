package cc.robotdreams.automation.base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.lang.reflect.Method;

public class BaseTestNG {
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        System.out.println("Починаємо тест: " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method, ITestResult result) {
        String status = result.isSuccess() ? "Тест пройдено" : "Тест провалено";
        System.out.println("Закінчуємо тест: " + method.getName() + " - " + status);
    }

}