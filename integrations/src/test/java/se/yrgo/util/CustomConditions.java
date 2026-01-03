package se.yrgo.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> elementHasBeenClicked(WebElement element) {
        return driver -> {
            try {
                element.click();
                return true;
            } catch (Exception e) {
                return false;
            }
        };
    }
}
