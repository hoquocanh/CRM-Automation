Feature: Verify list of key value correctly if panels are connecting

#Scenario#1:GET_PanelInfo_02_01_Verify that response message show the correct information if panel is connecting and when connecting a M4200i copper panel to ImVisionX
  Scenario Outline: Verify list of key value correctly if panels are connecting and when connecting a M4200i copper panel to ImVisionX
  Given Print Panel Information form <configuration> and save that data to <jsonDataPath>
  When Send GET request to collect Panel Information from <keyOfValue> to check data of panel <panelPosition>
  Then Verify that the value of <keyOfValue> meet <expectedValue>
    
    Examples: 
   	|configuration|jsonDataPath|keyOfValue|panelPosition|expectedValue|
   	|configurationC|data/panel_information/data.json|panelNumber|3|3|




