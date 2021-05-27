package BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.WaitCommand;

public class BrowserSetup {
	static WebDriver driver;
	static WaitCommand WC;

	public static WebDriver StartBrowser(String browsername, String url) throws InterruptedException {
		if (browsername.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browsername.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if (browsername.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		WC = new WaitCommand(driver);
		WC.pause(100);
		driver.manage().deleteAllCookies();
		WC.pause(100);
		driver.get(url);
		WC.pause(1000);
		return driver;
	}


	public void TearDown() throws Exception {
		try {
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
