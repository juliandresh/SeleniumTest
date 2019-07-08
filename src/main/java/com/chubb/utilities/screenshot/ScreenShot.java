package com.chubb.utilities.screenshot;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.chubb.config.ConfigurationFile;


public class ScreenShot {
	
	public static String getScreenShot(ConfigurationFile configFile, WebDriver webDriver, String screenShotName) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot)webDriver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = configFile.getImageLocation() + "//" + screenShotName + dateName + ".png";

		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}
