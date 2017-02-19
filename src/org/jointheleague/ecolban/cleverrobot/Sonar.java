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
	private GpioPinDigitalOutput strobe = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);// Strobe...pin 11
	private GpioPinInput echo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03); // Echo...pin 15
	private int filteredDistance;

	public Sonar()
	{
		echo.addListener(new GpioPinListenerDigital()
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

	public int readSonar()
	{
		strobe.high();
		strobe.low();
		return filteredDistance;
	}
}
