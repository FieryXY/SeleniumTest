package fieryxy.testselenium;

import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Hello world!
 *
 */
public class App 
{
	
	static String URL = "https://www.jointheleague.org";
	static ArrayList<String> urls = new ArrayList<String>();
	
	//static String[] ids = {"ar","zh-TW","cs","da","nl","fi","fr","de","el","hi","ja"};
	static String[] ids = {"el","la","ja","ru"};
	
	private static void generateUrls() {
		String template = "https://translate.google.com/#view=home&op=translate&sl=%s&tl=%s";
		String source = "en";
		String translate = ids[0];
		
		for(String id : ids) {
			translate = id;
			urls.add(String.format(template, source, translate));
			source = translate;
			if(source.equals("zh-TW")) {source = "zh-CN";}
		}
		urls.add(String.format(template, translate, "en"));
	}
	
	
    public static void main(String[] args) throws MalformedURLException
    {
    	generateUrls();
    	String toTranslate = JOptionPane.showInputDialog("Enter your text here.");
    	String current = toTranslate;
    	
    	System.setProperty("webdriver.chrome.driver", "/Users/sohan/Downloads/chromedriver-2");
    	ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");	
        WebDriver driver = new ChromeDriver(options);
    	for(String url : urls) {
    		current = translate(url, current, driver);
    		System.out.println(current);
    	}
    	
    	driver.close();
    	
    	
    	JOptionPane.showMessageDialog(null, current);
    	System.out.println(toTranslate);
    	
    }
    
    
    
    private static String translate(String url, String current, WebDriver driver) {
    	driver.get(url);
    	WebElement source = driver.findElement(By.xpath("//*[@id=\"source\"]"));
    	WebElement sourceInput = driver.findElement(By.xpath("//*[@id=\"input-wrap\"]/div[2]"));
    	source.sendKeys(current);
    	try {new WebDriverWait(driver, (int) 0.25*current.length()).until(ExpectedConditions.attributeToBe(sourceInput, "textContent", current));
    	} catch(TimeoutException e) {System.out.println("There was a problem");}
    	System.out.println("Content Set");
    	new WebDriverWait(driver, (int) 1*current.length()).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]/span")));
    	System.out.println("Element Visible");
    	try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//String switcher = Keys.chord(Keys.COMMAND, Keys.SHIFT, "s");
    	//source.sendKeys(switcher);
    	//WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]/span"));
    	WebElement result = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/div/span[1]"));
    	return result.getAttribute("textContent");
    }
}
