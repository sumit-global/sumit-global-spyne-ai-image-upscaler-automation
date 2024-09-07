package com.upscaler.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest test;

    public WebDriverWait wait;

    @BeforeSuite
    public void setupExtentReports() {
        // Initialize ExtentReports only once for the entire suite
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter("./Reports/SpyneTestReport.html");
            htmlReporter.config().setDocumentTitle("Automation Test Report");
            htmlReporter.config().setReportName("Spyne.ai Image Upscaler Test Report");
            htmlReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Host Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "Automation Tester");
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        // Parameterized Browser Setup
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }

            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--start-maximized");

            if (System.getProperty("headless", "false").equalsIgnoreCase("true")) {
                options.addArguments("--headless");
            }

            driver = new FirefoxDriver(options);
        } else {
            test.fail("Unsupported browser: " + browser);
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        // Log the results to Extent Report
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed: " + result.getName());
            test.log(Status.FAIL, "Reason: " + result.getThrowable());

            // Capture screenshot on failure
            String screenshotPath = captureScreenshot(result.getName());

            // Attach screenshot to report
            test.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed: " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped: " + result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDown() {
        // Flush the Extent Report
        if (extent != null) {
            extent.flush();
        }
    }

    // Method to capture screenshot on test failure
    public String captureScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "./Screenshots/" + screenshotName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            test.info("Error while capturing screenshot because of "+e.getMessage());
        }
        return destination;
    }
}
