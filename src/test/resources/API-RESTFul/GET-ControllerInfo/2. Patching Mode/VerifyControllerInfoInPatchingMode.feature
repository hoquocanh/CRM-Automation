Feature: A2. GET Patching mode on Controller Information - Run by API check patching mode


    #Scenario#1: Verify that controller information API responds "patchingMode" value correctly when sending GET method if the patching mode on device is normal
  Scenario Outline:  Verify patching mode Normal
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationC|1|patchingMode|Normal|
   	
   	#Scenario#1: Verify that controller information API responds "patchingMode" value correctly when sending GET method if the patching mode on device is local
  Scenario Outline:  Verify patching mode Local
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationB|1|patchingMode|Local|
   	
   	#Scenario#1: Verify that controller information API responds "patchingMode" value correctly when sending GET method if the patching mode on device is equipment
  Scenario Outline:  Verify patching mode Equipment
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationA|1|patchingMode|Equipment|
   	