package org.moflon.tutorial.sokobangamegui.rules;

import SokobanLanguage.Board;
import SokobanLanguage.Field;
import SokobanLanguage.Figure;

public class SokobanRules {

	public Result move(Figure figure, Field field) {
		// FIXME:  Check if the move makes sense
		field.setFigure(figure);
		return new Result();
	}

	public Result validateBoard(Board board) {
		// FIXME: Check if the board is valid
		return new Result();
	}
}
