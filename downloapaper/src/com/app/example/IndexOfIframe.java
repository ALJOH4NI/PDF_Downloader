package com.app.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
/***
This class is for finding the number of frame in HTML if need.
just add the URL for the page that you are trying to download from.

the result of this code in console will be the index number of the frame (0,1,2,...).

***/
public class IndexOfIframe {
public static void main(String[] args) {
		String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\geckodriver.exe";

		System.setProperty("webdriver.gecko.driver",pathClone);
	    WebDriver driver = new FirefoxDriver();
	    // add the URL 
	    driver.get("http://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber=8141933");  
	    driver.manage().window().maximize();
	    
	    int size = driver.findElements(By.tagName("iframe")).size();

	    for(int i=0; i<=size; i++){
		driver.switchTo().frame(i);
		int total=driver.findElements(By.xpath("html/body/a/img")).size();
		System.out.println(total);
	    driver.switchTo().defaultContent();}}}
