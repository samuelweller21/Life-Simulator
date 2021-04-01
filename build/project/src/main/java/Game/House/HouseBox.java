package main.java.Game.House;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.Game.Character.Player;
import main.java.Game.Utils.Utils;

public class HouseBox extends HBox {

	public House house;
	private Label name;
	private Label price;
	private Button buy;
	private ImageView image;
	
	public HouseBox(House house) {
		this.house = house;
		
		//Add components
		name = new Label(house.getName());
		price = new Label("£ " + Utils.largePriceFormatter(house.getPrice()));
		image = new ImageView(house.getImage());
		buy = new Button("Buy");
		buy.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Player.buyHouse(house);
			}
		});
		
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(10));
		box.getChildren().add(name);
		box.getChildren().add(price);
		box.getChildren().add(buy);
		
		this.getChildren().add(box);
		this.getChildren().add(image);
		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(10));
	}
	
	
	
	
}
