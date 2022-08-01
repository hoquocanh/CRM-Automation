Feature: Verify that the merging leads do NOT happens  when the leads from the different emails and 2 leads have 2 different companies

#Scenario#CRM-1172_1.2.1.1:Verify that the merging lead happens successfully when the leads from the same public email but One of lead has Lead Form as IB NC Leads
  Scenario Outline: CRM-1172_1.2.1.1 Verify that the merging lead happens successfully when the leads from the same public email but One of lead has Lead Form as IB NC Leads
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  #When Create a new Target Lead from <Leads file>  
  #Then Set Target Lead to archived <Leads file>

 Examples: 
   	|Leads file|
   	|MergedLead_SamePublicEmail\CRM-1172_1.2.1.1|   