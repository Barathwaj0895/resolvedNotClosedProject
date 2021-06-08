package EaseofProcess;

import org.openqa.selenium.interactions.Actions;
import java.lang.String;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandleInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.prefs.Preferences;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HelperClass extends Constants{
	public static WebDriver driver;
	public static int e;
	static String Username, jId, jSummary, jToDo, jResolution;
	static ArrayList<String> dummList=new ArrayList<String>();
	static ArrayList<String> jiraId=new ArrayList<String>();
	static ArrayList<String> jiraSummary=new ArrayList<String>();
	static ArrayList<String> jiraToDo=new ArrayList<String>();
	static ArrayList<String> jiraResolution=new ArrayList<String>();
	static String link="//a[(@href='/browse/'+jiraId.get(j)+')]";
	
		public static WebDriver changeLocation() {
 		driver = new ChromeDriver();
 		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 		return driver;	
 			}
	
	 	 	 public static void jiraLogin() throws Exception
		 	 {
	 	 	 	driver.get(JIRA_FILTER_URL);
	 	 	 	Thread.sleep(3000);
	 	 	 	driver.findElement(By.className("idp")).click();
	 	 	 	Thread.sleep(2000);
	 			 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 	         String loginId;   
	 	         System.out.println("Please Enter the user Midway Username :: ");
	 	         loginId = br.readLine();  
	 	 	 	driver.findElement(By.id("user_name")).sendKeys(loginId);
	 	 	 	BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
	 	         String passWord;   
	 	         System.out.println("Please Enter the user Midway Password :: ");
	 	         passWord = br.readLine();  
	 	 	 	driver.findElement(By.id("password")).sendKeys(passWord);
	 	 	 	driver.findElement(By.id("verify_btn")).click();
	 	 	 	driver.manage().window().maximize();
	 	 	 	Thread.sleep(10000);
	 	 	 }
	 	 	 
	 	 	public static void jiraCount(){
	 	 		driver.get(JIRA_FILTER_URL);
		 		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 	 	List<WebElement> d = driver.findElements(By.xpath("//a[@class='hidden-link issue-link']"));
			System.out.println(d.size());
			e=d.size();
			driver.manage().window().maximize();
	 	 	}
	 	 	
	 	 	public static void jiraIndexing() throws Exception {
				for (int i=1;i<=e;i++){
//					if(i>e) {
//						Thread.sleep(3000);
//						break;
//			 		}
				driver.findElement(By.xpath("(//td[contains(@class,'summary')]/p/a)["+i+"]")).click(); 
//		        ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
				Thread.sleep(3000);
				System.out.println("\n");
				System.out.println(i +"\n");
		 		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		 		checkNotclosedJiras();
		 		backToJiraList();
				}
//				driver.close();
	 	 	}
	 	 	
	 		public static void backToJiraList() throws Exception {
	 	 		driver.navigate().back();
	 	 		Thread.sleep(6000);
	 	 	}
	 		
	 	 	public static void checkNotclosedJiras() {
	 	 		System.out.println("Checking whether the jira is closed");	 	 	
		 		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 	 		WebElement a = driver.findElement(By.id("status-val"));
	 	 		String s = a.getText();
	 	 		System.out.println(s);
	 	 		if(s.contains("RESOLVED") || s.contains("QA VERIFY")) {
//	 	 			System.out.println("Verify and Close the Jira ASAP");
          		  Username = driver.findElement(By.xpath("//span[contains(@id,'issue_summary_reporter_')]")).getText();
          	  System.out.println("Verify and Close the Jira ASAP" +"\t" +Username);
		 	 		  jId = driver.findElement(By.xpath("//a[@class='issue-link']")).getText();
	            	  jSummary= driver.findElement(By.xpath("//h1[@id='summary-val']")).getText();
	            	  jToDo= "Verify and Close the Jira ASAP";
	            	  jResolution = "RESOLVED";
	            	  dummList.add(Username);
	            	  jiraToDo.add(jToDo);
	 	 			  jiraId.add(jId);
	            	  jiraSummary.add(jSummary);
	            	  jiraResolution.add(jResolution);
	 	 		}
	 	 		else if(s.contains("CLOSED")){
	 	 			System.out.println("Jira is Closed");
	 	 			
	 	 		}
	 	 		else {
	 	 			System.out.println("Jira is not resolved yet");
	 	 	}
		}
public static void mailCreatorPass() throws Exception{
                
                Properties props = new Properties();
     props.setProperty("mail.transport.protocol", "smtp");
     props.setProperty("mail.host", "smtp.amazon.com");
     Session session = Session.getDefaultInstance(props, null);

     try {
         Message message = new MimeMessage(session);
         message.setFrom(new InternetAddress("PR-DA-BOT@amazon.com"));
//      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("motupalv@amazon.com")); 
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("e2e-da@amazon.com, jdharshn@amazon.com, dhapriy@amazon.com, rjgokul@amazon.com")); 
//         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ravisanb@amazon.com,motupalv@amazon.com")); 
        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("motupalv@amazon.com, indhumas@amazon.com, naishw@amazon.com, vmani@amazon.com")); 
        message.setSubject("Resolved Not Closed Jira's");
        
        StringBuilder buf = new StringBuilder();

         buf.append("<html><body style=\"font-family:Calibri; font-size:90%;\">");
         buf.append("<p>Hi All, </p>");
         buf.append("<p><b>Verify and close the below listed jira's ASAP</b></p>");
         System.out.println("Verify and close the below listed jira's ASAP");
         buf.append(bugstableMainline7());
         buf.append("<p><b>If any of the Jira's are reproducible please reopen the jira and attach logs also add Video, screenshot (If applicable) </b></p>");
         buf.append("<p>Note: <br><b>**This is an automated mail. Do not reply as this mailbox is not monitored**</b></p>");
         buf.append("<p><b>Always Praise the Brave - MahaRishi</b></p>");
         buf.append("<p>Thanks,<br>PR Team BOT.</p>");
         
         String msg = buf.toString();
         message.setContent(msg, "text/html");
 
         Transport.send(message);
 
         System.out.println("Mail sent");
 
     } catch (Exception e) {
        System.out.println("Mail not sent");
        e.printStackTrace();
     }

     finally {
		driver.close();
	}
}
     
public static String bugstableMainline7(){
	 	
		System.out.println("Entered to making table");
	 	String s2 = "<p><b><i>None</b></i></p>";
	 	System.out.println(jiraId.size());
	 	
	 	if(jiraId.size()>0){
	 		
	 	 s2 ="\r\n" + 
	 	 		"<table id=\"jiras\" style=\"font-size:80%\" cellspacing=\"0\" width=\"100%\" border=\"0\" cellpadding=\"10\">\r\n" + 
	 	 		"<tbody><tr>\r\n" + 
	 	 		"<th bgcolor=\"DCDCDC\">Issue-ID</th>\r\n" + 
	 	 		"<th bgcolor=\"DCDCDC\">Summary</th>\r\n" + 
	 	 		"<th bgcolor=\"DCDCDC\">Status</th>\r\n" + 
	 	 		"<th bgcolor=\"DCDCDC\">Reporter</th>\r\n" +
	 	 		"</tr><tr>\r\n"; 
	 	 		
	 	 		 	 		
	 	    for(int j=0; j<jiraId.size(); j++)
	 	    { 
	 	    	s2 += "<td bgcolor=\"#9BBB59\"> <a href=\"https://issues.labcollab.net/browse/"+jiraId.get(j)+"\">"+jiraId.get(j) +"</a></td>\r\n" + 
	 	   
	 	    	 		"<td bgcolor=\"#EBF1DE\">"+jiraSummary.get(j) +"</td>\r\n" + 
	 	    	 		"<td bgcolor=\"#EBF1DE\">"+jiraResolution.get(j) +"</td>\r\n" + 
	 	    	 		"<td bgcolor=\"#EBF1DE\">"+dummList.get(j) +"</td>\r\n" +
	 	    	 		"</tr><tr>\r\n";
	 	    	}
	    
	 	       s2 += "</tr></tbody></table>";
	 	  }
 	 	return s2;
	}
} 
	 	 	