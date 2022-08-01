package utils.helper;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.groovy.control.customizers.SecureASTCustomizer.ExpressionChecker;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import org.apache.log4j.LogManager;
import cucumber.api.Scenario;

import utils.report.ExtentTestManager;


public class Logger {

//	public static void info(String message) {
//		ExtentTestManager.addStepLog(message);
//	}
	private static String methodName;
	private static String clasName;
	private static List<String> currentLogs = new ArrayList<>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);
	
	public static Scenario scenario;
	public static void info(String message) {
		//info(message,null);
		
		try {
			info(message,null);
		}
		catch (NullPointerException ex) 
		{
			System.out.println(ex);
		}
		
	}

	public static void info(String message, String extentNodeKey) {
		Reporter.log("<b>INFO: </b>" + message);
		saveLog(message);
		log.info(message);

		if (extentNodeKey == null || extentNodeKey.isEmpty()) {
			if (ExtentTestManager.getNode() != null)
				ExtentTestManager.getNode().info(MarkupHelper
						.createLabel("<span style=word-break:keep-all>" + message + "</span>", ExtentColor.BLUE));
			else
				ExtentTestManager.getTest().log(Status.INFO,
						"<span style=word-break:break-word>" + message + "</span>");
		} else
			ExtentTestManager.getNode(extentNodeKey).info(MarkupHelper
					.createLabel("<span style=word-break:keep-all>" + message + "</span>", ExtentColor.BLUE));
	}

	public static void bug(String bugId, String bugLink) {
		String bugInfo = String.format("The bug %s is added", bugId);
		String msg = "<a href='" + bugLink + "'>" + bugInfo + "</a>";
		Reporter.log(msg);
		log.error(msg);
		saveLog(msg);
		if (ExtentTestManager.getNode() != null)
			ExtentTestManager.getNode().warning(MarkupHelper.createLabel(msg, ExtentColor.ORANGE));
		else
			ExtentTestManager.getTest().log(Status.WARNING, msg);
	}

	public static void error(String message) {
		error(message, null);
	}

	public static void error(String message, String extentNodeKey) {
		Reporter.log(message);
		log.info("ERROR: " + message);
		saveLog("ERROR: " + message);
		message = "<a><i><b>"
				// + " style=\"color: #FF0000;\""
				+ "ERROR: </b></i>" + message + "</a>";

		if (extentNodeKey == null || extentNodeKey.isEmpty())
			ExtentTestManager.getNode().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
		else
			ExtentTestManager.getNode(extentNodeKey).fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	public static void verify(String message) {
		try {
		Reporter.log(message);
		log.info("VERIFY POINT: " + message);
		saveLog("VERIFY POINT: " + message);
		message = "<i><b >VERIFY POINT: </b></i>" + message;
		if (ExtentTestManager.getNode() != null)
			ExtentTestManager.getNode().info(MarkupHelper.createLabel("<b> " + message + "</b>", ExtentColor.BLUE));
		else
			ExtentTestManager.getTest().log(Status.INFO, message);
		}
		catch (NullPointerException ex) 
		{
			System.out.println(ex);
		}
	}

	public static void passed(String message) {
		passed(message, null);
	}

	public static void passed(String message, String extentNodeKey) {
		Reporter.log(message);
		log.info("PASSED: " + message);
		saveLog("PASSED: " + message);
		message = "<i><b"
				// + " style=\"color: #32cd32;\""
				+ ">PASSED: </b></i>" + message;

		if (extentNodeKey == null || extentNodeKey.isEmpty())
			ExtentTestManager.getNode().fail(MarkupHelper.createLabel(message, ExtentColor.GREEN));
		else
			ExtentTestManager.getNode(extentNodeKey).fail(MarkupHelper.createLabel(message, ExtentColor.GREEN));

	}

	public static void failed(String message) {
		failed(message, null);
	}

	public static void failed(String message, String extentNodeKey) {
		Reporter.log(message);
		log.info("FAILED: " + message);
		saveLog("FAILED: " + message);
		message = "<i><b"
				// + " style=\"color: #FF0000;\""
				+ ">FAILED: </b></i>" + message;

		if (extentNodeKey == null || extentNodeKey.isEmpty())
			ExtentTestManager.getNode().fail(MarkupHelper.createLabel(message, ExtentColor.RED));
		else
			ExtentTestManager.getNode(extentNodeKey).fail(MarkupHelper.createLabel(message, ExtentColor.RED));
	}

	private static void saveLog(String message) {
		String currentMethod = Thread.currentThread().getStackTrace()[3].getMethodName();
		String currentClass = Thread.currentThread().getStackTrace()[3].getClassName();
		if (!currentMethod.equals(methodName) || (currentMethod.equals(methodName) && !currentClass.equals(clasName))) {
			currentLogs.clear();
		}
		methodName = currentMethod;
		clasName = currentClass;
		currentLogs.add(message);
	}

	public static List<String> getCurrentLogs() {
		return currentLogs;
	}
}
