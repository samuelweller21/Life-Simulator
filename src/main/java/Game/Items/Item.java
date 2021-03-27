package main.java.Game.Items;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import main.java.Game.Utils.Logger;

public class Item {

	SimpleStringProperty name;
	String s_name;
	SimpleStringProperty quantity;
	int s_quantity;
	SimpleStringProperty price;

	public String getName() {
		return name.get();
	}

	public String getS_name() {
		return s_name;
	}

	public String getQuantity() {
		return quantity.get();
	}

	public int getS_quantity() {
		return s_quantity;
	}

	public String getPrice() {
		return price.get();
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public void setQuantity(SimpleStringProperty quantity) {
		this.quantity = quantity;
	}

	public void setS_Quantity(int s_quantity) {
		this.s_quantity = s_quantity;
		quantity.set(Integer.toString(s_quantity));
	}

	public int getS_Quantity() {
		return s_quantity;
	}

	public void remove(int quantity) {
		if (quantity > s_quantity) {
			Logger.warn("In item " + getName() + " you are trying to remove " + quantity + " but only own "
					+ getQuantity() + " so nothing has been removed");
		} else {
			s_quantity -= quantity;
			this.quantity.set(Integer.toString(s_quantity));
		}
	}

	public void setPrice(SimpleStringProperty price) {
		this.price = price;
	}

	public Item(String name, double price, int quantity) {
		this.name = new SimpleStringProperty(name);
		s_name = name;
		this.quantity = new SimpleStringProperty(Integer.toString(quantity));
		s_quantity = Integer.parseInt(this.quantity.get());
		this.price = new SimpleStringProperty(Double.toString(price));
	}

	public Observable quantityPropery() {
		return quantity;
	}

	public String toString() {
		return name.get() + " - " + quantity.get() + " - £" + price.get();
	}

}
