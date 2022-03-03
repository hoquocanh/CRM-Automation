Feature: Verify other fields when POST Rack Information

#Scenario#1:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if change the value of totalRacks
  Scenario Outline: Verify total rack after changed its value
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key totalRacks <keyOfValue>  to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request and collect totalRacks <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|totalRacks|200|5|

#Scenario#2:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if change the value of id
  Scenario Outline: Verify id after changed value
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|id|1|400|0123|
   	
   	#Scenario#3:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if change the value of status
  Scenario Outline: Verify status after changed value
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|status|1|200|a|

   	#Scenario#4:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if change the value of managedBy
  Scenario Outline: Verify managedBy after changed value
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|managedBy|1|200|a|

   	#Scenario#5:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if change the value of iPatchRackUnitsAvailable
  Scenario Outline: Verify iPatchRackUnitsAvailable after changed value
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|iPatchRackUnitsAvailable|1|200|5|

   	#Scenario#6:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if remove item totalRacks in Json area of Rack 1
  Scenario Outline: Verify after remove totalRacks key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove totalRacks key <keyOfValue> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request and collect totalRacks <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|totalRacks|1|200|
   	
   	#Scenario#7:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if remove item id in Json area of Rack 1
  Scenario Outline: Verify after remove id key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|id|1|400|
   	
   	#Scenario#8:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if remove item status in Json area of Rack 1
  Scenario Outline: Verify after remove status key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|status|1|200|
   	
   	#Scenario#9:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if remove item managedBy in Json area of Rack 1
  Scenario Outline: Verify after remove managedBy key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|managedBy|1|200|
   	
   	#Scenario#10:Verify that racks information API responds correctly when sending POST method to change other keys except rack name / customer rack number if remove item iPatchRackUnitsAvailable in Json area of Rack 1
  Scenario Outline: Verify after remove iPatchRackUnitsAvailable key
  Given Reset data of Rack Information using Json file <initialData> and show that information of <configuration> and save that data to <jsonDataPath>
  When Remove key <keyOfValue> at rack <rackPosition> and update to <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET request to collect <keyOfValue> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that the value of <keyOfValue> is show the old value
    
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|iPatchRackUnitsAvailable|1|200|