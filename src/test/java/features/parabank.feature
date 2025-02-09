Feature: Parabank UI Functionality Test

  Scenario: Verify footer links, navigation, and UI elements on Parabank
    Given I open the Parabank website
    Then I verify there is a "Partners" link in the footer
    When I click on the "Partners" link
    Then I verify a new tab is opened
    And I switch back to the original tab
    Then I verify the right-side services panel is visible
    When I scroll to the bottom and then back to the top
    And I click on the logo
    Then I verify the previous content disappears and home page appears
