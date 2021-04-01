package main.java.UI.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import main.java.Game.Utils.Logger;

public class DebugPaneController {

	public MainController mc;
	
	@FXML public TextArea debugArea;

	@FXML public AnchorPane debugPane;
	
	@FXML public MenuItem showAllItem, hideAllItem;
	@FXML public RadioMenuItem errorRadio, warnRadio, infoRadio, unknownRadio, initRadio, uiRadio, playerRadio;
	
	public void init(MainController mc) {
		this.mc = mc;
		debugArea.setWrapText(true);
		debugArea.textProperty().bind(Logger.stringLog);
		errorRadio.setOnAction(e -> Logger.updateBinding());
		warnRadio.setOnAction(e -> Logger.updateBinding());
		infoRadio.setOnAction(e -> Logger.updateBinding());
		unknownRadio.setOnAction(e -> Logger.updateBinding());
		initRadio.setOnAction(e -> Logger.updateBinding());
		uiRadio.setOnAction(e -> Logger.updateBinding());
		playerRadio.setOnAction(e -> Logger.updateBinding());
		
		//Autoscroll
		debugArea.textProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<? extends Object> arg0, Object arg1, Object arg2) {
				debugArea.setScrollTop(Double.MAX_VALUE);				
			}
		});
	}
	
	@FXML
	public void showAll() {
		errorRadio.setSelected(true);
		warnRadio.setSelected(true);
		infoRadio.setSelected(true);
		unknownRadio.setSelected(true);
		initRadio.setSelected(true);
		uiRadio.setSelected(true);
		playerRadio.setSelected(true);
		Logger.updateBinding();
	}
	
	@FXML
	public void hideAll() {
		errorRadio.setSelected(false);
		warnRadio.setSelected(false);
		infoRadio.setSelected(false);
		unknownRadio.setSelected(false);
		initRadio.setSelected(false);
		uiRadio.setSelected(false);
		playerRadio.setSelected(false);
		Logger.updateBinding();
	}

}
