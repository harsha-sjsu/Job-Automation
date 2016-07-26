package com.applicationbot.scraper;

import com.thoughtworks.selenium.Selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;







import java.util.regex.Pattern;


@SuppressWarnings("deprecation")
public class EmailRetriever {
	public static String getEmailAddressFromCLURL(String baseUrl) throws InterruptedException {
		
		
		Selenium selenium;
		WebDriver driver = new ChromeDriver();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
		
		selenium.open(baseUrl);
		
		Thread.sleep(15*1000);
		
		if (!selenium.isElementPresent("css=button.reply_button.js-only")) {
			selenium.stop();
			return "";
		}
		
		Thread.sleep(15*1000);
		
		
		selenium.click("css=button.reply_button.js-only");
		for (int second = 0;; second++) {
			if (second >= 60) 
				System.out.println("timeout");
			try { 
				if (selenium.isElementPresent("//div[@id='webmailinks']/b[2]")) {
					break; 
				}
				} 
			catch (Exception e) {
				e.printStackTrace();
				}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (!selenium.isElementPresent("css=div.anonemail")) {
			selenium.stop();
			return "";
		}
		
		String emailAddress ="";
		try {
		selenium.getText("css=div.anonemail");
		System.out.println(emailAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		selenium.stop();
		return emailAddress;
	}
	
}
