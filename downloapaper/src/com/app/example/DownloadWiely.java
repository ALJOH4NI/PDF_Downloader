package com.app.example;

/*
 * This class will download paper and save it to download folder
 * This code has been customized to download paper from Wiely online library
 * 
 * The list of URLs papers must be copied in 'papers.txt' file in 'lib' folder
 */
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


 
public class DownloadWiely {
	static String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\geckodriver.exe";
	public static void main(String[] args) {
		new DownloadWiely().start();
	}
	
	void start(){
		// TODO Auto-generated method stub
		int i=0;
		ArrayList<String> listOfpapersLinks =   getPaperLinks("C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\papers.txt");
		for (String paperLink : listOfpapersLinks) {
			
			System.out.println("start downlaod :"+paperLink);
			try{
				// TODO Auto-generated method stub
				System.setProperty("webdriver.gecko.driver",pathClone);				

				WebDriver driver = new FirefoxDriver();
				 			
				driver.get(paperLink);
				
				String papaerURL=driver.findElement(By.xpath("//a[@class='article-support__item-link js-infopane-epdf']")).getAttribute("href").toString();
				System.out.println("**************");
				System.out.println(papaerURL);
				driver.close();
				WebDriver driver1 = new FirefoxDriver();
				driver1.get(papaerURL);
				driver1.switchTo().frame(0);
				driver1.findElement(By.xpath("//a[@class='toolbar-btn ng-scope md-ink-ripple save']")).click();
				// for handling window save file that will pop out 
				Robot robotObject = new Robot();
				Thread.sleep(3000);
				robotObject.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				//closing the second windows 
				driver1.close();
			
			i ++;
			System.out.println("paper number "+ i + " has downloaded");				
			}catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(paperLink);
			}	
		}
		System.out.println("Downloading of all papers has been completed.");
	}
	

 	// Reading text file
	public ArrayList<String> getPaperLinks(String appsLinks){
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
				// TODO Auto-generated catch block
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