package org.moflon.tutorial.sokobangamegui.tests;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.moflon.tutorial.sokobangamegui.controller.BoardCreator;
import org.moflon.tutorial.sokobangamegui.controller.Controller;

//import testCasesJavaRobot.TestProgram;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//TestProgram prog = new TestProgram();
		//prog.drawSine();
		
		Logger.getRootLogger().setLevel(Level.INFO);

		/* Create an instance of this class and create an empty board */
		Controller controller = new Controller();
		controller.switchBoard(BoardCreator.createEmptyBoard(8, 8));

	}

}
