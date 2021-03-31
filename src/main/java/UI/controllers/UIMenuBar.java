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
	
	//About
	public Menu about;
	
	public MenuItem showAboutPage;
	
	public PlayerLogController playerLogController;
	public AboutPageController aboutPageController;
	public Stage logStage, aboutStage;
	public Scene logScene, aboutScene;
	
	//Main Controller
	public MainController mc;
	
	public void init(MainController mc) {
		this.mc = mc;
		Parent root = null, root2 = null;
		
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
		logScene = new Scene(root, 400, 400);
		logStage = new Stage();
		logStage.setTitle("Player log");
		logStage.setScene(logScene);
		
		// Initialise About page
		FXMLLoader aboutPage_loader = null;
		try {
			aboutPage_loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/AboutPage.fxml"));
			root2 = aboutPage_loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			Logger.error("Failed to load a XML file from UIMenuBar");
		}

		aboutPageController = aboutPage_loader.getController();
		aboutPageController.init(mc);
		aboutScene = new Scene(root2, 600, 600);
		aboutStage = new Stage();
		aboutStage.setTitle("About");
		aboutStage.setScene(aboutScene);
	}
	
	public UIMenuBar() {
		
		//File
		file = new Menu("File");
		close = new MenuItem("Close");
		close.setOnAction(e -> Platform.exit());
		file.getItems().add(close);
		
		//Log
		log = new Menu("Log");
		showPlayerLog = new MenuItem("Show log of events");
		showPlayerLog.setOnAction(e -> {if(logStage.isShowing()) { logStage.hide(); } else { logStage.show(); }});
		log.getItems().add(showPlayerLog);
		
		//About
		about = new Menu("About");
		showAboutPage = new MenuItem("About");
		showAboutPage.setOnAction(e -> {if(aboutStage.isShowing()) { aboutStage.hide(); } else { aboutStage.show(); }});
		about.getItems().add(showAboutPage);
		
		//Add to menu
		this.getMenus().add(file);
		this.getMenus().add(log);
		this.getMenus().add(about);
		
	}
	
}
