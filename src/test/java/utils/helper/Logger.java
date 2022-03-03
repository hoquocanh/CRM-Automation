package utils.helper;

import utils.report.ExtentTestManager;

public class Logger {

	public static void info(String message) {
		ExtentTestManager.addStepLog(message);
	}
}
