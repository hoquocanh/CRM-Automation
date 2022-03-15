package pages;


import utils.common.Constants;

public class LoginPage extends GeneralHomePage {

	

	public LoginPage open() {
		DriverUtils.getWebDriver().navigate().to(Constants.COGITO_URL);
		return this;
	}

	public boolean isLoginFunctionEnabled() {
		return btnLogin.isEnabled();
	}

	public AgentHomePage LoginAsAgent(String username, String password) {
		loginSuccessfully(username, password);
		return new AgentHomePage();
	}

	public SupervisorHomePage LoginAsSupervisor(String username, String password) {
		loginSuccessfully(username, password);
		return new SupervisorHomePage();
	}

	public LoginPage LoginFail(String username, String password) {
		submitLoginForm(username, password);
		return this;
	}

	public void loginSuccessfully(String username, String password) {
		submitLoginForm(username, password);
		waitForHomePageLoad();
	}

	public LoginPage submitLoginForm(String username, String password) {
		enterLoginForm(username, password);
		btnLogin.click();
		return this;
	}

	public LoginPage enterLoginForm(String username, String password) {
		icoLoading.waitForElementNotPresent(Constants.LONG_TIME);
		txtUsername.click();
		txtUsername.clear();
		txtUsername.enter(username);

		txtPassword.click();
		txtPassword.clear();
		txtPassword.enter(password);
		return this;
	}

	public LoginPage clickLoginButton() {
		btnLogin.waitForElementClickable();
		btnLogin.click();
		return this;
	}

	public String getErrorMessage() {
		lblErrorMsg.waitForDisplay();
		return lblErrorMsg.getText();
	}

	public LoginPage waitForLoginPageDisplay() {
		txtUsername.waitForDisplay();
		txtPassword.waitForDisplay();
		return new LoginPage();
	}
}
