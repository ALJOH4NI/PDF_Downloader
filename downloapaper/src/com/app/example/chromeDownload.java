package com.app.example;




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
import org.openqa.selenium.firefox.internal.ProfilesIni;


public class chromeDownload {
	//static String pathClone=Paths.get(".").toAbsolutePath().normalize().toString() +"/Clone/";
	static String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\chromedriver.exe";
	public static void main(String[] args) {
		new chromeDownload().start();
	}
	
	void start(){
		// TODO Auto-generated method stub
		int i=0;
		ArrayList<String> listOfGitRepositories =   getGitLinks("C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\papers.txt");
		for (String paperLink : listOfGitRepositories) {
			//String[] sp= paper.split("=");
			//String paperLink= "http://ieeexplore.ieee.org/stamp/stamp.jsp?arnumber="+sp[1];
			System.out.println("start downlaod :"+paperLink);
			////
			try{
				// close brouwser
				 killAllfirefox();
				// TODO Auto-generated method stub
				System.setProperty("webdriver.chrome.driver",pathClone);				
//				
//				running firefox browser
//				
//				WebDriver driver =  new ChromeDriver(options);

				//WebDriver driver = new FirefoxDriver();
				 			
				//driver = new FirefoxDriver(profile)
				//driver.get(profile);
				//driver.get(paperLink);
				//driver.findElement(By.xpath("div[@class='download-article test-pdf-link']")).click();
				//String papaerURL=driver.findElement(By.xpath("//a[@title='Download this book in PDF format']")).getAttribute("href").toString();
				//String papaerURL=driver.findElement(By.xpath("//a[@id='pdfLink']")).getAttribute("href").toString();
				//driver.findElement(By.id("pdfLink")).click();
				//String papaerURL=driver.findElement(By.xpath("//a[@aria-label='Download single PDF. Opens in a new window.']")).getAttribute("href").toString();
				System.out.println("**************");
				//System.out.println(papaerURL);
				///killAllfirefox();
				//driver.close();
				WebDriver driver1 = new ChromeDriver();
				driver1.get(paperLink);
				//Thread.sleep(30000);
				//driver1.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
				//boolean a;
				
				//driver1.findElement(By.xpath("//a[@aria-label='Download']")).click();
				//System.out.println(a);
				//driver1.findElement(By.xpath("//a[@id='download']")).click();
				//driver1.findElement(By.cssSelector("button[class=toolbarButton download hiddenMediumView]")).click();
				//driver1.get(papaerURL);
				//Thread.sleep(2000);
				WebDriverWait wait = new WebDriverWait(driver1, 20);
				WebElement el = wait.until(ExpectedConditions.elementToBeClickable(By.id("download")));
				((JavascriptExecutor)driver1).executeScript("arguments[0].click()", el); 
//				WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("download")));
//				element.click();
				//driver1.findElement(By.xpath("//a[@title='Download']")).click();
				// for handling window save file that will pop out 
				Robot robotObject = new Robot();
				Thread.sleep(3000);
				robotObject.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				//closing the second windows 
				driver1.close();
			//System.out.println("the brwoser is closed after downloading");
			
			i ++;
			System.out.println("paper number "+ i + " has downloaded");
//				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(paperLink);
			}	
		}
		System.out.println("Downloading of all papers has been completed.");
	}
	
	void killAllfirefox(){
		 
		  try{
			  
			    String cmdStr="taskkill /F /IM firefox.exe";
				Process p = Runtime.getRuntime().exec(new String[]{"bash","-c",cmdStr});
				p.waitFor();
				//System.err.println("\nComplete delete\n");

			}catch (Exception e) {
				// TODO: handle exception
			} 
	}

 	// return list of git repositories with user name and password
	public ArrayList<String> getGitLinks(String appsLinks){
		ArrayList<String> listOfGitRepositories =  new ArrayList<String>();
		try {
			FileInputStream fstream;

				fstream = new FileInputStream(appsLinks);
		
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			//Read File Line By Line
			try {
				while ((strLine = br.readLine()) != null)   {
				  listOfGitRepositories.add(strLine);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Close the input stream
			br.close();
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listOfGitRepositories;
	}


}