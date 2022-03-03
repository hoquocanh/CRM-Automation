Feature: Verify rack number and rack name after changing the value of these items on all racks

#Scenario#1:Verify that racks information API responds correctly when sending POST method to change rack number if modify rack number and rack name on all racks in Zone
  Scenario Outline: Verify rack number on rack 1
  Given Reset data using Json file <initialData> and print Rack Information of <configuration> and save that data to <jsonDataPath>
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
   
    Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|name|1|200|RACK 1|RACK 1|
 
  Scenario Outline: Verify rack name on rack 1
  When Update the value of the key <keyOfValue> at rack <rackPosition> to <inputValue> in <jsonDataPath>
  And Send a POST request to update server information with data updated
  And Send a GET to collect new Rack Number <keyOfValue1> info.
  Then Verify that the POST status code is <expectedCode>
  Then Verify that <keyOfValue> is changed to <inputValue> and meet <expectedValue> 	
   
   Examples: 
   	|configuration|initialData|jsonDataPath|keyOfValue|rackPosition|expectedCode|inputValue|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|data/rack_information/data.json|customerRackNumber|5|200|5|5|	