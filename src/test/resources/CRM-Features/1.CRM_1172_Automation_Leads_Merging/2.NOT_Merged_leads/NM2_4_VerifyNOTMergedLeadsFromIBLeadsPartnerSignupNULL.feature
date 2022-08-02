Feature: NM2_4_Verify that the merging leads do NOT happens when the leads from IB leads, Partner sign up and NULL

#Scenario_CRM-1172_2.4.1:Verify that the merging lead NOT happens  when the leads from IB leads, Partner sign up and NULL 
Scenario Outline: CRM-1172_2.4.1:Verify that the merging lead NOT happens  when the leads from IB leads, Partner sign up and NULL 
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:
When Create a new Target Lead from <Leads file>
When Create a new Source Lead from <Leads file>
When Create a new second Source Lead from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead using same email from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead using same email from <Leads file>  
#Step#3: Observe the second Source Lead
Then Check second Source Lead NOT merged with Target Lead using same email from <Leads file>  


Examples: 
|Leads file|
|NOTMergedLead_IBleadsPartnerSignUpNULL\CRM-1172_2.4.1|   	  
