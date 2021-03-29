package main.java.UI.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import main.java.Game.Utils.Logger;

public class UIMenuBar extends MenuBar {

	//File
	public Menu file;
	
	public MenuItem close;
	
	//Log
	public Menu log;
	
	public MenuItem showPlayerLog;
	public PlayerLogController playerLogController;
	public Stage logStage;
	public Scene scene;
	
	//Main Controller
	public MainController mc;
	
	public void init(MainController mc) {
		this.mc = mc;
		Parent root = null;
		// Initialise Playerlog
		FXMLLoader playerLog_loader = null;
		try {
			playerLog_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/PlayerLog.fxml"));
			root = playerLog_loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("Failed to load a XML file from UIMenuBar");
		}

		playerLogController = playerLog_loader.getController();
		playerLogController.init(mc);
		scene = new Scene(root, 400, 400);
		logStage = new Stage();
		logStage.setTitle("Player log");
		logStage.setScene(scene);

	}
	
	public UIMenuBar() {
		
		//File
		file = new Menu("File");
		close = new MenuItem("Close");
		close.setOnAction(e -> Platform.exit());
		file.getItems().add(close);
		this.getMenus().add(file);
		
		//Log
		log = new Menu("Log");
		showPlayerLog = new MenuItem("Show log of events");
		showPlayerLog.setOnAction(e -> {if(logStage.isShowing()) { logStage.hide(); } else { logStage.show(); }});
		
		log.getItems().add(showPlayerLog);
		this.getMenus().add(log);
		
	}
	
}
