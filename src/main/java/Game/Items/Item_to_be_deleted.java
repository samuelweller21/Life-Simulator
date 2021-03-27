package main.java.Game.Items;

import java.util.List;

import main.java.Game.Utils.Logger;

public class Item {

	private String name;
	private double price;
	private int quantity;

	public Item(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return name + " - " + quantity + " - £" + price;
	}

	public Item(List<String> list) {
		try {
			this.name = list.get(0);
			this.price = Double.parseDouble(list.get(1));
			this.quantity = Integer.parseInt(list.get(2));
		} catch (Exception e) {
			Logger.warn("Failed to create an item with name " + list.get(0));
		}
	}

	public void remove(int quantity) {
		if (quantity > this.quantity) {
			Logger.warn("In item " + getName() + " you are trying to remove " + quantity + " but only own "
					+ getQuantity() + " so nothing has been removed");

		} else {
			this.quantity -= quantity;
		}
	}

}
