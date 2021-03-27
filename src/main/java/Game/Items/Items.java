package main.java.Game.Items;

import java.util.Iterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.Game.Utils.Logger;

public class Items {

	// Note: Items should be named uniquely

	public ObservableList<Item> itemsProp;
	
	//Optional global item set
	
	public static ObservableList<Item> global;
	
	public static void addToGlobal(Item item) {
		
	}
	
	public static void addToGlobal(Items items) {
		
	}
	
	public static void removeFromGlobal(Item item) {
		
	}
	
	public static void removeFromGlobal(Items items) {
		
	}
	
	public void add(Items items) {
		Iterator<Item> itr = items.itemsProp.iterator();
		while (itr.hasNext()) {
			this.itemsProp.add(itr.next());
		}
	}
	
	public void remove(Items items) {
		Iterator<Item> itr = items.itemsProp.iterator();
		while (itr.hasNext()) {
			this.itemsProp.remove(itr.next());
		}
	}

	public Items() {
		itemsProp = FXCollections.observableArrayList();
	}

	public Optional<Item> getItem(String name) {
		return itemsProp.stream().filter(item -> name.equals(item.getName())).findFirst();
	}

	public void add(Item item, int quantity) {
		item.setS_Quantity(quantity);
		add(item);
	}

	public boolean remove(Item item) {
		Optional<Item> existingItems = itemsProp.stream().filter(it -> item.getName().equals(it.getName()))
				.findFirst();
		if (!existingItems.isPresent()) {
			Logger.warn(
					"You tried to remove " + item.getQuantity() + " of " + item.getName() + " but you do not own any");
			return false;
		} else {
			if ((existingItems.get().getS_Quantity()) < item.getS_Quantity()) {
				Logger.warn("You tried to remove " + item.getQuantity() + " of " + item.getName() + " but you only own "
						+ existingItems.get().getQuantity());
				return false;
			} else {
				int index = itemsProp.indexOf(existingItems.get());
				int existing_int = existingItems.get().getS_Quantity();
				int new_int = item.getS_Quantity();
				Item newItem = new Item(item.getName(), Double.parseDouble(item.getPrice()),
						existing_int - new_int);
				itemsProp.set(index, newItem);
				return true;
			}
		}
	}

	public boolean remove(Item item, int quantity) {
		item.setS_Quantity(quantity);
		return remove(item);
	}

	public void add(Item item) {
		Optional<Item> existingItems = itemsProp.stream()
				.filter(it -> (item.getName().equals(it.getName())) & (item.getPrice().equals(it.getPrice())))
				.findFirst();
		if (existingItems.isPresent()) {
			int index = itemsProp.indexOf(existingItems.get());
			int existing_int = existingItems.get().getS_Quantity();
			int new_int = item.getS_Quantity();
			Item newItem = new Item(item.getName(), Double.parseDouble(item.getPrice()),
					existing_int + new_int);
			itemsProp.set(index, newItem);
		} else {
			itemsProp.add(item);
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		java.util.Iterator<Item> itr = itemsProp.iterator();

		while (itr.hasNext()) {
			sb.append(itr.next().toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
