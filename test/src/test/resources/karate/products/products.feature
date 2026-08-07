Feature: Sauce Labs Product API

  Background:
    * url baseUrl

  Scenario: Homepage loads successfully
    Given path '/'
    When method GET
    Then status 200

  Scenario: Login page returns 200
    Given path '/index.html'
    When method GET
    Then status 200

  Scenario Outline: Product pages are accessible
    Given path '/<page>'
    When method GET
    Then status 200

    Examples:
      | page          |
      | inventory.html |
      | cart.html      |
      | checkout-step-one.html |