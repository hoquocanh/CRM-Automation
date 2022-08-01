package LinkingCucumber;

//import org.apache.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.config.Driver;
import cucumber.api.Scenario;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import pages.GeneralHomePage;
import utils.helper.Logger;

public class test_base extends AbstractTestNGCucumberTests {
	
	private static WebDriver driver=null;
	public static Scenario scenario;
	//private static org.apache.log4j.Logger LOGGER=LogManager.getLogger(test_base.class);
	public test_base()
	{
		//this.driver = GeneralHomePage.getDriver();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() {
		Logger.info("I am insideSetup");
		//context.setAttribute("autoLogBug", autoLogBug);
		//TestRunInformation runInfo = new TestRunInformation(method.getName(), browser);
		//BrowserUtils.setTestRunInformation(runInfo);
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) {
		//DriverUtils.quitBrowser();
		
		GeneralHomePage.getDriver().quit();
		
	}
	
}
