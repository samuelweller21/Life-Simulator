package UI.controllers;

import java.io.IOException;

import Game.Character.Player;
import Game.Jobs.BinCollector;
import Game.Main.Main;
import Game.Utils.Logger;
import Threads.CashFlickerer;
import Threads.TimeFlickerer;
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
		Player.player_init("John Smith");
		Logger.init(this);
		initDebug();

		Player.init(this);
		Player.setJob(new BinCollector());
		homepageController.init(this);
		cashFlickerer = new CashFlickerer(this);
		timeFlickerer = new TimeFlickerer(this);
	}

	public void initDebug() {

		// Load screens
		ObservableList<Screen> screens = Screen.getScreens();
		Rectangle2D primaryScreen = Screen.getPrimary().getBounds();

		// Load in debug XML
		FXMLLoader debugLoader = new FXMLLoader(getClass().getResource("/UI/view/DebugPane.fxml"));
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
			debugWindow.getIcons().add(new Image("/UI/view/res/house.png"));
			debugWindow.show();
		}

	}
}
