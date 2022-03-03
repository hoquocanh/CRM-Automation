package LinkingCucumber.GET_RackInfo.MultiRackMode;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		
		features = {"src/test/resources/API-RESTFul/GET-RackInfo/2. Multi rack mode/VerifyRackInfoInMultiRackMode.feature"},
        glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
)
public class VerifyRackInfoInMultiRackMode extends test_base 
 {

}
