package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findForm() {
        WebElement formElement = driver.findElement(By.tagName("form"));
        return formElement;
    }

    public void searchForBookWithTitle(String title) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[placeholder='Title']")));
        input.clear();
        input.sendKeys(title);

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='submit']"))).click();
    }

    public WebElement bookNotFound() {
        WebElement notfound = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='No books found']")));
        return notfound;
    }

    public WebElement bookFound() {
        WebElement found = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("caption")));
        return found;
    }
}
