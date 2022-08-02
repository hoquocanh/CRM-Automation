package LinkingCucumber.MergedLead;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging/1.Merged_leads/M1_2_VerifyMergedLeadsFromTheSamePublicEmail.feature"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
                        "pretty", "html:target/html-report/cucumber-html-reports.html",
                        "junit:target/cucumber-reports/cucumber.xml"}
	)	
public class M1_2_Merged_leads_from_the_same_public_email extends test_base
{

}

