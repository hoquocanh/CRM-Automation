package utils.report.cucumber;

import cucumber.api.event.TestSourceRead;

public class FeatureModel {
	private String uri;
	private String name;
	private TestSourceRead testSourceRead;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TestSourceRead getTestSourceRead() {
		return testSourceRead;
	}

	public void setTestSourceRead(TestSourceRead testSourceRead) {
		this.testSourceRead = testSourceRead;
	}
}
