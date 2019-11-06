package gitHubPackage;

import org.openqa.selenium.By;


import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import gitHubPackage.LoginGitHub;


public class SignInTest {
	//Object initialization for log
	//check why log is overwriting existing data
	 private static Logger log = LogManager.getLogger(SignInTest.class.getName());
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ratish AS\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.github.com/login");
		//Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		//logging in to github website
		log.info("logging in to github website");
		LoginGitHub.checkLogin(driver);
		
		//Thread.sleep(5000);
		String expectedURL = driver.getCurrentUrl();
		String actualURL = "https://github.com/";
		if(actualURL.equalsIgnoreCase(expectedURL)) {
			System.out.println("Test Passed");
			//check whether all links are working with error message
			//check how to automate forking and then check whether the forked repository link is working. 
			log.info("Searching your Repository");
			//search a repository from your forked list
			driver.findElement(By.xpath("//input[@id='dashboard-repos-filter-left']")).sendKeys("io");
			//xpath of listed repositories in your github account
			//driver.findElements(By.xpath("//div[@class='mb-3 Details js-repos-container mt-5']//li//a"));
			//Thread.sleep(2000);
			List<WebElement> lstAjx= driver.findElements(By.xpath("//ul[@class='list-style-none filterable-active']//span[@class='css-truncate css-truncate-target'][contains(text(),'io')]"));
			System.out.println("---lstAjx="+lstAjx.size());
			
			log.debug("Searching repositories with ajax function");
			if (lstAjx.size()>0) {
				log.info("Repositories exists in you account");
				int cnt = 0;
				for(WebElement cntrep : lstAjx) {
					cnt=cnt+1;
					if(cnt==1) {
						log.debug("Inside selected Repository");
						cntrep.click();
						break;
					}
					
				}
			}else {
				log.error("No such search exists.");
				System.out.println("No such search exists.");
			}
		
						
			//search for repositories
			log.info("Search for new repositories");
			WebElement searchRepo = driver.findElement(By.xpath("//input[@placeholder='Search or jump to…']"));
			searchRepo.sendKeys("selenium Automation  javascript" );
			//searchRepo.sendKeys(Keys.ARROW_DOWN);
			//searchRepo.sendKeys(Keys.RETURN);
			//Thread.sleep(2000);
			//searching in All GitHub
			driver.findElement(By.xpath("//span[@class='js-jump-to-badge-search-text-global'][contains(text(),'All GitHub')]")).click();
			//check if results are displayed
			//Thread.sleep(2000);
			//if results then select them in a list
			//the below xpath not working every time 
			List<WebElement> elerepo = driver.findElements(By.xpath("//ul[@class='repo-list']//li//a"));
			//List<WebElement> elerepo = driver.findElements(By.xpath("//div[@class='col-12 col-md-9 float-left px-2 pt-3 pt-md-0 codesearch-results']//li"));
			
			//Select the first repository from the list
			System.out.println("new Repo size="+elerepo.size());
			log.info("Selecting first Repository from the new list");
			if(elerepo.size()>0) {
				int i=0;
				
				//checking left side menu
				
				List<WebElement> lstspec = driver.findElements(By.xpath("//nav[@class='menu border d-none d-md-block']//a"));
				System.out.println("size of leftmenu="+lstspec.size());
				for(WebElement elespec :lstspec) {
					//Thread.sleep(2000);
					//checking whether links are working
					System.out.println("href left menu ="+elespec.getAttribute("href"));
					//elespec.click();
				}
				//checking whether sorting best match menu button works 
				driver.findElement(By.xpath("//summary[@class='btn btn-sm select-menu-button']")).click();
				
				//checking whether all options listed inside the sorting drop down				
				List<WebElement> lstsort = driver.findElements(By.xpath("//details-menu[@class='select-menu-modal position-absolute right-0']//a")); 
				System.out.println("lstsort="+lstsort.size());
				for(WebElement elesort : lstsort) {
					System.out.println("sort items ="+elesort.getText());
				}
				//To remove sorting dropdown 
				driver.findElement(By.xpath("//summary[@class='btn btn-sm select-menu-button']")).click();
				
				
				System.out.println("outside sort");
				System.out.println("size of repo list="+elerepo.size());
				
				for(WebElement rep :elerepo) {
					i=i+1;
					if(i==1) {
						System.out.println("Clicking on the first selected repository");
						log.debug("Clicking on the first selected repository");
						rep.click();
					//check if there are  results in the search
					//div[@class='px-2']//h3[contains(text(),'repository results')]
					//if no results are displayed a div with class='blankslate' is displayed with following xpath
					//div[@class='px-2']//h3[contains(text(),'find any')]
						
						break;
					}
				}
				
				
				//check if the second menu for repositories are working
				
				List<WebElement> lstmenu = driver.findElements(By.xpath("//nav[@class='hx_reponav reponav js-repo-nav js-sidenav-container-pjax container'] //a"));
				System.out.println("lstmenu="+lstmenu.size());
				for(WebElement elemenu:lstmenu) {
					String suburl = elemenu.getAttribute("href");
					//check whether the links are working
					//Thread.sleep(2000);
					System.out.println("-----in second menu------");
					log.info("Inside second menu");
					verifyLinkActive(suburl);
				}
				//Fork the first repository from the list or select repository which has not been forked
				//check if already forked - button will not be present
				boolean present = true;
				try {
					log.debug("checking if Button to Fork exists");
					driver.findElement(By.xpath("//form[contains(@class,'btn-with-count1')]//button[1]"));
					present=true;
				}catch(NoSuchElementException e) {
					log.debug("Button to Fork does not exists");
					present = false;
				}
				if(present=true) {
					//commented for now
					//driver.findElement(By.xpath("//form[contains(@class,'btn-with-count')]//button[1]")).click();
				}else {
					log.debug("Repository already forked");
					System.out.println("Already Forked");
				}
			
				
			}else {
				log.error("Display mesage as to no result exists for the typed search");
			}
			//check if images are working
			try {
				int invalidImgCnt =0;
			log.debug("Image checking");
				List<WebElement> lstimg = driver.findElements(By.tagName("img"));
				System.out.println("Total Images Present are "+lstimg.size());
				for (WebElement eleimg : lstimg) {
					if(eleimg!=null) {
						String imgurl = eleimg.getAttribute("src");
						//verifyImageActive(eleimg);
						System.out.println("--------image url-------");
						verifyLinkActive(imgurl);
					}else {
						log.fatal("Image not found");
					}
					
					
				}
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			System.out.println("outside image verification");
			//check whether your forked repositories are working
			List<WebElement> lst = driver.findElements(By.xpath("//div[@class='mb-3 Details js-repos-container mt-5']//li//a"));
			System.out.println("size="+lst.size());
			//Thread.sleep(2000);
			String str[];
			int lenName=0;
			String second ="";
			for(WebElement ele : lst) {
				System.out.println("text="+ele.getText().split("/")+"----");
				String url =ele.getAttribute("href");
				System.out.println("actual="+ele.getAttribute("href"));
				System.out.println("---href="+ele.getAttribute("href").contentEquals("https://github.com/smitharatish/smitharatish/selenium-cucumber-java"));
				
				//verifying the links of forked repositories	
				//Thread.sleep(2000);
				verifyLinkActive(url);
				
				/* 
				str=ele.getText().split("/");
				lenName =str.length;
				if(lenName>1) {
					second = str[1].trim();
					if(second.equalsIgnoreCase("smitharatish.github.io")) {
						System.out.println("---in1--");
						ele.click();
					}
					if(second.equalsIgnoreCase("selenium-cucumber-java")) {
						System.out.println("---in2--");
						ele.click();
					}
					if(second.equalsIgnoreCase("kspl-selenium-helper")) {
						System.out.println("---in3--");
						ele.click();
					}
				}
				System.out.println("len= "+str.length);
				System.out.println("2nd elem="+str[1].trim());
				*/
				
			}
		
			
		}else {
			log.error("URL Not Found");
			System.out.println("Test Failed");
		}		

	}
	//method to check whether links are active.
	public static void verifyLinkActive(String linkUrl) {
		
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection  httpURLConnect = (HttpURLConnection)url.openConnection();
			httpURLConnect.connect();
			if (httpURLConnect.getResponseCode()==200) {
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
			
			}
			if (httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND) {
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage()+" - "+HttpURLConnection.HTTP_NOT_FOUND);
			
			}
			
		}catch(Exception e) {
			System.out.println("error = "+linkUrl);
			//e.printStackTrace();
		}
	
	}
	/*public static void verifyImageActive(WebElement eleimage) {
		try {
			
			HttpClient client = HttpClientBuilder.create.build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}*/

}
