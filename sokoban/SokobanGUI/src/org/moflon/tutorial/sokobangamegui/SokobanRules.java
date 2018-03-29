package org.moflon.tutorial.sokobangamegui;

import SokobanLanguage.Board;
import SokobanLanguage.Field;
import SokobanLanguage.Figure;

public class SokobanRules {

	public Result move(Figure figure, Field field) {
		field.setFigure(figure);
		return new Result();
	}

	public Result validateBoard(Board board) {
		return new Result();
	}
}
