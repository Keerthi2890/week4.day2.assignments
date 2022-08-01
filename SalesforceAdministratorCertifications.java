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

public class SalesforceAdministratorCertifications {
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
		//Click on Ceritification Administrator
		driver.findElement(By.linkText("Administrator")).click();
		//Navigate to Certification - Administrator Overview window
		//Verify the Certifications available for Administrator Certifications should be displayed
		System.out.println(driver.getTitle());	
		driver.close();
		//switch to primary window
		driver.switchTo().window(primary);
		//close the browser
		driver.quit();
	}
}
