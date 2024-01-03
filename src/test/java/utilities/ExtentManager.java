package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance(String reportPath) {
        if (extent == null) {
            extent = new ExtentReports();

            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportPath);
            htmlReporter.config().setDocumentTitle("Jira Automated Test Report");
            htmlReporter.config().setReportName("Jira Automated Test Results");
            htmlReporter.config().setTheme(Theme.DARK);

            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        return extent.createTest(testName);
    }

    public static void addTestToReport(ExtentReports extent, ExtentTest test) {
        extent.flush();
    }
}
