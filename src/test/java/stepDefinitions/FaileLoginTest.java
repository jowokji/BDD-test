package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class FaileLoginTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Инициализация WebDriver и открытие страницы логина
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://example.com/login"); // Укажите актуальный URL
    }

    @Test
    public void testValidationErrorForEmptyFields() {
        // Очистка полей имени пользователя и пароля (они пусты по умолчанию)
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();

        // Клик по кнопке "Войти"
        driver.findElement(By.id("loginButton")).click();

        // Проверка отображения сообщения об ошибке
        boolean isValidationErrorDisplayed = driver.findElement(By.id("errorMessage")).isDisplayed();
        assertTrue(isValidationErrorDisplayed, "Validation error is not displayed for empty fields.");
    }

    @AfterClass
    public void tearDown() {
        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}
