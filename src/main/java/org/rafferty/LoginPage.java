package org.rafferty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//Using @ParameterizedTest @CsvFileSource(read JSON)
//Framework
public class LoginPage {
    private WebDriver driver;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        driver.findElement(usernameField).clear();
        //driver.findElement(usernameField).sendKeys(username);
        driver.findElement(usernameField).sendKeys(username == null ? "" : username);

        driver.findElement(passwordField).clear();
        //driver.findElement(passwordField).sendKeys(password);
        driver.findElement(passwordField).sendKeys(password == null ? "" : password);


        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
