package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import se.yrgo.integrations.GeneralStepDefinitions;

import java.time.Duration;

public class StartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public StartPage() {
        this.driver = GeneralStepDefinitions.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findFindBookButton() {
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn.btn-primary")));
        return button;
    }

    public SearchPage navigateToSearchPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("FIND A BOOK"))).click();
        return new SearchPage(driver);
    }
}
