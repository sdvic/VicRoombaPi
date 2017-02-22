package org.jointheleague.ecolban.cleverrobot;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.GpioPinInput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Sonar
{
	private long startTime;
	private long stopTime;
	final GpioController gpio = GpioFactory.getInstance();
	private GpioPinDigitalOutput strobeLeft = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);// Strobe...pin 11
	private GpioPinInput echoLeft = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03); // Echo...pin 15
	private GpioPinDigitalOutput strobeRight = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);// Strobe...pin 12
	private GpioPinInput echoRight = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04); // Echo...pin 16
	private GpioPinDigitalOutput strobeCenter = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02);// Strobe...pin 13
	private GpioPinInput echoCenter = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05); // Echo...pin 18
	private int filteredDistance;

	public Sonar()
	{
		echoLeft.addListener(new GpioPinListenerDigital()
		{
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event)
			{
				if (event.getState() == PinState.HIGH)
				{
					startTime = System.nanoTime();
				}
				if (event.getState() == PinState.LOW)
				{
					stopTime = System.nanoTime();
				}
				int distance = (int) ((((stopTime - startTime) / 1e3) / 2) / 29.1); // cm
				if (distance > 0 && distance < 1000)
				{
					filteredDistance = distance;
				}
			}
		});
	}

	public int readSonarLeft()
	{
		strobeLeft.high();
		strobeLeft.low();
		return filteredDistance;
	}
	public int readSonarRight()
	{
		strobeLeft.high();
		strobeLeft.low();
		return filteredDistance;
	}
	public int readSonarCenter()
	{
		strobeLeft.high();
		strobeLeft.low();
		return filteredDistance;
	}
}
