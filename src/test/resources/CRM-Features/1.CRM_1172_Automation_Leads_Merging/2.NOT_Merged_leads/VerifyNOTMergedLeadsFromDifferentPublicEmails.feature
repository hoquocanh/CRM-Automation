Feature: Verify that the merging leads do NOT happens when the leads from the different emails and different companies

#Scenario_CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Scenario Outline: CRM-1172_2.2.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads have 2 different companies
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:
When Create a new Target Lead using different email domain from <Leads file>
When Create a new Source Lead using different email domain from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead from <Leads file>  
#Post-condition:
Then Close

Examples: 
|Leads file|
|NOTMergedLead_DifferentPublicEmail\CRM-1172_2.1.1|   	  
