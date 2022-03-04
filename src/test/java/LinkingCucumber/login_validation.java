package LinkingCucumber;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/login_validation.feature"}, 
        glue = {"definitions.PanelInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class login_validation extends test_base
{

}

