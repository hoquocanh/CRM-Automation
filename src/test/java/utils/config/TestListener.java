package utils.config;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.event.ConcurrentEventListener;
import cucumber.api.event.EventHandler;
import cucumber.api.event.EventPublisher;
import cucumber.api.event.TestCaseStarted;
import cucumber.api.event.TestRunStarted;
import cucumber.api.event.TestSourceRead;
import cucumber.api.event.TestStepFinished;
import cucumber.api.event.TestStepStarted;
import gherkin.pickles.Pickle;
import gherkin.pickles.PickleLocation;
import utils.report.ExtentManager;
import utils.report.ExtentTestManager;
import utils.report.cucumber.FeatureModel;
import utils.report.cucumber.ScenarioModel;
import utils.report.cucumber.StepModel;
import utils.report.cucumber.TestSourcesModel;

public class TestListener implements ITestListener, ConcurrentEventListener {

	private PickleStepTestStep currentTestStep;	

	private TestSourcesModel testSources = new TestSourcesModel();
	private static final ThreadLocal<FeatureModel> currentFeatureModel = new InheritableThreadLocal<FeatureModel>();
	private static final ThreadLocal<LocalDateTime> startTestStepTime = new InheritableThreadLocal<LocalDateTime>();

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestStart(ITestResult result) {

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onTestSkipped(ITestResult result) {
		FeatureModel featureModel = currentFeatureModel.get();
		testSources.addTestSourceReadEvent(featureModel.getUri(), featureModel.getTestSourceRead());
		ScenarioModel currentScenario = new ScenarioModel();
		Pickle pickle = ExtentTestManager.getCurrentPickleEvent().getPickleEvent().pickle;
		currentScenario.setName(pickle.getName());
		List<String> tags = new ArrayList<>();
		pickle.getTags().forEach(x -> tags.add(x.getName()));
		currentScenario.setTags(tags);
		ExtentTestManager.createScenarioNode(currentScenario, featureModel, Status.SKIP,
				result.getThrowable().getMessage());
		for (int i = 0; i < pickle.getSteps().size(); i++) {
			List<PickleLocation> locations = pickle.getSteps().get(i).getLocations();
			int line = locations.size() == 2 ? locations.get(1).getLine() : locations.get(0).getLine();
			String keyword = testSources.getKeywordFromSource(featureModel.getUri(), line);
			String step = pickle.getSteps().get(i).getText();
			StepModel currentStep = new StepModel(keyword, step, currentTestStep);
			ExtentTestManager.createTestStepNode(currentStep, currentScenario);
			if (i == 0) {
				currentStep.setError(result.getThrowable().getMessage());
				ExtentTestManager.makeStepAsSkipped(currentStep);
			} else {
				ExtentTestManager.makeStepAsSkipped(currentStep);
			}
		}

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	public void onStart(ITestContext context) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		ExtentTestManager.initialReport();
	}

	public void onFinish(ITestContext context) {
		try {
			ExtentManager.getReporter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setEventPublisher(EventPublisher publisher) {
		publisher.registerHandlerFor(TestStepFinished.class, testStepFinishedHandler);
		publisher.registerHandlerFor(TestStepStarted.class, testStepStartedHandler);
		publisher.registerHandlerFor(TestRunStarted.class, testRunStartedHandler);
		publisher.registerHandlerFor(TestSourceRead.class, testSourceReadHandler);
		publisher.registerHandlerFor(TestCaseStarted.class, testCaseStartedHandler);

	}

	private EventHandler<TestCaseStarted> testCaseStartedHandler = new EventHandler<TestCaseStarted>() {
		@Override
		public void receive(TestCaseStarted event) {
			FeatureModel currentFeature = new FeatureModel();
			currentFeature.setUri(event.getTestCase().getUri());
			String featureName = testSources.getFeatureName(event.getTestCase().getUri());
			currentFeature.setName(featureName);

			ScenarioModel currentScenario = new ScenarioModel();
			currentScenario.setName(event.getTestCase().getName());
			List<String> tags = new ArrayList<>();
			event.getTestCase().getTags().stream().forEach(x -> tags.add(x.getName()));
			currentScenario.setTags(tags);
			ExtentTestManager.createScenarioNode(currentScenario, currentFeature);
		}
	};

	private EventHandler<TestRunStarted> testRunStartedHandler = new EventHandler<TestRunStarted>() {
		@Override
		public void receive(TestRunStarted event) {

		}
	};

	private EventHandler<TestStepStarted> testStepStartedHandler = new EventHandler<TestStepStarted>() {
		@Override
		public void receive(TestStepStarted event) {
			if (event.testStep instanceof PickleStepTestStep) {
				PickleStepTestStep pickleStepTestStep = (PickleStepTestStep) event.testStep;
				currentTestStep = pickleStepTestStep;
				ScenarioModel currentScenario = new ScenarioModel();
				currentScenario.setName(event.getTestCase().getName());
				String keyword = testSources.getKeywordFromSource(event.getTestCase().getUri(),
						pickleStepTestStep.getStepLine());
				String step = pickleStepTestStep.getStepText();
				StepModel currentStep = new StepModel(keyword, step, currentTestStep);
				ExtentTestManager.createTestStepNode(currentStep, currentScenario);
				startTestStepTime.set(LocalDateTime.now());
			}
		}
	};

	private EventHandler<TestStepFinished> testStepFinishedHandler = new EventHandler<TestStepFinished>() {
		@Override
		public void receive(TestStepFinished event) {
			if (event.testStep instanceof PickleStepTestStep) {
				String keyword = testSources.getKeywordFromSource(event.getTestCase().getUri(),
						currentTestStep.getStepLine());
				String step = currentTestStep.getStepText();
				StepModel currentStep = new StepModel(keyword, step, currentTestStep);

				if (event.result.getStatus() == Result.Type.SKIPPED) {
					ExtentTestManager.makeStepAsSkipped(currentStep);
				} else if (event.result.getStatus() == Result.Type.FAILED) {					
					currentStep.setError(event.result.getErrorMessage());
					if (event.result.getError() instanceof AssertionError) {
						ExtentTestManager.makeStepAsFailed(currentStep);
					} else {
						ExtentTestManager.makeStepAsErrored(currentStep);
					}
				}				
			}
		}
	};

	private EventHandler<TestSourceRead> testSourceReadHandler = new EventHandler<TestSourceRead>() {
		@Override
		public void receive(TestSourceRead event) {
			testSources.addTestSourceReadEvent(event.uri, event);
			FeatureModel currentFeature = new FeatureModel();
			currentFeature.setUri(event.uri);
			currentFeature.setName(testSources.getFeatureName(event.uri));
			currentFeature.setTestSourceRead(event);
			currentFeatureModel.set(currentFeature);
			if (!ExtentTestManager.isFeatureExisted(currentFeature)) {
				ExtentTestManager.createFeatureNode(currentFeature);
			}
		}
	};
}
