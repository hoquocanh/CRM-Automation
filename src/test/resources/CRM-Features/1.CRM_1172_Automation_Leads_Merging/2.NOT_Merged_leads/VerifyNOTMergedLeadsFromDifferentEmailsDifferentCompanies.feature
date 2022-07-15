Feature: Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

Feature: Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

#Scenario_CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Scenario Outline: CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:
Given Create 2 contacts from <Contacts file>  
Given Create a new Target Lead using contact from <Leads file>
Given Create a new Source Lead using contact from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead using contact from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead using contact from <Leads file>  
#Post-condition:
Then Close

Examples: 
|Leads file|Contacts file|
|NOTMergedLead_DifferentEmailsDifferentCompanies\CRM-1172_2.2.1|Contact\2_companies|   
