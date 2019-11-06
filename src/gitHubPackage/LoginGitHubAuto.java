package gitHubPackage;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.Assert;

import java.util.List;

public class LoginGitHubAuto {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ratish AS\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.github.com");
		Thread.sleep(5000);
		String sTitle = driver.getTitle();
		Assert.assertEquals(sTitle, "The world’s leading software development platform · GitHub");
		
		Actions action = new Actions(driver);
		
		System.out.println("in");
		WebElement target_name = driver.findElement(By.xpath("//summary[contains(text(),'Why GitHub?')]"));
		System.out.println("GitHub");
		action.moveToElement(target_name).perform();
		System.out.println("hover");
		Thread.sleep(2000);
		//action.release(target_name);
		List<WebElement> lst = driver.findElements(By.xpath("//ul[@class='list-style-none f5 pb-3']//li//a"));
		System.out.println("sizelst="+lst.size());
		System.out.println("out");
		
		for(WebElement ele : lst) {
			System.out.println("ele="+ele.getText());
			if(ele.getText().contains("Code review")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Project management")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Integrations")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Actions")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Package registry")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Security")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Team management")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Social coding")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Documentation")) {
				//ele.click();
				break;
			}
			if(ele.getText().contains("Code hosting")) {
				//ele.click();
				break;
			}
			
		}
		
		
		//driver.findElement(By.xpath("//ul[@class='d-lg-flex list-style-none']//li/a[contains(text(),'Enterprise')]")).click();
		
		
		System.out.println("new action");
		//
		/*Notes - https://www.seleniumeasy.com/selenium-tutorials/how-to-perform-mouseover-action-in-selenium-webdriver*/
		
		
		/* --Action class not working the second time.*/
		WebElement target_name3 = driver.findElement(By.xpath("//summary[contains(text(),'Explore')]"));
		action.moveToElement(target_name3);
		System.out.println("in target");
		action.click().build().perform();
		//clicking Explore git hub sub menu without taking as list
		//driver.findElement(By.xpath("//ul[@class='list-style-none mb-3']//li//a[contains(text(),'Explore GitHub')]")).click();
		System.out.println("done");
		
		//To retrieve elements under Explore menu
		List<WebElement> lstexp = driver.findElements(By.xpath("//ul[@class='list-style-none mb-3']//li//a"));
		System.out.println("sizelst="+lstexp.size());
		for(WebElement elem :lstexp) {
			System.out.println("name="+elem.getText());
			if(elem.getText().contains("Explore GitHub")) {
				System.out.println("done1");
				//elem.click();
				break;
			}
			if(elem.getText().contains("Topics")) {
				System.out.println("done2");
				//elem.click();
				break;
			}
			if(elem.getText().contains("Collections")) {
				System.out.println("done3");
				//elem.click();
				break;
			}
			if(elem.getText().contains("Trending")) {
				System.out.println("done4");
				//elem.click();
				break;
			}
			if(elem.getText().contains("Learning Lab")) {
				System.out.println("done5");
				//elem.click();
				break;
			}
			if(elem.getText().contains("Open source guides")) {
				System.out.println("done6");
				//elem.click();
				break;
			}
			
		}
		
		//new Actions(driver).moveToElement(target_name3).build().perform();
	//	driver.findElement(By.xpath("//summary[contains(text(),'Explore')]")).click();
		
		System.out.println("move to");
		Thread.sleep(2000);
		
		List<WebElement> lstmeexp = driver.findElements(By.xpath("//ul[@class='list-style-none mb-3']//li//a"));
		System.out.println("sizemnu="+lstmeexp.size());

		
		//Click Market Place
		//driver.findElement(By.xpath("//ul[@class='d-lg-flex list-style-none']//li//a[contains(text(),'Marketplace')]")).click();
		
		WebElement target_name4 = driver.findElement(By.xpath("//summary[contains(text(),'Pricing')]"));
		action.moveToElement(target_name4);
		System.out.println("in target");
		action.click().build().perform();
		//xpath for pricing
		
		List<WebElement> lstmprice = driver.findElements(By.xpath("//ul[@class='d-lg-flex list-style-none']//li[5]//div//a"));
		System.out.println("sizemnu="+lstmprice.size());
		for(WebElement elemp :lstmprice) {
			System.out.println("name="+elemp.getText());
			if(elemp.getText().contains("Plans")) {
				System.out.println("Plans");
				//elemp.click();
				break;
			}
			if(elemp.getText().contains("Compare plans")) {
				System.out.println("Compare plans");
				//elemp.click();
				break;
			}
			if(elemp.getText().contains("Contact Sales")) {
				System.out.println("Contact Sales");
				//elemp.click();
				break;
			}
			if(elemp.getText().contains("Nonprofit ")) {
				System.out.println("Nonprofit ");
				//elemp.click();
				break;
			}
			if(elemp.getText().contains("Education")) {
				System.out.println("Education");
				//elemp.click();
				break;
			}
		}
		driver.findElement(By.xpath("//input[@placeholder='Search GitHub']")).sendKeys("testing selenium");
		//clicking search
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='Box position-absolute overflow-hidden jump-to-suggestions js-jump-to-suggestions-container']")).click();
		driver.navigate().back();
		Thread.sleep(1000);
		
		//selecting an element with help of cssselector
		//driver.findElement(By.cssSelector(".button_main")).click();
		//or if value and class is unique
		//driver.findElement(By.cssSelector(".button_main[value='something']")).click()
		//div[@class='col-12 col-sm-8 col-lg-3 mx-auto mx-lg-0 mb-3 mb-lg-0 px-3 mt-4']//button[@class='btn-mktg btn-primary-mktg btn-block mt-n1']
		
		driver.findElement(By.xpath("//input[@id='user[login]']")).sendKeys("smitha123");
		driver.findElement(By.xpath("//input[@id='user[email]']")).sendKeys("smitha@123"); 
		driver.findElement(By.xpath("//input[@id='user[password]']")).sendKeys("123@abcd");
		driver.findElement(By.linkText("Sign up for GitHub"));
		driver.findElement(By.xpath("//form[@class='home-hero-signup text-gray-dark js-signup-form']//button")).submit();
		//driver.findElement(By.partialLinkText("Sign")).click();
		
		//summary[@contains(text(),'Why GitHub')]
		
		//click login
		//driver.findElement(By.xpath("//a[@class='HeaderMenu-link no-underline mr-3']")).click();

	}

}

