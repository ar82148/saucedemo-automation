package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.rafferty.LoginPage;
import org.rafferty.InventoryPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginDataDrivenTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        String firefoxBin = System.getenv("FIREFOX_BIN");
        if (firefoxBin != null && !firefoxBin.isEmpty()) {
            options.setBinary(firefoxBin);
        }
        options.addArguments("--headless");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest(name = "{3}")
    @CsvFileSource(resources = "/login-data.csv", numLinesToSkip = 1)
    public void loginAttempt(String username, String password, String expectedResult, String description) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, password);

        switch (expectedResult) {
            case "success" -> {
                InventoryPage inventoryPage = new InventoryPage(driver);
                Assertions.assertTrue(inventoryPage.isLoaded(), "Expected successful login to land on inventory page");
            }
            case "locked_out" -> {
                Assertions.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                        "Expected locked-out error message");
            }
            case "error" -> {
                Assertions.assertFalse(loginPage.getErrorMessage().isEmpty(),
                        "Expected an error message for invalid input");
            }
            default -> fail("Unknown expectedResult in test data: " + expectedResult);
        }
    }
}