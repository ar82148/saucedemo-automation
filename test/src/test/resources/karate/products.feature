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

  # Note: SauceDemo is a client-rendered SPA with no public REST API —
   # deeper endpoints aren't reachable via raw HTTP, so this suite is
   # scoped to a basic availability smoke check. Full flow coverage
   # lives in the Selenium suite.

  #Scenario Outline: Product pages are accessible
   #Given path '/<page>'
   #When method GET
   #Then status 200

   #Examples:
   #  | page          |
   #  | inventory.html |
   #  | cart.html      |
   #  | checkout-step-one.html |