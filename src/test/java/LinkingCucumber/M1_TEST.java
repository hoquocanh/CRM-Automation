package LinkingCucumber;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/TEST"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
                        "pretty", "html:target/html-report/cucumber-html-reports.html",
                        "junit:target/cucumber-reports/cucumber.xml"},
		tags= {"@RegressionTest"}
	)	
public class M1_TEST extends test_base
{

}

