@systemTest
Feature:HTTP API Request

  @Ignore
  Scenario: As a user on the first run I would like to change the password
    Given The "master.admin@nhs.net" password needs changing using the initial password "m4sterpassw0rd?" using endpoint "api/employer/account/password"
    When I change the password to "Thepass7"
    Then The user is ready

  Scenario Outline: As a user I would like to test an API
    Given I use the data file "storydatafile.json" and the endpoint "api/employer/auth"
    When I use test data "<test_data_name>"
    And I use request method "<method>"
    And With authentication "<require_auth>"
    Then The response should be "<response_code>" and "<response_message>"

    Examples:
      | test_data_name      | method | response_code | response_message | require_auth |
      | employeeauthrequest | POST   | 200           |                  |              |

  Scenario Outline: As a user I would like to call the user register api and change some values for
  negative/positive testing.
    Given I use the data file "storydatafile.json" with the end point "api/employer/register"
    And I use the test data set "userRegistrationTestData"
    And I use the username "<username>"
    And I use the password "<password>"
    And I use the roles "<Roles>"
    And I use the company name"<companyname>"
    And I use the surname "<surname>"
    And I use the first name"<firstname>"
    When I make the API Call
    Then The response call result should be "<responsecode>"

    Examples:
      | username      | password | Roles      | companyname           | surname | firstname | responsecode |
      | john@test.com | Thepass5 | ROLE_ADMIN | Johns Health Practice | Douglas | John      | 201          |
      #|  john.douglas@test.com  | Thepass5   | NONE          |Johns Health Practice  | Douglas   | John    |     400     |
      #|                         | Thepass5   | ROLE_ADMIN    |Johns Health practice  | Douglas   | John    |     400     |
      #|  john@test.com          |            | ROLE_ADMIN    |Johns Health Practice  | Douglas   | John    |     400     |
      #|  john@test.com          | Thepass5   | ROLE_ADMIN    |                       | Douglas   | John    |     400     |
      #|  john@test.com          | Thepass5   | ROLE_ADMIN    |Johns Health Practice  |           | John    |     400     |
      #|  john@test.com          | Thepass5   | ROLE_ADMIN    |Johns Health Practice  | Douglas   |         |     400     |

  @Ignore
  Scenario Outline: As a user I would like to call employer authentication API with negative tests
    Given I user the data file "storydatafile.json" for testing with the end point "/api/employer/auth"
    And I use the test dataset "employeeauthrequest"
    And I use username parameter "<username>"
    And I use password parameter "<password>"
    When I make the API end point call
    Then The Response call should be of value "<response_code>"
    Examples:
      | username      | password  | response_code |
      | john@test.com |           | 400           |
      |               | password1 | 400           |
      | john@test.com | password1 | 401           |