package main.java.Game.House;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import main.java.Game.Utils.Logger;

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
	
	public void loadImage(InputStream is) {
		try {
			this.img = new Image(is, 100, 100, false, false);
		} catch (Exception e) {
			Logger.warn("Could not load image " + name);
			e.printStackTrace();
		}	
	}
	
	
	public void loadImageFromList(String name) {
		InputStream is = null;
		try {
			is = this.getClass().getClassLoader().getResourceAsStream("main/resources/images/Houses/" + name + ".png");
			loadImage(is);
		} catch (Exception e1) {
			try {
				if (is.equals(null)) {
					is = this.getClass().getClassLoader().getResourceAsStream("main/resources/images/Houses/" + name + ".jpg");
					loadImage(is);
				}
			} catch (Exception e2) {
				try {
					if (is.equals(null)) {
						is = this.getClass().getClassLoader().getResourceAsStream("main/resources/images/Houses/" + name + ".svg");
						loadImage(is);
					}
				} catch (Exception e3) {
					Logger.warn("Could not load a house image");
				}
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getName() + " - £" + getSPrice());
		return sb.toString();
	}

}
