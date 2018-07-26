package org.moflon.tutorial.sokobangui.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.moflon.tutorial.sokobangamegui.controller.BoardCreator;
import org.moflon.tutorial.sokobangamegui.controller.Controller;
import org.moflon.tutorial.sokobangamegui.view.actions.PlayAction;

class TestBoardCreation {

	private TestView view;
	private Controller controller;
	
	@BeforeEach
	public void createView() {
		controller = new Controller((c, b) -> {
			view = new TestView(c, b);
			return view;
		});
		controller.switchBoard(BoardCreator.createEmptyBoard(8, 8));
	}
	
	/*@AfterEach
	public void deleteView() {
		controller = null;
	}*/
	
	@Test
	public void testSingleSokoban() {
		view.createSokoban(2,2);
		assertEquals(ExpectedBoards.singleSokoban(), view.printBoard());
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testTwoSokoban() {
		view.createSokoban(2,2);
		view.createSokoban(3,3);
		assertEquals(ExpectedBoards.twoSokoban(), view.printBoard());
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testSingleBlock() {
		view.createBlock(5,6);
		assertEquals(ExpectedBoards.singleBlock(), view.printBoard());
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testNoSokoban() {
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testNoBlock() {
		view.createSokoban(2,2);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testNoBoulder() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createEndPos(2,5);
		assertTrue(controller.boardIsValid());
	}
	
	@Test
	public void testNoEndPos() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testSingleBoulder() {
		view.createBoulder(3,2);
		assertEquals(ExpectedBoards.singleBoulder(), view.printBoard());
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testSingleEndPos() {
		view.createEndPos(2,5);
		assertEquals(ExpectedBoards.singleEndPos(), view.printBoard());
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoard() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertEquals(ExpectedBoards.validBoard(), view.printBoard());
		assertTrue(controller.boardIsValid());
	}
	
	@Test
	public void testBoulderOnEndPos() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(3,2);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testBlockOnBoulder() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(5,6);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testSokobanOnBoulder() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(2,2);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testSokobanOnBlock() {
		view.createSokoban(2,2);
		view.createBlock(2,2);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoardMoreBlocksLessEndPos() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBlock(6,6);
		view.createBlock(7,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoardMoreEndPosLessBlocks() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		view.createEndPos(4,5);
		view.createEndPos(6,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoardEqualEndPosAndBlocks() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBlock(6,6);
		view.createBlock(7,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		view.createEndPos(4,5);
		view.createEndPos(6,5);
		assertTrue(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoardBlockIsInCorner() {
		view.createSokoban(2,2);
		view.createBlock(7,7);
		view.createBlock(0,0);
		view.createBlock(0,7);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		view.createEndPos(4,5);
		view.createEndPos(6,5);
		assertFalse(controller.boardIsValid());
	}
	
	@Test
	public void testValidBoardMoveSokoban() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertEquals(ExpectedBoards.validBoard(), view.printBoard());
		assertTrue(controller.boardIsValid());
		view.setPlayModus(true);
		view.moveFigure(view.getField(2,2), view.getField(2,3));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidBoardMoveBlock() {
		view.createSokoban(2,2);
		view.createBlock(2,3);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		assertEquals(ExpectedBoards.validBoardMoveBlock(), view.printBoard());
		assertTrue(controller.boardIsValid());
		view.setPlayModus(true);
		view.moveFigure(view.getField(2,2), view.getField(2,3));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		view.moveFigure(view.getField(2,3), view.getField(2,4));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(ExpectedBoards.validBoardMoveBlockAfterMove(), view.printBoard());
	}
	
	@Test
	public void testValidBoardMoveBoulder() {
		view.createSokoban(2,2);
		view.createBlock(3,2);
		view.createBoulder(2,3);
		view.createEndPos(2,5);
		assertEquals(ExpectedBoards.validBoardMoveBoulder(), view.printBoard());
		assertTrue(controller.boardIsValid());
		view.setPlayModus(true);
		view.moveFigure(view.getField(2,2), view.getField(2,3));
		//view.moveFigure(view.getField(2,3), view.getField(2,4));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(ExpectedBoards.validBoardMoveBoulder(), view.printBoard());
	}
	
	@Test
	public void testValidBoardImportFile() {
		controller.loadSOKFile("boards/chaos.sok");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		assertEquals(ExpectedBoards.validBoardFileImport(), view.printBoard());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testValidBoardExportFile() {
		view.createSokoban(2,2);
		view.createBlock(5,6);
		view.createBoulder(3,2);
		view.createEndPos(2,5);
		//assertEquals(ExpectedBoards.validBoard(), view.printBoard());
		//assertTrue(controller.boardIsValid());
		//view.setPlayModus(true);
		//view.moveFigure(view.getField(2,2), view.getField(2,3));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		controller.saveSOKFile("boards/gen_board.sok");
	}
}
