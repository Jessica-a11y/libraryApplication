package se.yrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findForm() {
        WebElement formElement = driver.findElement(By.tagName("form"));
        return formElement;
    }
}
