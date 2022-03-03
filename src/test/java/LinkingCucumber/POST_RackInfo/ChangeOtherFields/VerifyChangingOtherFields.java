package LinkingCucumber.POST_RackInfo.ChangeOtherFields;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/API-RESTFul/POST-RackInfo/3. Change Other fields/VerifyChangingOtherFields.feature"}, 
		glue = {"definitions.RackInformation"},
        plugin = {"utils.config.TestListener"}
	)	
public class VerifyChangingOtherFields extends test_base
{

}
