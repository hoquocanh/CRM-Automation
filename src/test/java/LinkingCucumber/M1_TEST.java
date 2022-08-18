package LinkingCucumber;

import LinkingCucumber.test_base;
import cucumber.api.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/CRM-Features/TEST"}, 				
        glue = {"definitions"},
        plugin = {"utils.config.TestListener","json:target/cucumber-reports/cucumber.json",
        		"pretty", "html:target/html-report/cucumber-html-reports.html",
        "junit:target/cucumber-smoketest-reports/cucumber.xml"}
	)	
public class M1_TEST extends test_base
{

}

