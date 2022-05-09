package LinkingCucumber.MergedLead;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/1.Merged_leads/VerifyMergedLeadsFromDifferentEmailsButSameCompany.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener"}
	)	
public class Merged_leads_from_different_emails_but_same_company extends test_base
{

}

