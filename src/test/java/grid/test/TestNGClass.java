package grid.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestNGClass {
	public static WebDriver driver;

	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		String URL = "https://www.google.com";
		String Node = "https://172.16.4.212:27317/wd/hub";

		// System Property for Chrome Driver
		System.setProperty("webdriver.chrome.driver", "E:/amit/Projects/Selenium/Drivers/chromedriver_win32/chromedriver.exe");

		DesiredCapabilities cap = DesiredCapabilities.firefox();

		driver = new RemoteWebDriver(new URL(Node), cap);

		driver.navigate().to(URL);
		Thread.sleep(5000);
		driver.quit();
	}
}
