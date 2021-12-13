package org.chetu.load.test;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.chetu.load.test.functions.HDMeetFunctions;
import org.testng.annotations.Test;

/**
 * @author amitk2/parulj
 */

/**
 * Add Participant in the meeting: 1.Configure the browser. 2.Navigate to the
 * URL. 3.Join the meeting with meeting id
 */

public class AddParticipant {
	HDMeetFunctions hdMeetFunctions;

	public AddParticipant() {
		hdMeetFunctions = new HDMeetFunctions();
	}

	@Test(invocationCount = 2)
	public void AddParticipantTest()
			throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		hdMeetFunctions.addParticipants();
	}
}
