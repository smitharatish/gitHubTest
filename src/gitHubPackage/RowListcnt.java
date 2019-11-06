package gitHubPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RowListcnt {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ratish AS\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://bus.makemytrip.com/bus/search/Chennai/Delhi/24-10-2019?from_code=MMTCC1199&to_code=MMTCC2140");
		driver.manage().window().maximize();
		//driver.findElements(By.cssSelector("//div[class='sc-kTUwUJ dmMRtR'] [class='sc-kTUwUJ dmMRtR'] div"))
				//sree@gmail.om-12345

	}

}
