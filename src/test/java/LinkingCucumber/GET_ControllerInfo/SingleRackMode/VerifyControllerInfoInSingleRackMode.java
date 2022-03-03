package LinkingCucumber.GET_ControllerInfo.SingleRackMode;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/GET-ControllerInfo/1. Single rack mode/VerifyControllerInSingleRackMode.feature"}, 
        glue = {"definitions.ControllerInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyControllerInfoInSingleRackMode extends test_base
{

}
