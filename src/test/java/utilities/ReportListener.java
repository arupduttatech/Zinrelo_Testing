package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportListener implements ITestListener {
	protected static WebDriver driver;
	protected static ExtentReports reports;
	protected static ExtentTest test;
	ConfigProperties prop = new ConfigProperties();

	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + "test is started");
		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName() + "test is started");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("PASSED: " + result.getMethod().getMethodName());
		test.log(LogStatus.PASS, result.getMethod().getMethodName() + "test is passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED: " + result.getMethod().getMethodName());
		test.log(LogStatus.FAIL, result.getMethod().getMethodName() + "test is failed");

	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("SKIPPED: " + result.getMethod().getMethodName());
		test.log(LogStatus.SKIP, result.getMethod().getMethodName() + "test is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("on test sucess within percentage");
	}

	public void onStart(ITestContext context) {
		reports = new ExtentReports("reports/extent-report.html", true);
	}

	public void onFinish(ITestContext context) {
		System.out.println("on finish");
		reports.endTest(test);
		reports.flush();
	}
}
