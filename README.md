SauceDemo Automation

A QA automation portfolio project targeting the SauceDemo site, built with Java, Selenium, and Karate. Structure as a Maven multi-module project with a Page Object Model design and a CI pipeline running on GitHub Actions

Tech Stack
Java 17
Selenium WebDriver 4.35
JUnit 5 — test runner, data-driven tests via @ParameterizedTest / @CsvFileSource
Karate 1.4.1 — HTTP-level smoke checks
Maven — multi-module build (main / test)
GitHub Actions — CI on every push



What's Covered

Selenium (UI automation)

Data-driven login tests across multiple valid/invalid credential sets (LoginDataDrivenTest)
Full purchase flow: login → add to cart → checkout → order confirmation (PurchaseTest)
Page Object Model keeps locators and page interactions separate from test logic

Karate (HTTP-level checks)

Basic availability smoke check against the SauceDemo root page

Note on scope: SauceDemo is a client-rendered single-page app with no public REST API — pages like /inventory.html and /cart.html aren't real server routes and are gated by client-side (JS) auth state, not something a raw HTTP client can meaningfully exercise. Because of that, Karate coverage here is intentionally limited to a basic reachability check rather than full flow testing. Deeper API-assertion patterns (status codes, JSON schema validation, chained requests) are demonstrated instead in a separate project, api-automation, against a real REST API (reqres.in).



Running Locally:
mvn clean test




CI:

Every push triggers GitHub Actions Workflow (test.yml)
1. Sets up JDK 17 and Firefox
2. Runs the full Maven reactor build
3. Runs both the Selenium and Karate suites
