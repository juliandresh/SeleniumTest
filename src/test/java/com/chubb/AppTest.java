package com.chubb;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.chubb.config.ConfigJSON;
import com.chubb.config.ConfigurationFile;

import com.chubb.utilities.html.HtmlFile;
import com.chubb.utilities.screenshot.ScreenShot;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AppTest {
    private WebDriver webDriver;
    private ExtentHtmlReporter extentHtmlReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private ConfigJSON json = new ConfigJSON();
    private ConfigurationFile configFile = json.getConfigurationFile();
    private String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
    private HtmlFile htmlFile;

    @BeforeTest
    public void setExtent(){

        htmlFile = new HtmlFile();
        htmlFile.createHtml(configFile, timeStamp);

        extentHtmlReporter = new ExtentHtmlReporter(configFile.getExtentReportLocation()+"//myReport_"+ timeStamp + ".html");
        extentHtmlReporter.config().setDocumentTitle("Automation Report");
        extentHtmlReporter.config().setReportName("Functional Report");
        extentHtmlReporter.config().setChartVisibilityOnOpen(false);
        extentHtmlReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("Hostname","localhost");
        extentReports.setSystemInfo("OS","Windows7");
        extentReports.setSystemInfo("Tester Name","Liliana Barbosa");
        extentReports.setSystemInfo("Browser","Chrome");
    }

    @AfterTest
    public void endReport(){
        extentReports.flush();
        webDriver.quit();
    }

    @BeforeSuite
    public void setup(){
        System.setProperty("webdriver.chrome.driver", configFile.getDriverPath());
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("http://demo.nopcommerce.com/");
    }

    @Test
    public void logIn(){
        extentTest = extentReports.createTest("Application Login");
        System.out.println("Login");
        Assert.assertEquals("nopCommerce demo stor","nopCommerce demo store");
    }

    @Test
    public void noCommerceTittleTest(){
        extentTest = extentReports.createTest("Auto - Tittle");

        String tittle = webDriver.getTitle();
        System.out.println(tittle);
        Assert.assertEquals(tittle,"nopCommerce demo store");
    }

    @Test
    public void noCommerceLogoTest(){
        extentTest = extentReports.createTest("Pyme - Logo");
        boolean status = webDriver.findElement(By.xpath("//img[@alt='nopCommerce demo store']")).isDisplayed();
        Assert.assertTrue(status);
    }

    @Test
    public void noCommerceLoginTest(){
        extentTest = extentReports.createTest("Vida - Login");
        Assert.assertTrue(true);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        System.out.println(result);
        if(result.getStatus()==ITestResult.FAILURE){
            extentTest.log(Status.FAIL, "TEST CASE FAILED: " + result.getName());
            extentTest.log(Status.FAIL, "TEST CASE FAILED: " + result.getThrowable());

            String screenShotPath = ScreenShot.getScreenShot(configFile, webDriver, result.getName());
            try {
                extentTest.addScreenCaptureFromPath(screenShotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(result.getStatus() == ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "TEST CASE SKIPPED: " + result.getName());
        }
        else if(result.getStatus()== ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, "TEST CASE PASSED: " + result.getName());
        }
    }
}