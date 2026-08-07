package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.rafferty.LoginPage;
import org.rafferty.InventoryPage;
import org.rafferty.CartPage;
import org.rafferty.CheckoutPage;

public class PurchaseTest {
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

    @Test
    public void userCanPurchaseBackpack() {

        // Int page objects
        LoginPage loginPage = new LoginPage(driver);
        InventoryPage inventoryPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        //Complete purchase flow
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart("Sauce Labs Backpack");
        inventoryPage.openCart();

        //Check if completed successfully
        Assertions.assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));

        //Billing Information and Order
        cartPage.checkout();
        checkoutPage.enterCustomerInformation(
                "John",
                "Locke",
                "4815162342");
        checkoutPage.continueCheckout();
        checkoutPage.finishOrder();

        Assertions.assertEquals(
                "Thank you for your order!",
                checkoutPage.getConfirmationMessage());
    }
}
