package org.rafferty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    // Locators
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isItemInCart(String itemName) {

        By item =
                By.xpath("//div[@class='inventory_item_name' and text()='" + itemName + "']");

        return driver.findElement(item).isDisplayed();
    }

    public void checkout() {
        driver.findElement(checkoutButton).click();
    }
}
