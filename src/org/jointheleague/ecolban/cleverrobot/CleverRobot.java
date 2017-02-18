package org.jointheleague.ecolban.cleverrobot;

import java.io.IOException;
import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class CleverRobot extends IRobotAdapter {
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput strobe = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);// Strobe...pin
	boolean BLOCKING = true;
	private GpioPinInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN); // Echo...pin
																												// 15

	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws IOException {
		try {
			IRobotInterface base = new SimpleIRobot();
			CleverRobot rob = new CleverRobot(base);
			rob.initialize();
			rob.getGoing();
		} catch (InterruptedException | IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void initialize() throws IOException {
		System.out.println("Try event listner, rev Friday1815");
		strobe.addListener(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState() == PinState.HIGH) {
					System.out.println(".......HIGH......." + event.getPin() + " = " + event.getState());
					strobe.setState(PinState.LOW);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (event.getState() == PinState.LOW) {
					System.out.println(".......LOW......." + event.getPin() + " = " + event.getState());
					strobe.setState(PinState.HIGH);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void getGoing() {
		System.out.println("Going");
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			strobe.high();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			strobe.low();
		}
	}
}
// public void run() throws InterruptedException{
//
// while(true){
// try {
// Thread.sleep(2000);
// strobe.high(); // Make trigger pin HIGH
// Thread.sleep((long) 0.01);// Delay for 10 microseconds
// strobe.low(); //Make trigger pin LOW
// while(echo.isLow()){ //Wait until the ECHO pin gets HIGH
//
// }
// long startTime= System.nanoTime(); // Store the surrent time to calculate
// ECHO pin HIGH time.
// while(echo.isHigh()){ //Wait until the ECHO pin gets LOW
//
// }
// long endTime= System.nanoTime(); // Store the echo pin HIGH end time to
// calculate ECHO pin HIGH time.
//
// System.out.println("Distance :" + ((((endTime-startTime)/1e3)/2) / 29.1)
// + " cm"); //Printing out the distance in cm
// Thread.sleep(1000);
//
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
// @Override
// public void run() {
// System.out.println("Running");
// while(true)
// {
// pin11.pulse(1, BLOCKING);
// try {
// Thread.sleep(200);
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// }
