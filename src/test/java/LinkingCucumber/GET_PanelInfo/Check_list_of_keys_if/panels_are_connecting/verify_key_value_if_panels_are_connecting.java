package LinkingCucumber.GET_PanelInfo.Check_list_of_keys_if.panels_are_connecting;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/GET-Panel-Information/2. Show correct key's value if/panels are connecting/verify_key_value_if_panels_are_connecting.feature"}, 
        glue = {"definitions.PanelInformation"},
        plugin = {"utils.config.TestListener"}
	)	

public class verify_key_value_if_panels_are_connecting extends test_base{

}
