Feature: Verify the automatic Lead merging happens when the leads from different emails but same company

#Scenario#CRM-1172_1.3.1.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
  Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as IB NC Leads
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
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.1.1|Contact\Company_with_2_sub-contacts|

#Scenario#CRM-1172_1.3.1.2:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as Partner sign up
  Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as Partner sign up
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
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.1.2|Contact\Company_with_2_sub-contacts|

#Scenario#CRM-1172_1.3.1.3:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as NULL
  Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has Lead Form as NULL
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
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.1.3|Contact\Company_with_2_sub-contacts|


#Scenario#CRM-1172_1.3.2.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Opp.Stage than other
Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Opp.Stage than other
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
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.2.1|Contact\Company_with_2_sub-contacts|

#Scenario#CRM-1172_1.3.3.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Lead.Priority  than other
Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead has higher Lead.Priority  than other
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  Given Create a Contact and its child contacts from <Contacts file>  
  #Notice: Create Source Lead first, then Tartget Lead
  Given Create a new Source Lead using Contact child from <Leads file>
  Given Create a new Target Lead using Contact child from <Leads file>  
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead using Contact child from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead using Contact child from <Leads file>  
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.3.1|Contact\Company_with_2_sub-contacts|

#Scenario#CRM-1172_1.3.4.1:Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead is created earlier than the other
Scenario Outline: Verify that the merging lead happens successfully when the leads from different emails but same company in Odoo but One of lead is created earlier than the other
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
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.4.1|Contact\Company_with_2_sub-contacts|

#Scenario#CRM-1172_1.3.5.1:Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
Scenario Outline: Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  Given Create a Contact and its child contacts from <Contacts file>    
  #Create new Source Lead first, then Target Lead
  Given Create a new Source Lead using Contact child from <Leads file>
  Given Create a new Target Lead using Contact child from <Leads file>      
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead using Contact child from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead using Contact child from <Leads file>  
  #Post-condition:
  Then Close
    Examples: 
   	|Leads file|Contacts file|
   	|MergedLead_DifferentEmailButSameCompany\CRM-1172_1.3.5.1|Contact\Company_with_2_sub-contacts|



  