package gitHubPackage;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginGitHub {

	private static Logger log = Logger.getLogger(LoginGitHub.class.getName());
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ratish AS\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.github.com/login");
		Thread.sleep(3000);
		checkLogin(driver);

	}
	public static void checkLogin(WebDriver driver) {
		driver.findElement(By.xpath("//input[@id='login_field']")).sendKeys("smitharatish");
		log.info("Sent user id to login field");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("chkdcsn@1");
		log.info("Sent wrong password to password field ");
		driver.findElement(By.xpath("//input[@name='commit']")).submit();
		log.info("Submit the details");
		
	}

}
