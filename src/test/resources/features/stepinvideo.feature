Feature: Country Capitals
  Scenario: Validate country capital in Wikipedia vs Rest Countries Api
    Given a list of country names
    When user tries to extract the country capital from wikipedia and rest api
    Then the country capitals should match