package main.java.Game.Items;

import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.Game.Utils.Logger;

public class Items {

	// Note: Items should be named uniquely

	public ObservableList<ItemsTable> itemsProp;

	public Items() {
		itemsProp = FXCollections.observableArrayList();
	}

	public Optional<ItemsTable> getItem(String name) {
		return itemsProp.stream().filter(item -> name.equals(item.getName())).findFirst();
	}

	public void add(ItemsTable item, int quantity) {
		item.setS_Quantity(quantity);
		add(item);
	}

	public boolean remove(ItemsTable item) {
		Optional<ItemsTable> existingItems = itemsProp.stream().filter(it -> item.getName().equals(it.getName()))
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
				ItemsTable newItem = new ItemsTable(item.getName(), Double.parseDouble(item.getPrice()),
						existing_int - new_int);
				itemsProp.set(index, newItem);
				return true;
			}
		}
	}

	public boolean remove(Item item) {
		return remove(new ItemsTable(item));
	}

	public boolean remove(Item item, int quantity) {
		item.setQuantity(quantity);
		return remove(item);
	}

	public boolean remove(ItemsTable item, int quantity) {
		item.setS_Quantity(quantity);
		return remove(item);
	}

	public void add(ItemsTable item) {
		Optional<ItemsTable> existingItems = itemsProp.stream()
				.filter(it -> (item.getName().equals(it.getName())) & (item.getPrice().equals(it.getPrice())))
				.findFirst();
		if (existingItems.isPresent()) {
			int index = itemsProp.indexOf(existingItems.get());
			int existing_int = existingItems.get().getS_Quantity();
			int new_int = item.getS_Quantity();
			ItemsTable newItem = new ItemsTable(item.getName(), Double.parseDouble(item.getPrice()),
					existing_int + new_int);
			itemsProp.set(index, newItem);
		} else {
			itemsProp.add(item);
		}
	}

	public void add(Item item) {
		add(new ItemsTable(item));
	}

	public void add(Item item, int quantity) {
		item.setQuantity(quantity);
		add(item);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		java.util.Iterator<ItemsTable> itr = itemsProp.iterator();

		while (itr.hasNext()) {
			sb.append(itr.next().toString());
			sb.append("\n");
		}
		return sb.toString();
	}

}
