package LinkingCucumber;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import cucumber.api.testng.AbstractTestNGCucumberTests;

public class test_base extends AbstractTestNGCucumberTests {
	

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		//context.setAttribute("autoLogBug", autoLogBug);
		//TestRunInformation runInfo = new TestRunInformation(method.getName(), browser);
		//BrowserUtils.setTestRunInformation(runInfo);
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		//DriverUtils.quitBrowser();
	}
}
