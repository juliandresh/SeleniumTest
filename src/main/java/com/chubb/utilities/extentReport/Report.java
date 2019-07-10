package com.chubb.utilities.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.chubb.config.ConfigJSON;
import com.chubb.config.ConfigurationFile;
import com.chubb.utilities.html.HtmlFile;
import com.chubb.utilities.screenshot.ScreenShot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Report {
    private WebDriver webDriver;
    private ExtentHtmlReporter extentHtmlReporter;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private ConfigJSON json;
    private ConfigurationFile configFile;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ExtentHtmlReporter getExtentHtmlReporter() {
        return extentHtmlReporter;
    }

    public void setExtentHtmlReporter(ExtentHtmlReporter extentHtmlReporter) {
        this.extentHtmlReporter = extentHtmlReporter;
    }

    public ExtentReports getExtentReports() {
        return extentReports;
    }

    public void setExtentReports(ExtentReports extentReports) {
        this.extentReports = extentReports;
    }

    public ExtentTest getExtentTest() {
        return extentTest;
    }

    public void setExtentTest(ExtentTest extentTest) {
        this.extentTest = extentTest;
    }

    public ConfigJSON getJson() {
        return json;
    }

    public void setJson(ConfigJSON json) {
        this.json = json;
    }

    public ConfigurationFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigurationFile configFile) {
        this.configFile = configFile;
    }
}
