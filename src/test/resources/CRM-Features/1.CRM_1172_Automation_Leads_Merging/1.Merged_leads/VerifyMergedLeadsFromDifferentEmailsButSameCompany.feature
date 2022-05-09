Feature: Verify the automatic Lead merging happens when the leads from different emails but same company

#Scenario#CRM-1172_1.3.1.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
  Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
  
  Given Test <Leads file> and <Contacts file>
 
  
    
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_SamePublicEmail\CRM-1172_1.2.1.1|Contact\Company_with_2_sub-contacts|

