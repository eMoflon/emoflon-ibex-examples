package org.moflon.tutorial.sokobangui.tests;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.TextField;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.lang.reflect.Executable;

//import javaRobot.AutoClick;

public class TestProgram {
	
	private Robot robot;
	private AutoClick autoclick;
	private Runtime runtime;
	
	public void drawSine() {
		int xSet = 110;
		int ySet = 500;
		// variable of the sine wave
		int x = 0;
		int y = 0;
		double alpha = 0;
		runtime = Runtime.getRuntime();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			runtime.exec("mspaint.exe");
			
			// open an application by providing the installation path.
			//runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		robot.delay(2000); // give the delay. Robot does not sleep. that's why we have to provide delay
		
		// set position the mouse to initial position
		robot.mouseMove(xSet, ySet);
		
		AutoClick.leftClick(robot);
		
		//Point point = runtime.getLocationOnScreen();
		
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		
		// draw sine wave
		for (int i = 0; i < 360; i++) {
			robot.mouseMove(x+xSet, y+ySet); // set to the origin of the coordinate system in context of the sine wave.
			
			y = (int) Math.round(50*Math.sin(alpha)); // 100 will be the amplitude of the sine wave
			x++;
			alpha += 2*Math.PI/360*10; // in the denominator, the multipling by 1 does not change anything. By changing the multiplying value, we can change the frequency.
			
			
			robot.delay(15); // to see the delayed execution of the program
			
		}
		
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(2000);
		//System.exit(0);
		
	}

}
