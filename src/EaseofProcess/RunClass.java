package EaseofProcess;
	 
import java.io.IOException;
import java.sql.Savepoint;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

	 
	 public class RunClass extends HelperClass
	 {
	 public static void main(String[] args) throws Exception 
	 {
	 	  System.setProperty("webdriver.chrome.driver","C:\\Users\\ravisanb\\workspace\\Jira Audit\\driver\\chromedriver.exe");
	 	  ChromeDriver driver = new ChromeDriver();
//	        ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
	 	  changeLocation();
	 	  jiraLogin();
	 	 jiraCount();
	 	 jiraIndexing();
	 	mailCreatorPass();
	 	}
	 }