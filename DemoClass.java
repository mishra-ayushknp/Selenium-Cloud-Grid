package project;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;

public class DemoClass {
	public String username = "username";
	public String accesskey = "accesskey";
	public String gridURL = "@hub.lambdatest.com/wd/hub";

	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	public static String remote_url = "http://localhost:4444/";
	public Capabilities capabilities;
	
	@Parameters({"browser"})
	@BeforeMethod
	public void setDriver(String browser) throws MalformedURLException {
		// Setting the Browser Capabilities
		System.out.println("Test is running on "+browser);
		if (browser.equals("chrome")) {
			capabilities = new ChromeOptions();
			} 
		else if (browser.equals("edge")) {
			capabilities = new EdgeOptions();
			}
		driver.set(new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities));
		// Directing to the Testing Website
		driver.get().get("https://ecommerce-playground.lambdatest.io/");
		// Maximizing the Window
		driver.get().manage().window().maximize();
		driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		}
	
	public WebDriver getDriver() {
		return driver.get();
		}
	@Test
	public void validCredentials() {
		getDriver().findElement(By.name("search")).sendKeys("iphone");
		}
	
	
	@AfterMethod
	// To quit the browser
	public void closeBrowser() {
		driver.get().quit();
		//driver.remove();
		}
	}

