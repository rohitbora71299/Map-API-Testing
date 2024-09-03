
Feature: Validating Place API's


@AddPlace @Regression
Scenario Outline: Verify if Place is being Successfully added using AddPlaceApi
  Given Add Place Payload with "<name>","<language>","<location>"
  When User calls "AddPlaceAPI" with "POST" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"
  And verify placeId created maps to "<name>" using "getPlaceAPI"

Examples:
  |name|language|location|
  |RKPuram|English|Delhi|
  #|Dwarka|English |Delhi|

@DeletePlace @Regression
Scenario: Verify if Delete place functionality is working
  Given Delete Place Payload
  When  User calls "deletePlaceAPI" with "POST" http request
  Then the API call got success with status code 200
  And "status" in response body is "OK"