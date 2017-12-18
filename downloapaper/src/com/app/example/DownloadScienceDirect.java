package com.app.example;
/*NOTE: run this class as java application to download papers from Springer
 * 
 * 
 * This class is design to download pdf papers for Springer digital library 
 * you just need to add the list of URLS of the papers that you wants to download from Springer
 */
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.xml.utils.URI;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.interactions.Actions;

 
public class DownloadScienceDirect {
	// path of selenium driver 
	static String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\geckodriver.exe";
	public static void main(String[] args) {
		new DownloadScienceDirect().start();
	}
	
	void start(){
		// getting papers URL from txt file
		ArrayList<String> listOfPapersURL =   getPapersLinks("C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\papers.txt");
		for (String paperLink : listOfPapersURL) {
			
			System.out.println("start downlaod :"+paperLink);
			
			try{
				// Setting selenium to read the text file
				System.setProperty("webdriver.gecko.driver",pathClone);							
				System.out.println("**************");
				WebDriver driver = new FirefoxDriver();
				driver.get(paperLink);
				driver.findElement(By.id("pdfLink")).click();
				String papaerURL=driver.findElement(By.xpath("//a[@aria-label='Download single PDF. Opens in a new window.']")).getAttribute("href").toString();
				driver.close();
				WebDriver driver1 = new FirefoxDriver();
				driver1.get(papaerURL);
				Thread.sleep(3000);
				driver1.findElement(By.xpath("//a[@title='Download']")).click();
				
				// Handling pop up window to save the file
				Robot robotObject = new Robot();
				Thread.sleep(3000);
				robotObject.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				//closing the second windows 
				driver1.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(paperLink);
			}	
		}
		System.out.println("Downloading of all papers has been completed.");
	}
	
 	// reading text file
	public ArrayList<String> getPapersLinks(String appsLinks){
		ArrayList<String> listOfPapersURL =  new ArrayList<String>();
		try {
			FileInputStream fstream;

				fstream = new FileInputStream(appsLinks);
		
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			//Read File Line By Line
			try {
				while ((strLine = br.readLine()) != null)   {
					listOfPapersURL.add(strLine);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			//Close the input stream
			br.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return listOfPapersURL;
	}
	
}