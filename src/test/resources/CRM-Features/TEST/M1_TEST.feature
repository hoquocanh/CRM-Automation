Feature: M1_1_Verify the automatic Lead merging happens when the leads from the same company email

#Scenario#CRM-1172_1.1.1.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as IB NC Leads
  Scenario Outline: CRM-1172_1.1.1.1: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as IB NC Leads
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Create a new Target Lead from <Leads file>
  When Create a new Source Lead from <Leads file>
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead from <Leads file>  
  
    
    Examples: 
   	|Leads file|
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.1.1|
  