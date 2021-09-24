package maven;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Test {
	
	
public static String getData(int row, int cell)throws IOException{
		
		File  location = new File("D:\\javacode\\Selenium\\maven\\Excel\\CityNames.xlsx");
		
		FileInputStream fis = new FileInputStream(location);
		
		Workbook  wbobj= new XSSFWorkbook(fis);
		
		Sheet  sheetobj= wbobj.getSheet("Sheet1");
		 
		Row ro = sheetobj.getRow(row);
		Cell col= ro.getCell(cell);
		
		System.out.println(col);
		
		String stringvalue = null;
		int flag = col.getCellType();
		
		if(flag ==1) {
			stringvalue=col.getStringCellValue();
		}else 
		{
			double num=col.getNumericCellValue();
			long lng=(long) num;
			stringvalue =String.valueOf(lng);
		}
		return stringvalue;	
	} 
	
public static void main(String[] args) throws IOException,InterruptedException,AWTException {
	//Chrome driver 
	System.setProperty("webdriver.chrome.driver", 
			"D:\\javacode\\Selenium\\maven\\driver\\chromedriver.exe");
	WebDriver  wDriver = new ChromeDriver();
	
	wDriver.get("	");
	
	//to print site title
	String var=wDriver.getTitle();
	System.out.println(var);
	
	//select city from drop down
	
	WebElement fclick= wDriver.findElement(By .xpath("//*[@id=\"super-container\"]/div[2]/header/div[1]/div/div/div/div[2]/div[1]/span"));
	Actions acObj =new Actions(wDriver);
	acObj.click(fclick).build().perform();
	
	//Select location 
	WebElement searchLocation =wDriver.findElement(By.xpath("//*[@id=\"modal-root\"]/div/div/div/div[1]/div/div/input"));
	searchLocation.sendKeys(getData(1,1));					 
	
	// cancel pop-up	
	WebElement sclick=wDriver.findElement(By.xpath("//*[@id=\"wzrk-confirm\"]"));
	Actions acObj1= new Actions(wDriver);
	acObj1.click(sclick).build().perform();
	
	//for keyboard key press		
	Robot robot=new Robot();	//parent class
	
	WebElement click1= wDriver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div/div/div/div[2]/div/div[1]/div[4]/a/div/div[3]/div[1]/div"));
	Actions acObj2= new Actions(wDriver);
	acObj2.click(click1).build().perform();
	
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	Thread.sleep(3000);
	
	// movie
	WebElement click2 =wDriver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/div[4]/div[2]/div[4]/div/div/div[2]/a[3]/div/div[2]/div/img"));
	Actions acObj3= new Actions(wDriver);
	acObj3.click(click2).build().perform();
	
	Thread.sleep(2000);
	
	//movie name
	System.out.println("\nYou click on Movie :");
	String mname= wDriver.findElement(By.xpath("//*[@id=\"super-container\"]/div[2]/section[1]/div/div/div[2]/h1")).getText();
	System.out.println(mname);
	
	Thread.sleep(3000);
	
	//about movie
	
	System.out.println("\nDescription of Movie:");
	String description = wDriver.findElement(By.xpath("//*[@id=\"component-1\"]/section/span/div/span")).getText();
	System.out.println(description);
	
	//create folder to display details
	File fileObj= new File("D:\\Book_My_Show__Project");
	boolean var1= fileObj.mkdir();
	System.out.println(var1);
	
	//create file in folder
	File fileObj2= new File("D:\\Book_My_Show__Project\\display_project.txt");
	boolean var2=fileObj2.createNewFile();
	System.out.println(var2);
	
	Thread.sleep(2000);
	
	
	//write output in text  file
	FileWriter fwObj= new FileWriter("D:\\\\Book_My_Show__Project\\\\display_project.txt",true);
	BufferedWriter bwObj= new BufferedWriter(fwObj);
	Set<String> setObj=new LinkedHashSet<String>();
	
	setObj.add(mname);	//movieobj
	setObj.add(description);	//aboutmovie obj
	bwObj.newLine();
	String cityname= (getData(1,1));
	bwObj.write(cityname);	
	bwObj.close();
}
}
