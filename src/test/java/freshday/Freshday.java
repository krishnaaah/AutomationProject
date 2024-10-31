package freshday;

import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Freshday {
WebDriver driver;
String baseurl="https://freshdaykart.com/";

@BeforeTest
public void setup() {
	driver= new ChromeDriver();
}

@BeforeMethod
public void urlloading() {
	driver.get(baseurl);
	driver.manage().window().maximize();
}

@Test
public void test() throws IOException, Exception
{
//	title verification
	 String actualtitle = driver.getTitle();
	 System.out.println(actualtitle);
	 String exp = "Best Online Grocery Delivery in Cochin, Ernakulam at Big Offers and Discounts | Freshdaykart - Kochi";
	 if(actualtitle.equals(exp)) {
		 System.out.println("pass");
	 }
	 else {
		 System.out.println("fail");
	 }
	 
//  logo verification
	 WebElement logo=driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[1]/a/img"));
		Boolean logodisplay=logo.isDisplayed();
		if(logodisplay)
		{
			System.out.println("Logo is displayed");
		}
		else
		{
			System.out.println("failed");
		}
	 
// login
		
		driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[4]/ul/li[2]/p[1]/a")).click();
		File f = new File("C:\\Users\\KRISHNA KB\\Documents\\datadriven.xlsx");
		FileInputStream fi= new FileInputStream(f);
		XSSFWorkbook wb = new XSSFWorkbook(fi);
		XSSFSheet sh= wb.getSheet("Sheet1");
		System.out.println(sh.getLastRowNum());
		
		for (int i=1;i<=sh.getLastRowNum();i++)
		{
			String Email= sh.getRow(i).getCell(0).getStringCellValue();
			System.out.println("Email="+Email);
			String Password=sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println("Password="+Password);
			
			driver.findElement(By.xpath("/html/body/div[2]/div/form[1]/div[1]/input")).clear();
			driver.findElement(By.xpath("/html/body/div[2]/div/form[1]/div[1]/input")).sendKeys(Email);
			driver.findElement(By.xpath("/html/body/div[2]/div/form[1]/div[2]/input")).clear();
			driver.findElement(By.xpath("/html/body/div[2]/div/form[1]/div[2]/input")).sendKeys(Password);
		}
			driver.findElement(By.xpath("/html/body/div[2]/div/form[1]/button")).click();
			//driver.navigate().back();
			
//hover and select from drop down
 
	 WebElement fruitsdropdown=driver.findElement(By.xpath("/html/body/header/div[3]/div[2]/div/nav/div[2]/ul/li[2]/a"));
	  WebElement fruitstab=driver.findElement(By.xpath("/html/body/header/div[3]/div[2]/div/nav/div[2]/ul/li[2]/ul/li[2]/a"));
	  Actions action = new Actions(driver);
	  action.moveToElement(fruitsdropdown).moveToElement(fruitstab).click().perform();
	  
	  Thread.sleep(2000);
	  
//	 get product name 
	 // String ProductName=driver.findElement(By.xpath("")).getText();
	 // System.out.println(ProductName);
	  driver.findElement(By.name("search_text")).sendKeys("cabbage");
	  driver.findElement(By.name("Search")).click();
 driver.findElement(By.xpath("//*[@id=\"srt_ajax_id\"]/div/div/div[1]/a[1]")).click();
 
// buy product
 driver.findElement(By.xpath("//*[@id=\"plu-2527\"]/span")).click();
 driver.findElement(By.xpath("//*[@id=\"ads-2527\"]/button")).click();
 driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[4]/ul/li[1]/a[2]")).click();
 driver.findElement(By.xpath("//*[@id=\"payd\"]/div/div[3]/a")).click();
 
 
 //-----add address------
 
 
/* driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[1]/a")).click();
 driver.findElement(By.xpath("//*[@id=\"new_ad\"]/div/form/div[1]/input")).sendKeys("Sea view Apartments");
 WebElement countrydropdown = driver.findElement(By.xpath("//*[@id=\"new_ad\"]/div/form/div[2]/select"));
 Select oSelect=new Select(countrydropdown);
 oSelect.selectByVisibleText("India");
 WebElement statedropdown  = driver.findElement(By.xpath("//*[@id=\"state_div01\"]/select"));
 Select stateselect=new Select(statedropdown);
 stateselect.selectByVisibleText("Kerala");
 WebElement localdropdown  = driver.findElement(By.xpath("//*[@id=\"loc_div01\"]/select"));
 Select locstate=new Select(localdropdown);
 locstate.selectByVisibleText("Edappally");
 driver.findElement(By.xpath("//*[@id=\"new_ad\"]/div/form/div[5]/input")).sendKeys("Edappally");
 driver.findElement(By.xpath("//*[@id=\"new_ad\"]/div/form/div[6]/input")).sendKeys("685364");
 driver.findElement(By.xpath("//*[@id=\"new_ad\"]/div/form/div[7]/input")).sendKeys("Near lulu mall");*/
 driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[3]/div/div[1]/p[1]/input")).click();
 WebElement radioElement = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/div[3]/div/div[1]/p[1]/input"));
 boolean selectState = radioElement.isEnabled();
 		
 //performing click operation only if element is not selected-----
 if(selectState == false) {
 	radioElement.click();
 }
  
 
 //-----alert handling---------
 driver.findElement(By.xpath("//*[@id=\"MakeDefault\"]")).click();
 Alert alert = driver.switchTo().alert();
 String alertMessage= driver.switchTo().alert().getText();
 System.out.println("alert text:"+alertMessage);	
 Thread.sleep(5000);
 alert.accept();	
 
 //------screenshot taking----
 File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//Now you can do whatever you need to do with it, for example copy somewhere
 
	org.openqa.selenium.io.FileHandler.copy(scrFile, new File("./screenshots//ss1.png"));
  
	//-----returning home and scroll down---
	driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[1]/a/img")).click();
	 
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	 
	 
	 //----multiple window handling-----
	 //driver.findElement(By.xpath("/html/body/div[3]/div[5]/div[2]/div/a/img")).click();
	 
	/* String parentWindow=driver.getWindowHandle();
		driver.findElement(By.xpath("")).click();
		
		Set<String> allwindowhandle = driver.getWindowHandles();
		for(String a:allwindowhandle) {
			if(!a.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(a);
				
				driver.close();
			}
			driver.switchTo().window(parentWindow);
		}*/
	 
	// Get the window handle of the parent window
	 String parentWindowHandle = driver.getWindowHandle();

	 // Click on a link that opens a new window
	 driver.findElement(By.xpath("/html/body/div[3]/div[5]/div[2]/div/a/img")).click();

	 // Get the window handles of all the windows that are currently open
	 Set<String> allWindowHandles = driver.getWindowHandles();

	 // Loop through the window handles to find the handle of the new window
	 for (String windowHandle : allWindowHandles) {
	     if (!windowHandle.equals(parentWindowHandle)) {
	         driver.switchTo().window(windowHandle);
	         break;
	     }
	 }

	 // Now we are in the child window, do some actions
 driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[2]/div/form/input")).sendKeys("milk");
 driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[2]/div/form/button")).click();
 Thread.sleep(2000);
	 // ...
	  driver.close();

	 // Switch back to the parent window
	 driver.switchTo().window(parentWindowHandle);
	 
	 //-----scroll up------
	 JavascriptExecutor jss = (JavascriptExecutor) driver;
	 jss.executeScript("window.scrollTo(40,0)");
	 
	 
	 //------logout------
	 driver.findElement(By.xpath("/html/body/header/div[3]/div[1]/div[4]/ul/li[2]/p[1]/a")).click();
	 driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[2]/div/p[5]/a")).click();

	 
}
@AfterTest
public void closebrowser() {
	driver.close();
}

}
