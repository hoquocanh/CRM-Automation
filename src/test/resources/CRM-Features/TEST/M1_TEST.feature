Feature: M1_3_Verify the automatic Lead merging happens when the leads from different emails but same company

#Scenario#CRM-1172_1.3.1.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
  Scenario Outline: CRM-1172_1.3.1.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  Given Create a Contact and its child contacts from <Contacts file>  
  Given Create a new Target Lead using Contact child from <Leads file>
  Given Create a new Source Lead using Contact child from <Leads file>
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead using Contact child from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead using Contact child from <Leads file>  
  
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.1.1|Contact\Company_with_2_sub-contacts|

