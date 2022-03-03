Feature: Verify Rack Info in signle rack mode 

#Scenario#1:Verify that rack information API request show the information of Rack correctly in RESTful API response message if in single rack mode when  the rack status is "Good"
  Scenario Outline:  Verify  the rack status is "Good"
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationA|data/rack_information/initialData_configA.json|1|status|OK|

   #Scenario#2:Verify that rack information API request show the information of Rack correctly in RESTful API response message if in single rack mode when  the rack status is "Not Communicating"
  Scenario Outline:  Verify  the rack status is "Not Communicating" for a not working imVisionX
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationA|data/rack_information/initialData_configA.json|-1|status|NotCommunicating|

   #Scenario#3: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in single rack mode when checking the "name" item on "Not Communicating" rack is blank
  Scenario Outline:  Verify the "name" item on "Not Communicating" rack is blank
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationA|data/rack_information/initialData_configA.json|-1|name| |
   
    #Scenario#4: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in single rack mode when checking the "managedBy" item on a "Good" rack shows correctly
  Scenario Outline:  Verify  the "managedBy" item on a "Good" rack shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Check this rack is managed by rack position <masterRackPosition>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|masterRackPosition|
   	|configurationA|data/rack_information/initialData_configA.json|1|managedBy|1|
   
   #Scenario#5: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in single rack mode when checking the "powerUsagePercent" item on a "Good" rack shows correctly
  Scenario Outline:  Verify  the "powerUsagePercent" item on a "Good" rack shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationA|data/rack_information/initialData_configA.json|1|powerUsagePercent|2|
