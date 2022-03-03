package LinkingCucumber.POST_RackInfo.CheckStatusCodes;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/POST-RackInfo/4. Check status codes/VerifyStatusCode.feature"},				
        glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyStatusCode extends test_base
{

}
