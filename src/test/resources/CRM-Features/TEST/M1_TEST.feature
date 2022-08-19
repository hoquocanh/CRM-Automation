Feature: M1_3_Verify the automatic Lead merging happens when the leads from different emails but same company

#Scenario#CRM-1172_1.3.2.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Opp.Stage than other
@RegressionTest @Bug @Bug-CRM-1419 @Bug-CRM-1207
Scenario Outline: CRM-1172_1.3.2.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Opp.Stage than other
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  Given Create a Contact and its child contacts from <Contacts file>  
  Given Firstly, create a new Source Opportunity using Contact child from <Leads file>
  Given Secondly, create a new Target Opportunity using Contact child from <Leads file>
  #Step#1: Observe the Target Lead
	Then Check Target Opportunity after merged with Source Opportunity using Contact child from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Opportunity after merged with Target Opportunity using Contact child from <Leads file>    
 
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.2.1|Contact\Company_with_2_sub-contacts|