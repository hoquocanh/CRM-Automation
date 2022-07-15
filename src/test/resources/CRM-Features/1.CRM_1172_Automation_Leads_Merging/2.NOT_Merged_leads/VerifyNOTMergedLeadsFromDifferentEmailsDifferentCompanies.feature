Feature: Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

Feature: Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

#Scenario_CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Scenario Outline: CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:
Given Create 2 contacts from <Contacts file>  


Examples: 
|Contacts file|
|Contact\2_companies|   
