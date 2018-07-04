package org.moflon.tutorial.sokobangamegui.junitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.junit.jupiter.api.Test;
import org.moflon.tutorial.sokobangamegui.controller.BoardCreator;
import org.moflon.tutorial.sokobangamegui.controller.Controller;
import org.moflon.tutorial.sokobangamegui.rules.SokobanRules;
import org.moflon.tutorial.sokobangamegui.tests.AutoClick;

import SokobanLanguage.Board;
import SokobanLanguage.Field;
import SokobanLanguage.Figure;
import SokobanLanguage.SokobanLanguageFactory;

class OpenBoard {
	private Robot robot;
	//private AutoClick autoclick;
	private Runtime runtime;
	private Board board;
	private SokobanRules sokobanRules;

	@Test
	void test_launch_empty_board() {
		Logger.getRootLogger().setLevel(Level.DEBUG);
		/* Create an instance of this class and create an empty board */
		Controller controller = new Controller();
		board = BoardCreator.createEmptyBoard(8, 8);
		controller.switchBoard(board);
		
		//robot.delay(2000); // give the delay. Robot does not sleep. that's why we have to provide delay
		
		//robot.mouseMove(arg0, arg1);
		assertEquals("Welcome to Sokoban!", controller.currentStatus);
	}
	@Test
	void test_add_sokoban() {
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
		board = BoardCreator.createEmptyBoard(8, 8);
		controller.switchBoard(board);
		
		//robot.delay(2000); // give the delay. Robot does not sleep. that's why we have to provide delay
		
		//robot.mouseMove(arg0, arg1);
		assertEquals("Welcome to Sokoban!", controller.currentStatus);
		
		for (EClass elementClass : controller.getFigureClasses()) {
			//ec = elementClass;
			if (elementClass.getName() == "Sokoban") {
				System.out.println("You have got the sokoban..!!");
				final EClass ec = elementClass; 
				for (Field f : board.getFields()) {
					System.out.println(f.getRow());
					Figure newFigure = (Figure) SokobanLanguageFactory.eINSTANCE.create(ec);
					controller.setFigure(f, newFigure);
					sokobanRules = new SokobanRules(board);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			} 
		}
		
	}

}
