package DataDriven;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ReadExcelFile;

public class readExcel {
	ChromeDriver driver;
	@Test(dataProvider="testData")
	public void login(String username, String password) throws InterruptedException {
		WebDriverManager.chromedriver().setup();		
		driver = new ChromeDriver();
		
		driver.get("http://newtours.demoaut.com/");
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElementByName("password").sendKeys(password);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='login']")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.getTitle().matches("Find a Flight: Mercury Tours:"), "User is not signed in");
		
	}

	@DataProvider(name="testData")
	public Object[][] TestDataFeed(){
		
		ReadExcelFile config = new ReadExcelFile("E:\\New-Eclipse-Workspace\\DataDrivenFramework\\DDF.xlsx");
		int rows = config.getRowCount(0);
		
		Object[][] credentials = new Object[rows][2];
		
		for(int i=0;i<rows;i++) {
			
			credentials[i][0] = config.getData(0, i , 0);
			credentials[i][1] = config.getData(0, i, 1);
		}
		return credentials;
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
			
	
}
