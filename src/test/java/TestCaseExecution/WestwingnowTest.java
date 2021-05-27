package TestCaseExecution;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import BaseClass.BrowserSetup;
import ExcelReader.readexcelsheet;
import PageObjects.WestwingnowPage;
import utility.WaitCommand;

public class WestwingnowTest extends readexcelsheet{
    WebDriver driver;
    WestwingnowPage wwp;
	WaitCommand wc;
	BrowserSetup BS;
	String filepath = System.getProperty("user.dir")+ "\\config\\file.properties";
	String excelfilepath = System.getProperty("user.dir")+ "\\TestData\\datafile.xlsx";
	File file;
	FileInputStream fileInput;
	Properties prop;
	public int DataSet=-1;
	public  String Res;
	public  String Fail;
	static String sheetname;
	readexcelsheet obj1;
   
	@BeforeMethod
	public void HomePageNavigation() throws Throwable {
			fileInput = new FileInputStream(filepath);
	        prop = new Properties();
			prop.load(fileInput);
		    driver = BrowserSetup.StartBrowser(prop.getProperty("browserName"),prop.getProperty("url"));
	}

	@Test(dataProvider="excelDP",priority = 0,enabled=true)
	public void LoginPageOne(String stremailAddress , String strpassword ,String strsearchdata, String Result) throws Exception {
      
		DataSet++;
		wwp = new WestwingnowPage(driver);
		wc=new WaitCommand(driver);
		wc.implicitlyWait();
		wwp.PopUp();
		wc.pause(1000);
		wwp.SearchData(strsearchdata);
		wc.pause(1000);
		wwp.SearchProductList(strsearchdata);
		wwp.VerifyProductPage(wwp.VerifyProductLinks());
		wc.pause(1000);
		wc.refresh();
		wc.pause(1000);
		wwp.WishListIcon();
		wc.pause(1000);
		wwp.LogInHere();
		wc.pause(1000);
		wwp.EmailAddress(stremailAddress);
		wc.pause(1000);
		wwp.Password(strpassword);
		wc.pause(1000);
		wwp.LoginButton();
		wc.pause(1000);
		wc.getScreenshot(driver, "product_added"); //Please refer Screenshots folder
		wc.pause(1000);
		wwp.WishListButton();
		wc.pause(1000);
		wwp.DeleteProduct();
		wc.pause(1000);
		wc.getScreenshot(driver, "product_deleted"); //Please refer Screenshots folder
		wc.pause(1000);
		//Result is equal to pass mentioned here
		Res="Passed Successfully";
		obj1 =new readexcelsheet();
		obj1.WriteResult(excelfilepath,"DataSet", Res, DataSet+1);
}
	
	@DataProvider
	public Object[][] excelDP() throws IOException {
		Object[][] arrObj = getExcelData(excelfilepath,"DataSet");
		return arrObj;
	}
	
	@AfterMethod
	public void getResult() throws Exception {
		BS= new BrowserSetup();
		BS.TearDown();
	}
}