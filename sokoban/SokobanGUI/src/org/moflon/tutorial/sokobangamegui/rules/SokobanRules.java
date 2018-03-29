package org.moflon.tutorial.sokobangamegui.rules;

import org.emoflon.tutorial.sokobanrules.SokobanValidator;
import org.emoflon.tutorial.sokobanrules.api.SokobanrulesAPI;

import SokobanLanguage.Board;
import SokobanLanguage.Field;
import SokobanLanguage.Figure;

public class SokobanRules {
	private SokobanValidator validator;
	private SokobanrulesAPI api;
	private String allsWell = "Everything seems to be ok...";

	public SokobanRules(Board board) {
		validator = new SokobanValidator(board);
		api = validator.getAPI();
	}

	public Result move(Figure figure, Field field) {
		// FIXME: Only move if it makes sense
		field.setFigure(figure);
		return new Result(true, allsWell);
	}

	public Result validateBoard(Board board) {
		if (!api.atLeatOneSokoban().hasMatches())
			return new Result(false, "You need to have at least one Sokoban!");

		if (api.moreThanOneSokoban().hasMatches())
			return new Result(false, "You cannot have more than one Sokoban!");

		if (!api.atLeastOneBlock().hasMatches())
			return new Result(false, "You must have at least one block!");

		if (!api.atLeastOneEndField().hasMatches())
			return new Result(false, "You must have at least one end field!");

		if (api.atLeastOneBlock().countMatches() != api.atLeastOneEndField().countMatches())
			return new Result(false, "You must have exactly as many end fields as blocks!");

		return api.aTargetFieldIsOccupied()//
				.findAnyMatch()//
				.map(m -> m.getField())//
				.map(f -> new Result(false, "Field [" + f.getRow() + ", " + f.getCol() + "] " + //
						"is an end position but is occupied!"))//
				.orElse(new Result(true, allsWell));
	}
}
