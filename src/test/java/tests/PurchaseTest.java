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
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("signon.rememberSignons", false);
        profile.setPreference("signon.autofillForms", false);
        profile.setPreference("browser.safebrowsing.passwords.enabled", false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
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
        assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"));

        //Billing Information and Order
        cartPage.checkout();
        checkoutPage.enterCustomerInformation(
                "John",
                "Locke",
                "4815162342");
        checkoutPage.continueCheckout();
        checkoutPage.finishOrder();

        assertEquals(
                "Thank you for your order!",
                checkoutPage.getConfirmationMessage());
    }
}
