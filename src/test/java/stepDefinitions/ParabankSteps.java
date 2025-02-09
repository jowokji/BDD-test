package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import static org.junit.Assert.*;

public class ParabankSteps {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Given("I open the Parabank website")
    public void openParabankWebsite() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Then("I verify there is a {string} link in the footer")
    public void verifyFooterLink(String linkText) {
        WebElement footer = driver.findElement(By.id("footer"));
        WebElement partnerLink = footer.findElement(By.linkText(linkText));
        assertNotNull("Partners link not found in the footer", partnerLink);
    }

    @When("I click on the {string} link")
    public void clickFooterLink(String linkText) {
        WebElement footer = driver.findElement(By.id("footer"));
        WebElement partnerLink = footer.findElement(By.linkText(linkText));
        partnerLink.click();
    }

    @Then("I verify a new tab is opened")
    public void verifyNewTabOpened() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        assertEquals("New tab did not open", 2, tabs.size());
        driver.switchTo().window(tabs.get(1));
    }

    @And("I switch back to the original tab")
    public void switchBackToOriginalTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.close();  // Close new tab
        driver.switchTo().window(tabs.get(0));
    }

    @Then("I verify the right-side services panel is visible")
    public void verifyServicesPanelVisible() {
        WebElement servicesPanel = driver.findElement(By.id("rightPanel"));
        assertTrue("Right-side services panel is not visible", servicesPanel.isDisplayed());
    }

    @When("I scroll to the bottom and then back to the top")
    public void scrollPageUpDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        js.executeScript("window.scrollTo(0, 0)");
    }

    @And("I click on the logo")
    public void clickLogo() {
        WebElement logo = driver.findElement(By.cssSelector("img[title='ParaBank']"));
        logo.click();
    }

    @Then("I verify the previous content disappears and home page appears")
    public void verifyHomePageAppears() {
        WebElement homeContent = driver.findElement(By.id("topPanel"));
        assertTrue("Home page did not load", homeContent.isDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
