package Threads;

import Game.Utils.Logger;
import UI.controllers.MainController;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

public class TimeFlickerer extends Service<Void> {

	public MainController mc;
	private final int flickerTime = 200;

	public TimeFlickerer(MainController mc) {
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
					mc.homepageController.infoPaneController.timeLabel.setTextFill(Color.RED);

					// Wait x seconds
					try {
						Thread.sleep(flickerTime);
					} catch (Exception e) {
						// e.printStackTrace();
						Logger.error("Failed to sleep in task manager");
					}

					// Set cash to black
					mc.homepageController.infoPaneController.timeLabel.setTextFill(Color.BLACK);

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
