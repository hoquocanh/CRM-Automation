package LinkingCucumber.MergedLead;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/1.Merged_leads/VerifyMergedLeadsFromTheSamePublicEmail.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener"}
	)	
public class Merged_leads_from_the_same_public_email extends test_base
{

}

