package LinkingCucumber;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/TEST"}, 		
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
                        "pretty", "html:target/html-report/cucumber-html-reports.html",
                        "junit:target/cucumber-regression-reports/cucumber.xml"},
		tags= {"@SmokeTest"}
	)	
public class SmokeTest_Merged_leads_feature extends test_base
{

}

