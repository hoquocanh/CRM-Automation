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

#Scenario_CRM-1172_1.4.1.2:Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as Partner sign up
Scenario Outline: Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as Partner sign up
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
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.1.2|Contact\Reseller|

#Scenario_CRM-1172_1.4.1.3:Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as NULL
Scenario Outline: Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has Lead Form as NULL
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
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.1.3|Contact\Reseller|

#Scenario_CRM-1172_1.4.2.1:Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has higher Opp.Stage than other
Scenario Outline: Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has higher Opp.Stage than other
Given Launch Odoo Page
Given Login successfully
Given Active developer mode   
#Pre-condition:
Given Create a Reseller from <Contacts file>  
Given Firstly, setup a new Source Opportunity using Reseller contact from <Leads file>
Given Secondly, setup a new Target Opportunity using Reseller contact from <Leads file>
#Step#1: Observe the Target Lead
Then Check Target Opportunity after merged with Source Opportunity using Reseller contact from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Opportunity after merged with Target Opportunity using Reseller contact from <Leads file>  
#Post-condition:
Then Close

Examples: 
|Leads file|Contacts file|
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.2.1|Contact\Reseller|

#Scenario_CRM-1172_1.4.3.1:Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has higher Lead.Priority  than other
Scenario Outline: Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead has higher Lead.Priority  than other
Given Launch Odoo Page
Given Login successfully
Given Active developer mode   
#Pre-condition:
Given Create a Reseller from <Contacts file>  
Given Create a new Source Lead using Reseller from <Leads file>
Given Create a new Target Lead using Reseller from <Leads file>  
#Step#1: Observe the Target Lead
Then Check Target Lead after merged with Source Lead using Reseller from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead after merged with Target Lead using Reseller from <Leads file>  
#Post-condition:
Then Close

Examples: 
|Leads file|Contacts file|
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.3.1|Contact\Reseller|

#Scenario_CRM-1172_1.4.4.1:Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead is created earlier than the other
Scenario Outline: Verify that the merging lead happens successfully when Multiple leads from different emails, not belong to the same company but the domain belongs to an existing partner but One of lead is created earlier than the other
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
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.4.1|Contact\Reseller|

#Scenario_CRM-1172_1.4.5.1:Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
Scenario Outline: Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
Given Launch Odoo Page
Given Login successfully
Given Active developer mode   
#Pre-condition:
Given Create a Reseller from <Contacts file>  
Given Create a new Source Lead using Reseller from <Leads file>
Given Create a new Target Lead using Reseller from <Leads file>  
#Step#1: Observe the Target Lead
Then Check Target Lead after merged with Source Lead using Reseller from <Leads file>    
#Step#2: Observe the Source Lead
Then Check Source Lead after merged with Target Lead using Reseller from <Leads file>  
#Post-condition:
Then Close

Examples: 
|Leads file|Contacts file|
|MergedLead_DifferentEmailButSamePartner\CRM-1172_1.4.5.1|Contact\Reseller|

