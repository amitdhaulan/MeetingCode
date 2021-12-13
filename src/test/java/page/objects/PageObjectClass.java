package page.objects;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageObjectClass {
	public static Properties prop;
	public static Properties objectProp;
	public static WebDriver driver;
	WebElement loginButton;
	WebElement email;
	WebElement emailNext;
	WebElement password;
	WebElement passbtn;
	WebElement meetingurl;
	WebElement meetingroom;
	WebElement bar;
	WebElement element;
	WebElement SecurityOptions;

	public PageObjectClass() throws IOException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		prop = new Properties();
		FileInputStream fis = new FileInputStream("../jitsi-meet-torture/utils/config.properties");
		prop.load(fis);
		driver.navigate().to(prop.getProperty("URL"));
		driver.manage().window().maximize();
		objectProp = new Properties();
		FileInputStream objectFis = new FileInputStream("../jitsi-meet-torture/utils/objects.properties");
		objectProp.load(objectFis);
	}

	public WebElement getEmail() {
		return driver.findElement(By.xpath(PageObjectClass.objectProp.getProperty("Email")));
	}

	public WebElement getPassword() {
		return driver.findElement(By.xpath(PageObjectClass.objectProp.getProperty("Password")));
	}

	public WebElement getLoginButton() {
		return driver.findElement(By.xpath(PageObjectClass.objectProp.getProperty("LoginButton")));
	}

	public WebElement getNextButton() {
		return driver.findElement(By.xpath(PageObjectClass.objectProp.getProperty("NextButton")));
	}

	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void explicitWait(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20);

		// Wait till the element is not visible
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("ur xpath here")));
	}

	public WebElement getPassButton() {
		return driver.findElement(By.xpath(PageObjectClass.objectProp.getProperty("PassButton")));
	}

	public WebElement getMeetingUrl() {
		return driver.findElement(By.xpath(""));
	}

	public void doubleCLick() throws InterruptedException {
		Actions act = new Actions(driver);
		Thread.sleep(2000);
		meetingurl = driver.findElement(By.xpath("//img[@class='editIcons']"));
		act.doubleClick(meetingurl).perform();
	}

	public WebElement getMeetingRoom() {
		return driver.findElement(By.xpath("//span[text()='Start Meeting']"));
	}

	public void switchToFrame() {
		driver.switchTo().frame("jitsiConferenceFrame0");
	}

	public WebElement getBar() {
		return driver.findElement(By.xpath("//div[@class='toolbox-content-wrapper']"));
	}

	public WebElement getBar1() {
		return driver.findElement(By.xpath("//div[@class='jitsi-icon jitsi-icon-default ']"));
	}

	public WebElement getElement() {
		return driver.findElement(
				By.cssSelector("#new-toolbox > div > div > div > div.toolbox-button-wth-dialog > div > div > div"));
	}

	public WebElement getSecurityOption() {
		return driver.findElement(By.xpath("//span[text()='Security options']"));
	}

	/*
	 * public WebElement getLobbySwitch() { // return
	 * driver.findElement(By.xpath("//input[@id='lobby-section-switch']")); return
	 * driver.findElement(By.xpath(
	 * "/html/body/div[2]/div/div/div[3]/div/div[2]/div/div/div/div/form/div/div[1]/div/div/label/span[1]"
	 * )); }
	 */

	public WebElement getLobbySwitch() {
//		return driver.findElement(By.xpath("//input[@id='lobby-section-switch']"));
		return driver.findElement(By.xpath("//label[@class='css-4b7tya']"));
	}

	// Close Lobby Pop-Up
	public WebElement getEndLobby() {
		return driver.findElement(By.cssSelector(
				"body > div.atlaskit-portal-container > div > div > div.focus-lock > div > div.css-krjp51 > div > div > header > div > svg"));
	}

	// End the Moderator
	public WebElement getEndMeeting() {
		return driver.findElement(By.cssSelector(
				"#new-toolbox > div > div > div.toolbox-content-items > div:nth-child(9) > div > div > div"));
	}
}
