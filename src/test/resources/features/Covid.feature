Feature: Covid API testing
@covid
  Scenario: Get COVID information for all states
    When user sends GET request to Covid api
    And status code is 200
    Then user get information about all states


  Scenario: Get COVID information for all countries
    When user sends GET request to Covid api to get countries info
    And status code is 200
    Then user validates information about all countries

