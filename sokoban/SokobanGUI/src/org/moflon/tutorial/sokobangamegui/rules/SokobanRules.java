package org.moflon.tutorial.sokobangamegui.rules;

import org.emoflon.sokobanrules.api.SokobanrulesAPI;
import org.emoflon.sokobanrules.SokobanValidator;

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
		SokobanValidator validator = new SokobanValidator(board);
		SokobanrulesAPI api = validator.getAPI();
		
		if(!api.moreThanOneSokoban().hasMatches()) {
			return new Result("You need to have at least one Sokoban");
		}
		
		return new Result();
	}
}
