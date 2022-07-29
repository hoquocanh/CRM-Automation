package LinkingCucumber.NOTMergedLead;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/2.NOT_Merged_leads/VerifyNOTMergedLeadsFromDifferentEmailsDifferentCompanies.feature"}, 				
        glue = {"definitions"},        
		plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
                "pretty", "html:target/html-report/cucumber-html-reports.html",
                "junit:target/cucumber-reports/cucumber.xml"}
	)	
public class NOT_Merged_leads_from_the_different_emails_different_companies extends test_base
{

}