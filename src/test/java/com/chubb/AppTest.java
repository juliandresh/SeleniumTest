package com.chubb;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import com.chubb.config.ConfigJSON;
import com.chubb.config.ConfigurationFile;

import com.chubb.seleniumTest.wis.WisApplication;
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
    WisApplication wisApp;
    private ExtentHtmlReporter extentHtmlReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private ConfigJSON json;
    private ConfigurationFile configFile;

    private HtmlFile htmlFile;

    @BeforeTest
    public void setExtent(){

        String timeStamp = new SimpleDateFormat(configFile.getDateFormat()).format(new Date());
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

    @BeforeSuite
    public void setup(){
        json = new ConfigJSON();
        configFile = json.getConfigurationFile();

        wisApp = new WisApplication();
        wisApp.setConfigFile(configFile);
        webDriver = wisApp.startWebDriver();

        wisApp.setWebDriver(webDriver);
    }

    @Test(priority = 0)
    public void loadWebPage(){
        extentTest = extentReports.createTest("Load Web Page");
        Assert.assertEquals("WIS Web Insurance Sale.", wisApp.loadWebPage());
    }

    @Test(priority = 1)
    public void logIn(){
        extentTest = extentReports.createTest("Application Login");
        Assert.assertEquals("COTIZACIÓNA",wisApp.getLogin());
    }

    @Test(priority = 2)
    public void crearCotizacion(){
        extentTest = extentReports.createTest("Crear Cotización");
        Assert.assertTrue(true);
    }

    @Test(priority = 3)
    public void consultarCotizacion(){
        extentTest = extentReports.createTest("Consultar Cotización");
        Assert.assertEquals("0379136",wisApp.getConsultarCotizacion());
    }

    @Test(priority = 4)
    public void expedirCotizacion(){
        extentTest = extentReports.createTest("Expedir Cotización");
        Assert.assertTrue(false);
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

    @AfterTest
    public void endReport(){
        extentReports.flush();
        wisApp.closeWebDriver();
    }
}