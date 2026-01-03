package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.integrations.GeneralStepDefinitions;
import se.yrgo.util.CustomConditions;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public StartPage() {
        this.driver = GeneralStepDefinitions.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement findFindBookButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));
        return button;
    }

    public SearchPage navigateToSearchPage() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-primary")));
        button.click();
        return new SearchPage(driver);
    }
}
