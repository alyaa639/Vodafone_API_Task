Feature: Validate that the API successfully returns a list of brands.

  Scenario Outline: Return The List OF Brands
    Given the API endpoint brands is available
    When  I send a GET to endpoint request to brands
    Then  I should receive a "<status>" response
    And   Check that the list of brands has more than one entry.
    And   Check that each brand object contains _id and name properties.
    Examples:
      | status |
      | 200    |


  Scenario Outline: Validate that a brand is successfully returned by a specific ID.
    Given the API endpoint brands is available
    When  I send a GET to endpoint request to return specific brand
    Then  I should receive a "<status>" response
    And   The brand name should match the "<name>"
    Examples:
      | status | name  |
      | 200    | asmaa |





