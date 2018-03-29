package org.moflon.tutorial.sokobangamegui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import org.moflon.tutorial.sokobangamegui.Controller;
import org.moflon.tutorial.sokobangamegui.View;

public class PlayAction implements ActionListener {
	public static final String PLAY  = "Play Game!";
	public static final String EDIT = "Edit Game";
	
	private JMenuItem playToggle;
	private boolean playModus;
	private View view;
	private Controller controller;
	
	public PlayAction(JMenuItem playToggle, View view, Controller controller) {
		this.playToggle = playToggle;
		playModus = false;
		playToggle.setText(PLAY);
		this.view = view;
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(isEditModus())
			if(!controller.boardIsValid()) 
				return;
		
		playModus = !playModus;
		playToggle.setText(playModus? EDIT : PLAY);
		view.updateView();
	}
	
	private boolean isEditModus() {
		return !playModus;
	}

	public boolean isPlayModus() {
		return playModus;
	}
}
