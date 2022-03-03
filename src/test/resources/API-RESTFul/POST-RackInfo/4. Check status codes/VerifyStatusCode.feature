Feature: Verify status code when POST Rack Information

#Scenario#1:Verify that racks information API responds correctly when sending POST method if RM LAN existed Rack X Not Communicating alarm
  Scenario Outline: Verify status code 503 if RM LAN existed Rack X Not Communicating alarm
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  And Send a POST request to update server information with data updated
  Then Verify that the POST status code is <expectedCode>
    
    Examples: 
   	|configuration|initialData|jsonDataPath|expectedCode|
   	|configurationA|data/rack_information/initialData_configB.json|data/rack_information/data.json|503|

#Scenario#2:Verify that racks information API responds correctly when sending POST method if the request is handled by server successfully
  Scenario Outline: Verify status code 200 if the request is handled by server successfully
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  And Send a POST request to update server information with data updated
  Then Verify that the POST status code is <expectedCode>
    
    Examples: 
   	|configuration|initialData|jsonDataPath|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|200|

#Scenario#3:Verify that racks information API responds correctly when sending POST method if the request has bad data which doesn't support
  Scenario Outline: Verify status code 400 if the request has bad data which doesn't support
 Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  Then Verify that the POST status code is <expectedCode>
  
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|400|255|

#Scenario#4:Verify that racks information API responds correctly when sending POST method if Missing or incorrect authentication credentials
  Scenario Outline: Verify status code 401 if missing or incorrect authentication credentials
 Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  And Send a POST request to update server information with data updated using No Auth
  Then Verify that the POST status code is <expectedCode>
  
    Examples: 
   	|configuration|initialData|jsonDataPath|expectedCode|authenUsername|authenPassword|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|401|incorrect_username|incorrect_password|

#Scenario#5:Verify that racks information API responds correctly when sending POST method if The URI requested is invalid or the resource requested does not exist.
  Scenario Outline: Verify status code 404 if the URI requested is invalid or the resource requested does not exist.
 Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  And Send a POST request to an incorrect URL
  Then Verify that the POST status code is <expectedCode>
  
    Examples: 
   	|configuration|initialData|jsonDataPath|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|404|

#Scenario#6:Verify that racks information API responds correctly when sending POST method if The method is not supported for the specified resource.
  Scenario Outline: Verify status code 405 if The method is not supported for the specified resource.
 Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  And Send a PUT request to update server information with data updated
  Then Verify that the PUT status code is <expectedCode>
  
    Examples: 
   	|configuration|initialData|jsonDataPath|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|405|



