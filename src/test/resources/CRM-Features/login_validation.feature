Feature: Verify totalPanels key value

#Scenario#1:GET_PanelInfo_01_01_Verify that response message show correct "totalPanels" after changing orders when connecting all Copper panel types to ImVisionX
  Scenario Outline: Verify totalPanels key value when connecting all panel types
  Given Print A of <configuration>
  When Login
  Then Close
    
    Examples: 
   	|configuration|
   	|configurationC|

