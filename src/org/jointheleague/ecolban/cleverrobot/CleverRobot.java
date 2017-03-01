package org.jointheleague.ecolban.cleverrobot;
<<<<<<< HEAD
/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
=======
/**************************************************************************
 * Version 0.1 adding left/right/center sensors
 **************************************************************************/
>>>>>>> d1f3e655fe345f2acc5eff4c9d0e46bebfe54e25
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter
{

<<<<<<< HEAD
    public CleverRobot(IRobotInterface iRobot)
    {
	super(iRobot);
    }

    public static void main(String[] args) throws Exception
    {
	System.out.println("Try event listner, rev Monday 2030");
	IRobotInterface base = new SimpleIRobot();
	CleverRobot rob = new CleverRobot(base);
	rob.getGoing();
    }
=======
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
>>>>>>> d1f3e655fe345f2acc5eff4c9d0e46bebfe54e25

    private void getGoing()
    {
	Sonar sonar = new Sonar();
	for (int i = 0; i < 100000; i++)
	{
	    try
	    {
		System.out.print(sonar.readSonar("left") + "     ");
		Thread.sleep(300);
		System.out.print(sonar.readSonar("center") + "     ");
		Thread.sleep(300);
		System.out.print(sonar.readSonar("right") + "\n");
		Thread.sleep(300);
	    } catch (Exception e)
	    {
		System.out.println("readSonar exception");
	    }
	}
	try
	{
	    shutDown();
	} catch (IOException e)
	{
	}
    }

    private void shutDown() throws IOException
    {
	reset();
	stop();
	closeConnection();
    }
}
