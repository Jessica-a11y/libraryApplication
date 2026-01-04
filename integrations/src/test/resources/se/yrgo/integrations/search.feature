Feature: Searching for books
As a user I want to be able to search for available books so I know what I can loan.

Scenario: Getting to the search page
Given the user is on the start page.
When the user navigates to the book search.
Then they can see the search form.

Scenario: User searches for a book that does not exist
  Given the user is on the start page.
  When the user searches for "Gild"
  Then "No books found" should be displayed

  Scenario: User searches for a book that exists
  Given the user is on the start page.
  When the user searches for "Här kommer Pippi Långstrump"
  Then "Matching books" should be displayed