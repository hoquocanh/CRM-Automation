package utils.report.cucumber;

import cucumber.api.PickleStepTestStep;

public class StepModel {

	private String name;
	private String keyword;
	private String error;
	private PickleStepTestStep currentTestStep;

	public StepModel(String keyword, String name, PickleStepTestStep currentTestStep) {
		this.keyword = keyword;
		this.name = name;
		this.currentTestStep = currentTestStep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public PickleStepTestStep getCurrentTestStep() {
		return currentTestStep;
	}

	public void setCurrentTestStep(PickleStepTestStep currentTestStep) {
		this.currentTestStep = currentTestStep;
	}

}
