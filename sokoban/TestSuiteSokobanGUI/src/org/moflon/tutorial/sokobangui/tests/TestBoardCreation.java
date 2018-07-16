package org.moflon.tutorial.sokobangui.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.moflon.tutorial.sokobangamegui.controller.BoardCreator;
import org.moflon.tutorial.sokobangamegui.controller.Controller;

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
}
