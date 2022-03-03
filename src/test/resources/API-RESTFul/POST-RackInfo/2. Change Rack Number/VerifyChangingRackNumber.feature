Feature: Verify rack number when POST Rack Information

#Scenario#1:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number as number "5"
  Scenario Outline: Verify rack number after entered rack number as 5
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
 And Send a GET and collect new Rack Number <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that <keyOfValue> is changed to <inputValue> and meet <expectedValue>
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|200|5|5|
   	
   	#Scenario#2:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number as number "255"
  Scenario Outline: Verify rack number after entered rack number as 255
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
 And Send a GET and collect new Rack Number <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|400|255|1|
   	
   	#Scenario#3:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number is a negative number
  Scenario Outline: Verify rack number after entered rack number as -1
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
 And Send a GET and collect new Rack Number <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|400|-1|1|
   	
   	#Scenario#4:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number that is lager than 255
  Scenario Outline: Verify rack number after entered rack number as 256
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
 And Send a GET and collect new Rack Number <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|400|256|1|
   	
   	#Scenario#5:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number that is lager than 255
  Scenario Outline: Verify rack number after entered rack number as as a character
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
 And Send a GET and collect new Rack Number <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|500|a|1|
   	
   	#Scenario#6:Verify that racks information API responds correctly when sending POST method to change rack number if the entered rack number is blank
  Scenario Outline: Verify rack number is blank
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|500| |
   	
   	#Scenario#7:Verify that racks information API responds correctly when sending POST method to change rack number if modify rack number and rack name on all racks in Zone
  Scenario Outline: Verify rack number and rack name after changing the value of these items on all racks
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue1> at rack <rackPosition1> to <inputValue1> in <jsonDataPath>
  When Update the value of the key <keyOfValue2> at rack <rackPosition2> to <inputValue2> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  Then Verify that the POST status code is <expectedCode>
  And Send a GET and collect new Rack Number <keyOfValue1> info.
  Then Verify that <keyOfValue1> is changed to <inputValue1> and meet <expectedValue1> 
  And Send a GET and collect new Rack Number <keyOfValue2> info.
  Then Verify that <keyOfValue2> is changed to <inputValue2> and meet <expectedValue2>
  Examples: 
  |configuration|initialData|jsonDataPath|keyOfValue1|rackPosition1|expectedCode|inputValue1|expectedValue1|keyOfValue2|rackPosition2|inputValue2|expectedValue2|
  |configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|200|RACK 1|RACK 1|rackNumber|1|5|5|
  
  #Scenario#8:Verify that racks information API responds correctly when sending POST method to change rack number if modify rack number on a rack to duplicate another rack in LAN
  Scenario Outline: Verify after add duplicate rack number
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|2|400|1|2|
   	
   	#Scenario#9:Verify that racks information API responds correctly when sending POST method to change rack name if remove item rack number in Json area of Rack 1
  Scenario Outline: Verify after remove rack name number
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|rackNumber|1|200|
   	