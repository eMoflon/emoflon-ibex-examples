package org.moflon.tutorial.sokobangui.junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.moflon.tutorial.sokobangamegui.controller.BoardCreator;
import org.moflon.tutorial.sokobangamegui.controller.Controller;
import org.moflon.tutorial.sokobangui.tests.AutoClick;

class OpenBoard {
	private Robot robot;
	//private AutoClick autoclick;
	private Runtime runtime;
	
	

	@Test
	void test() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		/*runtime = Runtime.getRuntime();
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		

		/* Create an instance of this class and create an empty board */
		Controller controller = new Controller();
		controller.switchBoard(BoardCreator.createEmptyBoard(8, 8));
		
		//robot.delay(2000); // give the delay. Robot does not sleep. that's why we have to provide delay
		
		//robot.mouseMove(arg0, arg1);
		assertEquals("Welcome to Sokoban!", controller.currentStatus);
	}

}
