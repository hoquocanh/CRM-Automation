package LinkingCucumber.GET_RackInfo.SingleRackMode;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/GET-RackInfo/1. Single rack mode/VerifyRackInfoInSingleRackMode.feature"}, 
        glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
)
public class VerifyRackInfoInSingleRackMode extends test_base 
 {

}
