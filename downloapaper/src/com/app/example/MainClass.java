package com.app.example;
/*
 * This class for downloading papers from IEEE library
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

 
public class MainClass {
	//static String pathClone=Paths.get(".").toAbsolutePath().normalize().toString() +"/Clone/";
	static String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\geckodriver.exe";
	public static void main(String[] args) {
		new MainClass().start();
	}
	
	void start(){
		
		int i=0;
		ArrayList<String> listOfGitRepositories =   getGitLinks("C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\papers.txt");
		for (String paperLink : listOfGitRepositories) {

			System.out.println("start downlaod :"+paperLink);

			try{		
				System.setProperty("webdriver.gecko.driver",pathClone);				
			
				System.out.println("**************");

				WebDriver driver1 = new FirefoxDriver();
				driver1.get(paperLink);
				
				// ieee library do not touch this code 
				 driver1.switchTo().frame(1);
				 driver1.findElement(By.id("download")).click();
				// end of ieee code
				
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
			if (i==40) {
				System.out.println("I'm inside the thread after 40 downloads");
				Thread.sleep(300000);
				i=0;
			}
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(paperLink);
			}	
		}
		System.out.println("Downloading of all papers has been completed.");
	}
	
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