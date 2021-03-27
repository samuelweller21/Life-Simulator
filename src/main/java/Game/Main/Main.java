package main.java.Game.Main;

import javafx.application.Application;
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
import main.java.Game.Utils.DoAfterInit;
import main.java.Game.Utils.Logger;
import main.java.UI.controllers.MainController;

public class Main extends Application {

	public static final boolean DEBUG = true;
	public Parent root = null;
	public MainController mc;
	public Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		Parent root = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/java/UI/view/Main.fxml"));
		try {
			root = loader.load();
			mc = loader.getController();

			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);

			// Set preferred Dimensions

			if (!DEBUG) {

				Rectangle2D screen = Screen.getPrimary().getBounds();
				primaryStage.setWidth(0.8 * screen.getWidth());
				primaryStage.setHeight(0.8 * screen.getHeight());
				primaryStage.setX(0.1 * screen.getWidth());
				primaryStage.setY(0.1 * screen.getHeight());

			} else {

				ObservableList<Screen> screens = Screen.getScreens();
				Rectangle2D primaryScreen = Screen.getPrimary().getBounds();

				primaryStage.setWidth(0.8 * primaryScreen.getWidth());
				primaryStage.setHeight(0.8 * primaryScreen.getHeight());
				primaryStage.setX(0.1 * primaryScreen.getWidth());
				primaryStage.setY(0.1 * primaryScreen.getHeight());

			}
			primaryStage.setOnCloseRequest(e -> Platform.exit());
			primaryStage.setTitle("Life Simulator");
			primaryStage.getIcons().add(new Image("/main/java/UI/view/res/house.png"));
			primaryStage.show();
			this.primaryStage = primaryStage;
		} catch (Exception e) {
			e.printStackTrace();
		}
		DoAfterInit.run();
	}

	@Override
	@FXML
	public void stop() {
		Logger.info("Closing down");
	}

	public static void launcher(String[] args) {
		launch(args);
	}

}
