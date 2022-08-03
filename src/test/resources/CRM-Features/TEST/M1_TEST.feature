Feature: M1_1_Verify the automatic Lead merging happens when the leads from the same company email



#Scenario#CRM-1172_1.1.2.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Opp.Stage than other
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Opp.Stage than other
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Firstly, create a new Source Opportunity from <Leads file>
 When Secondly, create a new Target Opportunity from <Leads file>
  #Step#1: Observe the Target Lead
  Then Check Target Opportunity after merged with Source Opportunity from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Opportunity after merged with Target Opportunity from <Leads file>  
 
	
	Examples: 
   	|Leads file|
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.2.1|



  

  