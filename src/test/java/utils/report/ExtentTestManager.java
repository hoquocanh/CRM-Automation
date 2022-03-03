package utils.report;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import cucumber.api.PickleStepTestStep;
import cucumber.api.testng.PickleEventWrapper;
import utils.report.cucumber.FeatureModel;
import utils.report.cucumber.ScenarioModel;
import utils.report.cucumber.StepModel;

public class ExtentTestManager {
	private static String workingDir = System.getProperty("user.dir");
	private static String resultFileName = "ReportResults.html";
	private static String folderResultName = "ReportResults";
	private static String screenshotsFolderName = "screenshots";

	static Map<String, ExtentTest> featureMap = new HashMap<String, ExtentTest>();
	static Map<String, ExtentTest> scenarioMap = new HashMap<String, ExtentTest>();
	static Map<PickleStepTestStep, ExtentTest> stepMap = new HashMap<PickleStepTestStep, ExtentTest>();
	private static ThreadLocal<PickleEventWrapper> currentPickleEvent = new InheritableThreadLocal<PickleEventWrapper>();
	static String outputFolder;
	static ExtentReports extent;
	static ThreadLocal<ExtentTest> stepTestThreadLocal = new InheritableThreadLocal<>();

	public static synchronized boolean isFeatureExisted(FeatureModel currentFeature) {
		return featureMap.containsKey(currentFeature.getUri());
	}

	public static synchronized void initialReport() {
		try {
			if (ExtentManager.isReportCreated())
				extent = ExtentManager.getReporter();
			else {
				outputFolder = "test-output" + System.getProperty("file.separator") + folderResultName;
				String path = workingDir + System.getProperty("file.separator") + outputFolder
						+ System.getProperty("file.separator") + resultFileName;
				extent = ExtentManager.getReporter(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void createFeatureNode(FeatureModel currentFeature) {
		try {
			ExtentTest feature = extent.createTest(Feature.class, currentFeature.getName());
			featureMap.put(currentFeature.getUri(), feature);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void createScenarioNode(ScenarioModel currentScenario, FeatureModel currentFeature) {
		try {
			ExtentTest scenario = featureMap.get(currentFeature.getUri()).createNode(Scenario.class,
					currentScenario.getName());
			if (currentScenario.getTags() != null) {
				scenario.assignCategory(
						currentScenario.getTags().toArray(new String[currentScenario.getTags().size()]));
			}
			scenarioMap.put(currentScenario.getName(), scenario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void createScenarioNode(ScenarioModel currentScenario, FeatureModel currentFeature,
			Status status, String message) {
		try {
			ExtentTest scenario = featureMap.get(currentFeature.getUri()).createNode(Scenario.class,
					currentScenario.getName());
			if (currentScenario.getTags() != null) {
				scenario.assignCategory(
						currentScenario.getTags().toArray(new String[currentScenario.getTags().size()]));
			}
			if (status == Status.SKIP) {
				scenario.skip(MarkupHelper.createCodeBlock(message));
			}
			scenarioMap.put(currentScenario.getName(), scenario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void createTestStepNode(StepModel currentStep, ScenarioModel currentScenario) {
		try {
			ExtentTest step = scenarioMap.get(currentScenario.getName()).createNode(
					new GherkinKeyword(currentStep.getKeyword()),
					currentStep.getKeyword() + " " + currentStep.getName());

			stepMap.put(currentStep.getCurrentTestStep(), step);
			stepTestThreadLocal.set(step);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void makeStepAsFailed(StepModel currentStep) {
		try {
			stepMap.get(currentStep.getCurrentTestStep()).fail(MarkupHelper.createCodeBlock(currentStep.getError()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void addStepLog(String message) {
		stepTestThreadLocal.get().info(String.format(
				"<p style=\"border: 1px solid #ebedef; border-radius: 4px; font-size: 13px; font-family: Consolas,monospace;  margin: 0.1;  padding: 7px 15px; font-family: Consolas,monospace;\">[Info] - %s</p>",
				message));
	}

	public static synchronized void makeStepAsErrored(StepModel currentStep) {
		try {
			stepMap.get(currentStep.getCurrentTestStep()).error(MarkupHelper.createCodeBlock(currentStep.getError()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void makeStepAsSkipped(StepModel currentStep) {
		try {
			stepMap.get(currentStep.getCurrentTestStep()).skip(currentStep.getError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized void makeStepAsSkipped(StepModel currentStep, String screenShot) {
		try {
			stepMap.get(currentStep.getCurrentTestStep()).skip(MarkupHelper.createCodeBlock(currentStep.getError()));
			stepMap.get(currentStep.getCurrentTestStep()).addScreenCaptureFromPath(screenShot);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static synchronized String getScreenshotFolder() {
		String path = outputFolder + System.getProperty("file.separator") + screenshotsFolderName;
		File output = new File(path);
		if (!output.exists())
			output.mkdir();
		return path;
	}

	public static synchronized String getOutputFolder() {
		return outputFolder;
	}

	public static synchronized String getScreenshotsFolderName() {
		return screenshotsFolderName;
	}

	public static PickleEventWrapper getCurrentPickleEvent() {
		return ExtentTestManager.currentPickleEvent.get();
	}

	public static void setCurrentPickleEvent(PickleEventWrapper currentPickleEvent) {
		ExtentTestManager.currentPickleEvent.set(currentPickleEvent);
	}
}