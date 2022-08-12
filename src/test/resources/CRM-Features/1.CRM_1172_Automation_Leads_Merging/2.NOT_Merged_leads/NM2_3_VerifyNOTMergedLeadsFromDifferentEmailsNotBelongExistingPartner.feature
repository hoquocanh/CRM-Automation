Feature: NM2_3_Verify that the merging leads do NOT happens  when the leads from the different emails, not belong to a domain of an existing partner

#Scenario_CRM-1172_2.3.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads are not belong to a domain of an existing partner
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.3.1:Verify that the merging leads do NOT happens  when the leads from different emails and 2 leads are not belong to a domain of an existing partner
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:  
When Create a new Target Lead using different email domain from <Leads file>
When Create a new Source Lead using different email domains from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead from <Leads file>  


Examples: 
|Leads file|
|NOTMergedLead_DifferentEmailsNotBelongExistingPartner\CRM-1172_2.3.1|   

#Scenario_CRM-1172_2.3.2:Verify that the merging leads do NOT happens  when the leads from different emails and a lead belong to an existing domain, the other is not
@RegressionTest @SmokeTest
Scenario Outline: CRM-1172_2.3.2:Verify that the merging leads do NOT happens  when the leads from different emails and a lead belong to an existing domain, the other is not
Given Launch Odoo Page
Given Login successfully
Given Active developer mode 
#Pre-condition:  
Given Create 1 contact from <Contacts file>  
Given Create a new Target Lead using contact from <Leads file>
When Create a new Source Lead using different email domains from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead NOT merged with Source Lead using contact from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead NOT merged with Target Lead from <Leads file>  

Examples: 
|Leads file|Contacts file|
|NOTMergedLead_DifferentEmailsNotBelongExistingPartner\CRM-1172_2.3.2|Contact\1_company|    