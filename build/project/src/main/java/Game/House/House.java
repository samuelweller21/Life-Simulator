package main.java.Game.House;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class House {
	
	public String s_name;
	public SimpleStringProperty name = new SimpleStringProperty("");
	public String url;
	public double s_price;
	public SimpleStringProperty price = new SimpleStringProperty("");
	public Image img;
	
	public House(String name, double price, String url) {
		this.s_name = name;
		this.name.set(name);
		this.s_price = price;
		this.price.set(Double.toString(price));
		this.url = url;

		//Load image
		try {
			img = new Image(new FileInputStream(url), 100, 100, false, false);
		} catch (FileNotFoundException e) {
			Logger.warn("Could not load image " + name + " from " + url);
			e.printStackTrace();
		}			
		
	}
	
	public Image getImage() {
		return img;
	}
	
	public String getSPrice() {
		return price.get();
	}
	
	public double getPrice() {
		return s_price;
	}
	
	public String getName() {
		return s_name;
	}
	
	public House(String name, double price) {
		this.s_name = name;
		this.name.set(name);
		this.s_price = price;
		this.price.set(Double.toString(price));
	}
	
	public House(String name, double price, boolean lookForImage) {
		this.s_name = name;
		this.name.set(name);
		this.s_price = price;
		this.price.set(Double.toString(price));
		
		if (lookForImage) {
			loadImageFromList(name);
		}
	}
	
	public void loadImage(String url) {
		this.url = url;
		try {
			this.img = new Image(new FileInputStream(url), 100, 100, false, false);
		} catch (FileNotFoundException e) {
			Logger.warn("Could not load image " + name + " from " + url);
			e.printStackTrace();
		}		
	}
	
	
	public void loadImageFromList(String name) {
		File file;
		try {
			url = Utils.RES_URL + "/images/Houses/" + name + ".png";
			file = new File(url);
			if (!file.exists()) {
				url = Utils.RES_URL + "/images/Houses/" + name + ".jpg";
				file = new File(url);
				if (!file.exists()) {
					url = Utils.RES_URL + "/images/Houses/" + name + ".svg";
					file = new File(url);
				}
			}
			if (file.exists()) {
				loadImage(url);
			}
		} catch (Exception e) {
			Logger.warn("Could not find file " + name);
			e.printStackTrace();
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName() + " - £" + getSPrice());
		return sb.toString();
	}

}
