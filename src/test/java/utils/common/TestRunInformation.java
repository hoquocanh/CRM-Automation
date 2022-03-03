package utils.common;

public class TestRunInformation {
	String methodName;
	String browser;

	public TestRunInformation(String methodName, String browser) {
		this.methodName = methodName;
		this.browser = browser;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getBrowser() {
		return browser;
	}
}
