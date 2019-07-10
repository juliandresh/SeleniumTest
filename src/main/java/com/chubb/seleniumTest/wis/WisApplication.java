package com.chubb.seleniumTest.wis;

import com.chubb.config.ConfigurationFile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WisApplication
{
    WebDriver webDriver;
    ConfigurationFile configFile;

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public ConfigurationFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigurationFile configFile) {
        this.configFile = configFile;
    }

    public WebDriver startWebDriver() {
        System.setProperty(configFile.getWebdriver(), configFile.getDriverPath());
        WebDriver webDriver =  new ChromeDriver();
        webDriver.manage().window().maximize();

        return webDriver;
    }

    public void closeWebDriver() {
        webDriver.quit();
    }

    public String loadWebPage() {
        webDriver.get(configFile.getWeburl());
        return webDriver.getTitle();

    }

    public String getLogin() {
        WebElement usuario = webDriver.findElement(By.name("LoginWIS$UserName"));
        usuario.sendKeys(configFile.getUser());

        WebElement password = webDriver.findElement(By.name("LoginWIS$Password"));
        password.sendKeys(configFile.getPassword());

        WebElement bLogIn = webDriver.findElement(By.name("LoginWIS$LoginButton"));
        bLogIn.click();

        return  webDriver.findElement(By.id("lblCotTit")).getText();
    }

    public static void crearCotizacion(WebDriver driver) {
        // TODO Auto-generated method stub

    }

    public String getConsultarCotizacion() {
        WebElement bLogIn = webDriver.findElement(By.id("MenuPrincipal1_HLink2"));
        bLogIn.click();

        WebElement txtCotizacion = webDriver.findElement(By.name("txtProposta"));
        txtCotizacion.sendKeys("476257");

        WebElement buscar = webDriver.findElement(By.name("Button1"));
        buscar.click();

        WebElement cotizacion = webDriver.findElement(By.xpath("//a[contains(text(),'476257')]"));
        cotizacion.click();

        return webDriver.findElement(By.id("txtApolice")).getText();

    }

    private void getExpedirCotizacion(WebDriver driver) {

        // TODO Auto-generated method stub
        WebElement emitir1 = driver.findElement(By.id("MenuPrincipal1_tdRol3"));
        emitir1.click();

        WebElement emitir = driver.findElement(By.name("TXTCotizacion"));
        emitir.sendKeys("476593");

        WebElement buscarcot = driver.findElement(By.name("btnBuscar"));
        buscarcot.click();
    }
}
