package LinkingCucumber.POST_RackInfo.ChangeRackName;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/POST-RackInfo/1. Change Rack Name/VerifyChangingRackName.feature"},
				
        glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyChangingRackName extends test_base
{

}
