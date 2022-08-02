Feature: M1_1_Verify the automatic Lead merging happens when the leads from the same company email

#Scenario#CRM-1172_1.1.1.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as IB NC Leads
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as IB NC Leads
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

#Scenario#CRM-1172_1.1.1.2:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as Partner sign up
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as Partner sign up
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
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.1.2|

#Scenario#CRM-1172_1.1.1.3:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as NULL
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has Lead Form as NULL
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
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.1.3|

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

#Scenario#CRM-1172_1.1.3.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Lead.Priority  than other
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead has higher Lead.Priority  than other
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Firstly, setup a new Source Lead from <Leads file>  	  
 	When Secondly, setup a new Target Lead from <Leads file>
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead from <Leads file>  
  
    
    Examples: 
   	|Leads file|
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.3.1|

#Scenario#CRM-1172_1.1.4.1:Verify that the merging lead happens successfully when the leads from the same company email but One of lead is created earlier than the other
  Scenario Outline: Verify that the merging lead happens successfully when the leads from the same company email but One of lead is created earlier than the other
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
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.4.1|

#Scenario#CRM-1172_1.1.5.1:Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
  Scenario Outline: Verify that the merging lead happens successfully when One lead is assigned to Install Base team and another lead is assigned to Marketing
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode 
  #Pre-condition:
  When Firstly, setup a new Source Lead from <Leads file>  	  
 	When Secondly, setup a new Target Lead from <Leads file>
  #Step#1: Observe the Target Lead
  Then Check Target Lead after merged with Source Lead from <Leads file>    
  #Step#2: Observe the Source Lead
  Then Check Source Lead after merged with Target Lead from <Leads file>  
  
    
    Examples: 
   	|Leads file|
   	|MergedLead_SameCompanyEmail\CRM-1172_1.1.5.1|

  

  