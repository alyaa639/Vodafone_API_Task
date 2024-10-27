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
    When  I send a GET to endpoint request to return specific brand with ID "<id>"
    Then  I should receive a "<status>" response
    And   The brand name should match the "<name>"
    Examples:
      | status | name  | id                       |
      | 200    | zeus | 667fbba6986188d4dce49796 |


  Scenario Outline: Validate that the API prevents the creation of duplicate brand entries via POSTbrands request.
    Given the API endpoint brands is available
    When  I send a post request with name "<name>" and description "<description>"
    Then  I should receive a "<status>" response

    Examples:
      | status | name | description               |  |
      | 422    | zeus | zeus company ban loa fake |  |

  Scenario Outline: Validate that the API returns an appropriate error when attempting to retrieve a brand that does not exist
    Given the API endpoint brands is available
    When  I send a GET to endpoint request to return specific brand with ID "<id>"
    Then  I should receive a "<status>" response
    And   error message "<errorMessage>" should be returned
    Examples:
      | status | errorMessage                          | id               |
      | 422    | {\"error\":\"Unable to fetch brand\"} | 6437664656457645 |


  Scenario Outline: Validate that the API throws an error when attempting to delete a non-existent brand.
    Given the API endpoint brands is available
    When  I send a Delete to endpoint request to return specific brand with ID "<id>"
    Then  I should receive a "<status>" response
    And   error message "<errorMessage>" should be returned
    Examples:
      | status | errorMessage                          | id               |
      | 422    | {\"error\":\"Unable to delete brand\"} | 6437664656457645 |

