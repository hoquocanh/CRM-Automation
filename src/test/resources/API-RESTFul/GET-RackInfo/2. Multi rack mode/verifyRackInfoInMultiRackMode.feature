Feature: Verify Rack Info in multi rack mode

#Scenario#1: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of ImVisionX when checking the "iPatchRackUnitsAvailable" item of the imVisionX shows correctly
  Scenario Outline:  Verify "iPatchRackUnitsAvailable" item of the imVisionX shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|1|iPatchRackUnitsAvailable|9|
   
   #Scenario#2: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of ImVisionX when checking the "iPatchRackUnitsAvailable" item of the dependent rack shows correctly
  Scenario Outline:  Verify "iPatchRackUnitsAvailable" item of the dependent rack shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|2|iPatchRackUnitsAvailable|9|
   
   #Scenario#3: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of ImVisionX when checking the "managedBy" item of the imVisionX shows correctly
  Scenario Outline:  Verify "managedBy" item of the imVisionX shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Check this rack is managed by rack position <masterRackPosition>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|masterRackPosition|
   	|configurationB|data/rack_information/initialData_configB.json|1|managedBy|1|
   
    #Scenario#4: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of ImVisionX when checking the "managedBy" item of the dependent rack shows correctly
  Scenario Outline:  Verify "managedBy" item of the dependent rack shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Check this rack is managed by rack position <masterRackPosition>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|masterRackPosition|
   	|configurationB|data/rack_information/initialData_configB.json|2|managedBy|1|
   
   #Scenario#5: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of ImVisionX when checking the "customerRackNumber" item of the imVisionX shows correctly
  Scenario Outline:  Verify "customerRackNumber" item of the imVisionX shows correctly
  	Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|1|rackNumber|1|

	#Scenario#6: Verify that rack information API request show the information of Rack correctly in RESTful API response message if in multi-rack mode including a dependent rack attaches to <-- port of dependent rack when checking the "customerRackNumber" item of the dependent rack shows correctly
  Scenario Outline:  Verify "customerRackNumber" item of the dependent rack shows correctly
  	 Given Reset data using Json file <jsonDataPath> and print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation to check data of rack <rackPosition>
    Then Verify that value of <key> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|rackPosition|key|expectedValue|
   	|configurationB|data/rack_information/initialData_configB.json|2|rackNumber|2|

   
   