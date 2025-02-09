package stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.*;
import static org.testng.AssertJUnit.assertTrue;
import static utils.WebDriverManeger.*;

public class NavigationSteps {
    WebDriver driver;

    @Given("the user is on the Login page")
    public void theUserIsOnTheLoginPage() {
        System.setProperty(TYPE_OF_DRIVER, PATH_TO_DRIVER);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(INDEX_PAGE_URL);
    }

    @When("the user clicks on the {string} link")
    public void theUserClicksOnTheLink(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    @Then("the user should be redirected to the Registration page")
    public void theUserShouldBeRedirectedToTheRegistrationPage() {
        assertTrue(driver.getCurrentUrl().contains("register.htm"));
    }

    @Then("the registration form should be displayed")
    public void theRegistrationFormShouldBeDisplayed() {
        assertTrue(driver.findElement(By.id("customerForm")).isDisplayed());
        driver.quit();
    }
}