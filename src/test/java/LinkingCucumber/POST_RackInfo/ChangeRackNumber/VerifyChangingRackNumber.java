package LinkingCucumber.POST_RackInfo.ChangeRackNumber;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/POST-RackInfo/2. Change Rack Number/VerifyChangingRackNumber.feature"}, 
        glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyChangingRackNumber extends test_base
{

}
