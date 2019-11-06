package gitHubPackage;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.logging.log4j.*;
import gitHubPackage.LoginGitHub;
import org.junit.Assert;

public class MenuIn {
	private  static  Logger log = LogManager.getLogger(MenuIn.class.getName());
	
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ratish AS\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		log.info("Maximising Window");
		driver.get("http://www.github.com/login");
		log.info("Reaching the URL");
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		log.info("Logging to GitHub");
		LoginGitHub.checkLogin(driver);
		
		//checking the navigation menu bar on top after logging
		log.info("Navigation menu list");
		List<WebElement> navmenu =driver.findElements(By.xpath("//nav[contains(@class,'d-flex flex-column flex-lg-row flex-self-stretch flex-lg-self-auto')]//a"));
		System.out.println("Nav Menu size="+navmenu.size());
		
		//Removing last link from the list
		int menuSize = navmenu.size()-1;
		
		//opening the links in new tabs
		log.info("Opening windows in new tab");
		
		for(int i=1;i<menuSize;i++) {
			
			navmenu.get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.RETURN));
			
		}
		
		System.out.println("size="+driver.getWindowHandles().size());
		Set<String> handles = driver.getWindowHandles();
		log.info("Accessing the tabs to check title");
		int cnt =0;
		String mainWin="";
		for(String handItr :handles) {
			if(cnt==0) {
			 mainWin =handItr;
			}
			cnt=cnt+1;
			if(cnt!=1) {
				driver.switchTo().window(handItr);
				log.info("Switching to open tab and comparing the title");
				System.out.println("title="+driver.getTitle());	
				if(cnt==2) {
					Assert.assertEquals("Explore", driver.getTitle());	
				}else if(cnt==3) {
					Assert.assertEquals("Marketplace · Tools to improve your workflow", driver.getTitle());	
				}else if(cnt==4) {
					Assert.assertEquals("Issues", driver.getTitle());
				}else if(cnt==5) {
					Assert.assertEquals("Pull Requests", driver.getTitle());
				}
				
			}
			
		}
		driver.switchTo().window(mainWin);
		driver.findElement(By.xpath("//div[@class='Header-item mr-0 mr-lg-3 flex-order-1 flex-lg-order-none']")).click();
		driver.findElement(By.xpath("//div[@class='Header-item position-relative']//summary[@class='Header-link']")).click();
		List<WebElement> lstmenu = driver.findElements(By.xpath("//details-menu[@class='dropdown-menu dropdown-menu-sw']//a"));
		System.out.println("size="+lstmenu.size());
		//for(WebElement wmenu : lstmenu) {
			for(int menucnt =0;menucnt<lstmenu.size();menucnt++) {
				System.out.println("in="+lstmenu.get(menucnt).getText());
				//lstmenu.get(menucnt).sendKeys(Keys.CONTROL,Keys.ENTER);
			}
			
			//wmenu.click();
			//break;
	//	}
		
		
	
	}

}
