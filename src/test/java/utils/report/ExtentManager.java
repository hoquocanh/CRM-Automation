package utils.report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter(String outputResult) throws Exception {
		if (extent == null) {
			if (outputResult == null || outputResult.trim().length() == 0)
				throw new Exception("Output folder result is required");

			checkTestOutputDirectory(outputResult);

			ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(outputResult);
			htmlReport.config().setTheme(Theme.STANDARD);
			extent = new ExtentReports();
			extent.attachReporter(htmlReport);
		}
		return extent;
	}

	public synchronized static ExtentReports getReporter() throws Exception {
		try {
			return getReporter("");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static boolean isReportCreated() {
		return (extent != null) ? true : false;
	}

	private static void checkTestOutputDirectory(String outputResult) {
		String[] arrPath = outputResult.split("\\" + System.getProperty("file.separator"));
		String tmpDir = System.getProperty("user.dir");
		File tmpFile;

		for (int i = 3; i > 1; i--) {
			tmpDir += System.getProperty("file.separator") + arrPath[arrPath.length - i];
			tmpFile = new File(tmpDir);
			if (!tmpFile.exists())
				tmpFile.mkdirs();
		}
	}
}
