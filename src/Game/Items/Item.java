package Game.Items;

import Game.Utils.Logger;

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

	public void remove(int quantity) {
		if (quantity > this.quantity) {
			Logger.warn("In item " + getName() + " you are trying to remove " + quantity + " but only own "
					+ getQuantity() + " so nothing has been removed");

		} else {
			this.quantity -= quantity;
		}
	}

}
