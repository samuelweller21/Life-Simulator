package main.java.UI.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.java.Game.Character.Player;
import main.java.Game.Main.Main;
import main.java.Game.Threads.CashFlickerer;
import main.java.Game.Threads.TimeFlickerer;
import main.java.Game.Utils.Logger;

public class MainController {

	@FXML
	public HomepageController homepageController;
	@FXML
	public DebugPaneController debugPaneController;

	public Main main;
	public CashFlickerer cashFlickerer;
	public TimeFlickerer timeFlickerer;

	@FXML
	public void initialize() {
		Logger.init(this);
		Player.player_init("John Smith");
		initDebug();

		Player.init(this);
		homepageController.init(this);
		cashFlickerer = new CashFlickerer(this);
		timeFlickerer = new TimeFlickerer(this);
		
		
		//Temp
		this.homepageController.showBackpack();
	}

	public void initDebug() {

		// Load screens
		ObservableList<Screen> screens = Screen.getScreens();
		Rectangle2D primaryScreen = Screen.getPrimary().getBounds();

		// Load in debug XML
		FXMLLoader debugLoader = new FXMLLoader(getClass().getResource("/main/java/UI/view/DebugPane.fxml"));
		Parent debugRoot = null;
		try {
			debugRoot = debugLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		debugPaneController = debugLoader.getController();
		debugPaneController.init(this);

		if (Main.DEBUG) {

			// Set up window
			Stage debugWindow = new Stage();
			debugWindow.setOnCloseRequest(e -> Platform.exit());
			debugWindow.setTitle("Debug Window");
			Scene debugScene = new Scene(debugRoot);

			if (screens.size() > 1) {
				// Put debug on second monitor
				Rectangle2D secondScreen = screens.get(1).getBounds();
				debugWindow.setWidth(secondScreen.getWidth());
				debugWindow.setHeight(secondScreen.getHeight());
				debugWindow.setX(primaryScreen.getWidth());
				debugWindow.setMaximized(true);
			} else {
				// Put debug on the primary monitor a little bit smaller
				debugWindow.setWidth(primaryScreen.getWidth() / 2);
				debugWindow.setHeight(primaryScreen.getHeight() / 2);
				debugWindow.setX(primaryScreen.getWidth() / 2);
			}

			debugWindow.setScene(debugScene);
			try {
				debugWindow.getIcons().add(new Image("/main/java/UI/view/res/house.png"));
			} catch (Exception e) {
				Logger.error("Couldn't get icon image, has it moved?");
			}
			debugWindow.show();
		}

	}
}
