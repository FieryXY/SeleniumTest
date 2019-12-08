package fieryxy.testselenium;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static String URL = "https://www.jointheleague.org";
	
    public static void main(String[] args) throws MalformedURLException
    {
    	//DesiredCapabilities caps = new DesiredCapabilities();
    	/*caps.setCapability("browserName", "IE");
    	caps.setCapability("version", "11");
    	caps.setCapability("platform", "WIN10");
    	caps.setCapability("name", "My First Test");*/
    	
    	
    	System.setProperty("webdriver.chrome.driver", "/Users/sohan/Documents/LeagueStuff/testselenium/chromedriver");
    	WebDriver driver = new ChromeDriver();
    	driver.get("https://www.jointheleague.org");
    	/*WebElement element = driver.findElement(By.name("q"));

    	element.sendKeys("TestingBot");
    	element.submit();*/

    	System.out.println(driver.getTitle());
    	driver.quit(); 
    }
}
