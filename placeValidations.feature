Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
     Given Add Place Payload with "<name>" "<language>" "<address>"
     When user call "AddPlaceAPI" with "Post" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
     And verify place_Id created maps to "<name>" using "getPlaceAPI"
     
 Examples:
     |name   ||language || address           |
     |AAhouse|| english || World cross center|
     #|BBhouse|| spanish || sea cross center  |
 
 @DeletePlace    
Scenario: Verify if Delete Place functionality is working
     Given DeletPlace payload
     When user call "deletePlaceAPI" with "Post" http request
     Then the API call got success with status code 200
     And "status" in response body is "OK"
     