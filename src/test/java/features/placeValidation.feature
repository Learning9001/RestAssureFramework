@AllTests
Feature: Validating API

 @First @All
Scenario Outline: Verify if place is added successfully using AddPlace API
    Given Add Place Payload <name> <Website>
    When user calls "addPlaceAPI" API with "POST" http request
    Then API Call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify that <name> mapped with "getPlaceAPI"
    

    Examples: 
      | name              | Website          |
      | "Frontline House" | "www.google.com" |
 ##     | "second house"    | "www.msn.com"    |
 
 @Second  @All
 Scenario: Verify delete place API functionality working
    Given Delete Place Payload 
    When user calls "deletePlaceAPI" API with "POST" http request
    Then API Call got success with status code 200
    And "status" in response body is "OK"
    
    
    