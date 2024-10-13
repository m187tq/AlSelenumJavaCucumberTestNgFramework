@Ignore
Feature: Wiremock Example

  Scenario Template: Test

    Given I use endpoint "/api/message"
    When  I use "<method>" command
    And   request "<request>"
    Then  The response should be "<responsecode>" with response message "<responsefile>"

    Examples:
      | request   | method | responsecode | responsefile      |
      |           | get    | 200          | testresponse.json |
      | test.json | post   | 200          | postresponse.json |