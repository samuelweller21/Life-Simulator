package main.java.UI.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import main.java.Game.Utils.PlayerLog;

public class PlayerLogController {

	@FXML
	public TextArea playerLogArea;
	@FXML
	public VBox textBox;
	
	public MainController mc;
	
	public void init(MainController mc) {
		this.mc = mc;
		playerLogArea.setWrapText(true);
		playerLogArea.textProperty().bind(PlayerLog.stringLog);
		
		//Autoscroll
		playerLogArea.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				playerLogArea.setScrollTop(Double.MAX_VALUE);			
			}
		});
	}
	
}
