package main.java.Game.Threads;

import main.java.Game.Utils.Logger;
import main.java.UI.controllers.MainController;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

public class CashFlickerer extends Service<Void> {

	public MainController mc;
	private final int flickerTime = 200;

	public CashFlickerer(MainController mc) {
		this.mc = mc;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {

				// To Do: Play a noise

				for (int i = 0; i < 3; i++) {
					// Set cash to red
					mc.homepageController.infoPaneController.cashLabel.setTextFill(Color.RED);

					// Wait x seconds
					try {
						Thread.sleep(flickerTime);
					} catch (Exception e) {
						// e.printStackTrace();
						Logger.error("Failed to sleep in task manager");
					}

					// Set cash to black
					mc.homepageController.infoPaneController.cashLabel.setTextFill(Color.BLACK);

					// Wait x seconds
					try {
						Thread.sleep(flickerTime);
					} catch (Exception e) {
						// e.printStackTrace();
						Logger.error("Failed to sleep in task manager");
					}
				}
				return null;

			}
		};
	};

}
