package LinkingCucumber.GET_PanelInfo.CheckTotalPanels;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/GET-PanelInfo/1. Check totalPanels key/VerifyTotalPanels.feature"}, 
        glue = {"definitions.PanelInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyTotalPanels extends test_base
{

}

