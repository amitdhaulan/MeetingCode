package org.chetu.load.test.functions;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
import page.objects.PageObjectClass;

/**
 * @author amitk2/parulj
 */

public class HDMeetFunctions {
	static PageObjectClass poc;
	public static WebDriver driver;
	int count = 0;

	public HDMeetFunctions() {
		/*
		 * try { poc = new PageObjectClass(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	public void initializeChrome() {
		try {
			poc = new PageObjectClass();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Login function
	public void login() throws InterruptedException {
		initializeChrome();
		poc.implicitWait();
		poc.getLoginButton().click();
		poc.getEmail().sendKeys(PageObjectClass.prop.getProperty("Email"));
		poc.getNextButton().click();
		poc.implicitWait();
		poc.getPassword().sendKeys(PageObjectClass.prop.getProperty("Password"));
		poc.getPassButton().click();
		poc.implicitWait();
		poc.doubleCLick();
		poc.implicitWait();
	}

	// Start meeting function
	public void startMeeting() throws InterruptedException {
		poc.getMeetingRoom().click();
		
//		poc.explicitWait(poc.getMeetingRoom()er);
		
		
		poc.implicitWait();
		Thread.sleep(3000);
		// Switch to Iframe
		poc.switchToFrame();
		poc.implicitWait();
		Actions action = new Actions(PageObjectClass.driver);
		action.moveToElement(poc.getBar());
		//action.clickAndHold(poc.getBar());
		poc.implicitWait();
		poc.getElement().click();
		poc.implicitWait();
	}

	// Turn-off lobby function
	public void turnOffLobby() throws InterruptedException {
		poc.getSecurityOption().click();
		poc.implicitWait();
		String str = poc.getLobbySwitch().getAttribute("data-checked");

		System.out.println("===============> " + str);

		try {
			if (str.equals("true") || str.equals(null) || str.equals("null")) {
				poc.getLobbySwitch().click();
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

		// poc.getLobbySwitch().click();
		/*
		 * poc.implicitWait(); boolean bool = poc.getLobbySwitch().isSelected();
		 */
		/*
		 * poc.implicitWait(); if (bool == true) { poc.implicitWait();
		 * poc.getLobbySwitch().click(); } else {
		 * System.out.println("lobby is alredy selected"); }
		 */
		poc.implicitWait();
		poc.getEndLobby().click();
	}

	// End meeting function
	public void endMeeting() throws InterruptedException, AWTException {
		try {
			// These coordinates are screen coordinates
			int xCoord = 500;
			int yCoord = 500;

			// Move the cursor
			Robot robot = new Robot();
			robot.mouseMove(xCoord, yCoord);
		} catch (AWTException e) {
			System.out.println(e.getLocalizedMessage());
		}
		Thread.sleep(Integer.parseInt(PageObjectClass.prop.getProperty("EndMeetingTime")));
		poc.getEndMeeting().click();
		System.out.println("Meeting is Ended");
	}

	// Add participants function
	public void addParticipants()
			throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		count = count + 1;
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		options.addArguments("use-fake-ui-for-media-stream");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.navigate().to("https://dev.hdmeet.com/");
		driver.manage().window().maximize();

		// Add Participant in the Meeting
		WebElement addParticipant = driver.findElement(By.xpath("//div[text()='Join Meeting']"));
		addParticipant.click();
		// Enter Meeting Id
		WebElement meetting_id = driver.findElement(By.xpath("//input[@name='joincname']"));
		meetting_id.sendKeys("loadtesting");
		// Join Meeting
		WebElement join_meeting = driver.findElement(By.xpath("//button[@class='popupJoinBtn']"));
		join_meeting.click();
		/*
		 * driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		 * System.out.println("count value" + count); // if (count % 2 == 0) {
		 * System.out.println("count value" + count);
		 * driver.switchTo().frame("jitsiConferenceFrame0");
		 * driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS); WebElement
		 * bar =
		 * driver.findElement(By.xpath("//div[@class='toolbox-content-wrapper']"));
		 * Actions action = new Actions(driver); action.moveToElement(bar);
		 */

		Thread.sleep(35000);
		playSound();
	}

	/*
	 * @AfterTest public void playAudio() throws MalformedURLException,
	 * UnsupportedAudioFileException, IOException, LineUnavailableException {
	 * System.out.println("=================> After Test");
	 * 
	 * }
	 */

	void playSound()
			throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		File file = new File(
				"E:/amit/Projects/MyTeam/Members/Parul/JitsiMeet/jitsi-meet-torture/resources/fakeAudioStream.wav");
		URL url = null;
		if (file.canRead()) {
			url = file.toURI().toURL();
		}
		System.out.println(url);
		AudioClip clip = Applet.newAudioClip(url);
		clip.play();
		System.out.println("should've played by now");
	}

}
