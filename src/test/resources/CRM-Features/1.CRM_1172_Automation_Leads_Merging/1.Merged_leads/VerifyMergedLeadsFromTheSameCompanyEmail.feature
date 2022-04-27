Feature: Verify the automatic Lead merging happens

#Scenario#CRM-1172_1.1.2.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Opp.Stage than other
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Opp.Stage than other
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Firstly, create a new Source Opp from <Leads file>
  
  
  
  #Post-condition:
  Then Close
  
    
    Examples: 
   	|Leads file|
   	|CRM-1172_1.1.2.1|

