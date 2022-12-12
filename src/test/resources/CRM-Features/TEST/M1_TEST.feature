Feature: M1_1_Verify the automatic Lead merging happens when the leads from the same company email


#Scenario#CRM-1172_1.1.1.2:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as Partner sign up
  @RegressionTest @SmokeTest
  Scenario Outline: CRM-1172_1.1.1.2:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as Partner sign up
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Create a new Target Lead from <Leads file>
  
  
    
    Examples: 
   	|Leads file|
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.1.2|

