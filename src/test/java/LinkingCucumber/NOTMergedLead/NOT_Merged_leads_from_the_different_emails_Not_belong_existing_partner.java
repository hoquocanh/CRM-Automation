package LinkingCucumber.NOTMergedLead;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/2.NOT_Merged_leads/VerifyNOTMergedLeadsFromDifferentEmailsNotBelongExistingPartner.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener"}
	)	
public class NOT_Merged_leads_from_the_different_emails_Not_belong_existing_partner extends test_base
{

}