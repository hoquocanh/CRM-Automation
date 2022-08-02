Feature: 1.Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

#Scenario_CRM-1172_2.5.1:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to CM_Channel_Management
Scenario Outline: CRM-1172_2.5.1:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to CM_Channel_Management
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:  
When Create a new Target Lead from <Leads file>
When Create a new Source Lead from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead using same email from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead using same email from <Leads file>  

Examples: 
|Leads file|
|NOTMergedLead_OneLeadAssignedToInstallBaseteam\CRM-1172_2.5.1|