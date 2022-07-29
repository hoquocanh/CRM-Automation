package LinkingCucumber;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/1.CRM_1172_Automation_Leads_Merging"}, 		
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-regression-reports/cucumber.json",
                        "pretty", "html:target/html-report/cucumber-html-reports.html",
                        "junit:target/cucumber-regression-reports/cucumber.xml"}
	)	
public class Merged_leads_feature extends test_base
{

}

