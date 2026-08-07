package org.rafferty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {

    private WebDriver driver;

    // Locators
    private final By pageTitle = By.cssSelector(".title");
    private final By cartIcon = By.className("shopping_cart_link");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isLoaded() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public void addItemToCart(String itemName) {

        By addButton = By.xpath(
                "//div[text()='" + itemName + "']" +
                        "/ancestor::div[@class='inventory_item']" +
                        "//button");

        driver.findElement(addButton).click();
    }

    public void openCart() {
        driver.findElement(cartIcon).click();
    }
}