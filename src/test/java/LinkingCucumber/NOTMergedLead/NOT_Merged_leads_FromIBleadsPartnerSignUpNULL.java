package LinkingCucumber.NOTMergedLead;
import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/2.NOT_Merged_leads/VerifyNOTMergedLeadsFromIBLeadsPartnerSignupNULL.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
                        "pretty", "html:target/html-report/cucumber-html-reports.html",
                        "junit:target/cucumber-reports/cucumber.xml"}
	)	
public class NOT_Merged_leads_FromIBleadsPartnerSignUpNULL extends test_base
{

}