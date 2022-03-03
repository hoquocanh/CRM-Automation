Feature: A1. GET Controller Information - Run by API check racks in single rack mode

	#Scenario#1:Verify that controller information will show correctly in RESTful API response message in single rack mode when checking language
  Scenario Outline:  Verify that language shows correctly
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationB|1|language|English|

   #Scenario#2:Verify that controller information will show correctly in RESTful API response message in single rack mode when checking displays information in a connecting displays
  Scenario Outline:  Verify that display information exist
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the <key> existed in Controller information
    
    
    Examples: 
   	|configuration|rackPosition|key|
   	|configurationB|1|display|
    
    #Scenario#3:Verify that controller information will show correctly in RESTful API response message in single rack mode when checking vendor
  Scenario Outline:  Verify that vendor shows correctly
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationB|1|vendor|CommScope|

   #Scenario#4:Verify that controller information will show correctly in RESTful API response message in single rack mode when checking type
  Scenario Outline:  Verify that type shows correctly
  	Given Print Rack Information of <configuration>
  	When Send a GET request to collect <key> infomation from Controller to check data of rack <rackPosition>
    Then Verify the value of <key> in Controller information meet <expectedValue>
    
    
    Examples: 
   	|configuration|rackPosition|key|expectedValue|
   	|configurationB|1|type|imVision Controller X|
    
    