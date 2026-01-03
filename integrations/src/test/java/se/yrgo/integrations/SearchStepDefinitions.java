package se.yrgo.integrations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import se.yrgo.pages.SearchPage;
import se.yrgo.pages.StartPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearchStepDefinitions {
    private StartPage startPage;
    private SearchPage searchPage;

    public SearchStepDefinitions() {
        this.startPage = new StartPage();
    }

    @When("the user navigates to the book search.")
    public void the_user_navigates_to_the_book_search() {
        searchPage = startPage.navigateToSearchPage();
        searchPage.findForm();
    }

    @Then("they can see the search form.")
    public void they_can_see_the_search_form() {
        assertNotNull(startPage.findFindBookButton());
        assertEquals("form", searchPage.findForm().getTagName());
    }
}
