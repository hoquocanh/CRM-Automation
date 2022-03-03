Feature: Verify changing rack name when POST Rack Information

#Scenario#1:Verify that racks information API responds correctly when sending POST method to change rack name if the entered rack name includes 1 valid character
  Scenario Outline: Verify rack name after entered rack name includes 1 valid character
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that <keyOfValue> is changed to <inputValue> and meet <expectedValue>
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|200|a|a|

#Scenario#2:Verify that racks information API responds correctly when sending POST method to change rack name if the entered rack name includes 50 valid characters
  Scenario Outline: Verify rack name after entered rack name includes 50 valid character
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that <keyOfValue> is changed to <inputValue> and meet <expectedValue>
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|200|01234567890123456789012345678901234567890123456789|01234567890123456789012345678901234567890123456789|
   	
   	#Scenario#3:Verify that racks information API responds correctly when sending POST method if RM LAN existed Rack X Not Communicating alarm
  Scenario Outline: Verify status 503 appears if RM LAN existed Rack X Not Communicating alarm
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
    
  Examples: 
  |configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
  |configurationA|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|503|aa|
  
  #Scenario#4:Verify that racks information API responds correctly when sending POST method to change rack name if the entered rack name includes 1 valid character but rack number includes invalid number
  Scenario Outline: Verify after entered valid rack name but invalid rack number
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue1> at rack <rackPosition1> to <inputValue1> in <jsonDataPath>
  When Update the value of the key <keyOfValue2> at rack <rackPosition2> to <inputValue2> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  Then Verify that the POST status code is <expectedCode>
  
  
 Examples: 
  |configuration|initialData|jsonDataPath|keyOfValue1|rackPosition1|expectedCode|inputValue1|expectedValue1|keyOfValue2|rackPosition2|inputValue2|
  |configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|400|RACK 1|rack 1|rackNumber|1|256|
 
 #Scenario#5:Verify that racks information API responds correctly when sending POST method to change rack name if remove item rack name in Json area of Rack 1
  Scenario Outline: Verify after remove rack name key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|200|rack 1|

#Scenario#6:Verify that racks information API responds correctly when sending POST method to change rack name if the entered rack name includes 51 valid character
  Scenario Outline: Verify rack name after entered rack name includes 51 valid character
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|400|01234567890123456789012345678901234567890123456789A|rack 1|
