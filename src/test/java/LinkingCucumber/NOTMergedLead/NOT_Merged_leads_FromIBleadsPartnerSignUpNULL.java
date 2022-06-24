package LinkingCucumber.NOTMergedLead;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/2.NOT_Merged_leads/VerifyNOTMergedLeadsFromIBLeadsPartnerSignupNULL.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener"}
	)	
public class NOT_Merged_leads_FromIBleadsPartnerSignUpNULL extends test_base
{

}