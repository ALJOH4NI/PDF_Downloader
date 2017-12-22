# PDF_Downloader
This is a java tool that takes URL from IEEE, ACM, etc. to download research papers PDF.
The tool uses selenium driver to download pdf from digital libraries.

# Per-requisite
1. install selenium driver. 
2. Firefox browser.

# About the PDF_Downloader:
The tool has 5 classes that run independently of other, where each class in the project has been designed to download pdfs from digital libraries(IEEE, ACM, etc.) and save the pdf to download folder of your system. The tool read a file 'papers.txt' located in 'lib' folder inside the project. Where this file can have a list URL provided by you, for a specific library that you want to download from it.



Before running the code you have to configure selenium driver to your system. The driver located in 'lib' folder within the project. you just need to change the path of the selenium in all the classes of the project to your machine environment.
```
public class DownloadACM {
	// path of selenium driver 
	static String pathClone="C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\geckodriver.exe";
```

Also, you need to change the path of 'paper.txt' file in the code. 
```
	void start(){
  // getting papers URL from txt file
	ArrayList<String> listOfPapersURL =   getPapersLinks("C:\\Users\\falko\\eclipse-workspace\\downloapaper\\lib\\papers.txt");

```


# Notes
The tool only works on Windows environment but it can works on any system by installing the appropriate selenium driver to that system.
