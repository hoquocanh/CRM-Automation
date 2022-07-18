Feature: Verify the automatic Lead merging happens when the leads from different emails but same partner

#Scenario_CRM-1172_1.4.1.1:Verify that the merging lead happens successfully when Multiple  leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as IB NC Leads
Scenario Outline: CRM-1172_1.4.1.1_Verify that the merging lead happens successfully when Multiple  leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as IB NC Leads
Given Launch Odoo Page
Given Login successfully
Given Active developer mode   
#Pre-condition:
Given Create a Reseller from <Contacts file>  
Given Create a new Target Lead using Reseller from <Leads file>
Given Create a new Source Lead using Reseller from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Lead after merged with Source Lead using Reseller from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead after merged with Target Lead using Reseller from <Leads file>  
#Post-condition:
Then Close
  
Examples: 
|Leads file|Contacts file|
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.1.1|Contact\Reseller|




