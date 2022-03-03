package LinkingCucumber.GET_ControllerInfo.PatchingMode;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/GET-ControllerInfo/2. Patching Mode/VerifyControllerInfoInPatchingMode.feature"}, 
        glue = {"definitions.ControllerInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyControllerInfoInPatchingMode extends test_base
{

}
