Feature: NM2_5_Verify that the merging leads do NOT happens when One lead is assigned to Install Base team and another lead is assigned to another team except Marketing

#Scenario_CRM-1172_2.5.1:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to CM_Channel_Management
@RegressionTest @SmokeTest
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

#Scenario_CRM-1172_2.5.2:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to CMD
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.5.2:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to CMD
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
|NOTMergedLead_OneLeadAssignedToInstallBaseteam\CRM-1172_2.5.2|

#Scenario_CRM-1172_2.5.3:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to Field Sales
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.5.3:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to Field Sales
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
|NOTMergedLead_OneLeadAssignedToInstallBaseteam\CRM-1172_2.5.3|

#Scenario_CRM-1172_2.5.4:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to EAM
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.5.4:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to EAM
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
|NOTMergedLead_OneLeadAssignedToInstallBaseteam\CRM-1172_2.5.4|

#Scenario_CRM-1172_2.5.5:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to BD_America
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.5.5:Verify that the merging lead NOT happens when One lead is assigned to Install Base team and another lead is assigned to BD_America
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
|NOTMergedLead_OneLeadAssignedToInstallBaseteam\CRM-1172_2.5.5|  

