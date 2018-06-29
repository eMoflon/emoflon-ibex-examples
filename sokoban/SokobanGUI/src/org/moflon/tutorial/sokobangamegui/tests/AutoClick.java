package org.moflon.tutorial.sokobangamegui.tests;

import java.awt.Robot;
import java.awt.event.InputEvent;

public class AutoClick {
	
	public static void leftClick(Robot robot) {
		
		// press mouse press button
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		
		// release mouse press button
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	
	public static void doubleLeftClick(Robot robot) {
		
		// press mouse press button
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		
		// release mouse press button
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		// press mouse press button
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				
		// release mouse press button
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
}
