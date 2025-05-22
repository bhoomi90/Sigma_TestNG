package extentReports;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.qameta.allure.Allure;
import webdriver.DriverFactory;

	public class ExtentReportListeners implements ITestListener {
	    private static ExtentReports extent;
	    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();

	    @Override
	    public void onStart(ITestContext context) {
	        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        extent.setSystemInfo("OS", System.getProperty("os.name"));
	        extent.setSystemInfo("Tester", "Vahini");
	    }

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
	        testThread.set(test);
	        Allure.step("Starting test: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestSuccess(ITestResult result) {
	        testThread.get().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
	        Allure.step("Test Passed: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onTestFailure(ITestResult result) {
	        ExtentTest test = testThread.get();
	        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
	        test.log(Status.FAIL, result.getThrowable());

	        WebDriver driver = DriverFactory.getdriver();  // Fetch WebDriver instance

	        if (driver != null) {
	            String screenshotPath = captureScreenshotOnFailure(driver, result.getMethod().getMethodName());
	            if (screenshotPath != null) {
	                test.addScreenCaptureFromPath(screenshotPath);  // Attach screenshot to Extent Report

	                // Attach screenshot to Allure Report
	                try {
	                    File screenshotFile = new File(screenshotPath);
	                    Allure.addAttachment("Screenshot on Failure", "image/png", 
	                            new ByteArrayInputStream(FileUtils.readFileToByteArray(screenshotFile)), "png");
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        testThread.get().log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }

	    // Method to capture screenshot only on failure
	    private String captureScreenshotOnFailure(WebDriver driver, String testName) {
	        if (driver instanceof TakesScreenshot) {
	            TakesScreenshot ts = (TakesScreenshot) driver;
	            File src = ts.getScreenshotAs(OutputType.FILE);

	            // Define the directory and timestamp
	            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		        String screenshotPath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + "_" + timestamp + ".png";

	            // Ensure the directory exists
	            File directory = new File(System.getProperty("user.dir") + "/reports/screenshots/");

	            if (!directory.exists()) {
	                directory.mkdirs();
	            }

	            try {
	                FileUtils.copyFile(src, new File(screenshotPath));
	                return screenshotPath;
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	}
