package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class SalesforceCustomerServiceOptions {
	public static void main(String[] args) throws InterruptedException {
		//Chrome driver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		//Load the URL
		driver.get("https://login.salesforce.com/");
		//maximize the window
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Login to the application with Username and Password
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		//Click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("(//span[@class=' label bBody'])[2]")).click();
		//window Handle- Moving to the second window
		String primary=driver.getWindowHandle();
		Set<String> windowHandles=driver.getWindowHandles();
		List<String> fstWindowHandles=new ArrayList<String>(windowHandles);
		String sndWindowHandles=fstWindowHandles.get(1);
		driver.switchTo().window(sndWindowHandles);
		//Click confirm on Confirm redirect
		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand']")).click();
		//Access the shadow dom elements
		Shadow dom=new Shadow(driver);
		//Click on Resources
		WebElement resource=dom.findElementByXPath("//span[text()='Resources']");
		resource.click();
		//mouse action!-mouse hover on Learning On Trailhead	
		Actions builder=new Actions(driver);
		builder.moveByOffset(10,20).perform();
		builder.click().perform();
		WebElement option=dom.findElementByXPath("//span[text()='Learning on Trailhead']");
		builder.moveToElement(option).perform();
		Thread.sleep(3000);
		//Click on Salesforce Certifications
		WebElement certification=dom.findElementByXPath("//a[text()='Salesforce Certification']");
		Actions actions = new Actions(driver);
		actions.moveToElement(certification).click().perform();
		//Choose role as salesforce Architect
		driver.findElement(By.xpath("(//img[@class='roleMenu-item_img'])[2]")).click();
		//Get the Text(Summary) of Salesforce Architect
		System.out.println(driver.findElement(By.xpath("//div[@class='slds-container--center slds-container--medium slds-p-vertical--large']//h1")).getText());
		System.out.println(driver.findElement(By.xpath("//div[@class='slds-container--center slds-container--medium slds-p-vertical--large']//div[1]")).getText());
		//Get the List of Salesforce Architect Certifications Available
		System.out.println("Salesforce Architect Certifications:");
		List<WebElement> cert1=driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
		for(int i=0; i<cert1.size(); i++) {
			String title1=cert1.get(i).getText();
			System.out.println(title1);		
		}
		//Click on Application Architect 
		driver.findElement(By.linkText("Application Architect")).click();
		//Get the List of Certifications available
		System.out.println("Application Architect Certifications:");
		List<WebElement> cert2=driver.findElements(By.xpath("//div[@class='credentials-card_title']//a"));
		for(int i=0; i<cert2.size(); i++) {
			String title2=cert2.get(i).getText();
			System.out.println(title2);		
		}
		driver.close();
		//switch to primary window
		driver.switchTo().window(primary);
		//close the browser
		driver.quit();
	}
}
