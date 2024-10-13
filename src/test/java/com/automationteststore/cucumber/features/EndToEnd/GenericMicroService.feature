@Ignore
Feature:Generic MicroServices Feature

  Scenario Template: Generic Test For WebServices

    Given I pass the "http://p9264.mocklab.io/json/1" and "<file>"
    And use the "<method>"
    When I process the call
    Then The response should be "<response>" with "<responsestatus>"

    Examples:
      | file      | method | response          | responsestatus |
      | test.json | GET    | testresponse.json | 200            |
      | test.json | POST   | postresponse.json | 200            |


