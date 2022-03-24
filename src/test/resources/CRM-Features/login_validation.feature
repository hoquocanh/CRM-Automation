Feature: Verify totalPanels key value

#Scenario#1:GET_PanelInfo_01_01_Verify that response message show correct "totalPanels" after changing orders when connecting all Copper panel types to ImVisionX
  Scenario Outline: Verify totalPanels key value when connecting all panel types
  Given Launch Odoo Page
  Given Login successfully
  Given Active developer mode
  Given Go to <page>
  #Given Create a new Lead
  #Then Close
    
    Examples: 
   	|page|
   	|CRM|

