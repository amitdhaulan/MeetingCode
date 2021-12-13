package org.chetu.load.test;

import java.awt.AWTException;
import java.io.IOException;

import org.chetu.load.test.functions.HDMeetFunctions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * @author amitk2/parulj
 */

public class HdMeetLoadJitsi {
	HDMeetFunctions hdmeetfunctions = new HDMeetFunctions();

	@Test
	public void hdmeetlogin() throws InterruptedException, IOException {
		// Login into the application
		hdmeetfunctions.login();

		// Start the meeting
		Thread.sleep(40);
		hdmeetfunctions.startMeeting();

		// Turn off the lobby
		hdmeetfunctions.turnOffLobby();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException, AWTException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			// End the meeting
			hdmeetfunctions.endMeeting();
		}
	}
}
