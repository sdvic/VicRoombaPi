package org.jointheleague.ecolban.cleverrobot;
/**************************************************************************
 * Version 0.1 adding left/right/center sensors
 **************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	
	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Tuesday2100");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.getGoing();
	}

	private void getGoing() {
		Sonar sonar = new Sonar();
		for (int i = 0; i < 100000; i++) {
			System.out.println("+" + sonar.readSonarLeft() + " . " + sonar.readSonarCenter() + " . " + sonar.readSonarRight());
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
		try {
			shutDown();
		} catch (IOException e) {
		}
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
