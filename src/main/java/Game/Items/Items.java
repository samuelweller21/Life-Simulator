package main.java.Game.Items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class Items {
	
	// Note: Items should be named uniquely

	public ObservableList<Item> itemsProp;
	
	//Optional global item set
	
	public static ObservableList<Item> global = FXCollections.observableArrayList();
	
	public void addAsNamedList(String name) {
		addFromURL(Utils.RES_URL + "/ItemLists/" + name + ".csv");
	}
	
	public void addFromURL(String url) {
		File file = new File(url);
		if (!file.exists()) {
			Logger.warn("File at " + url + " does not exist");
		} else if (!file.canRead()) {
			Logger.warn("File at " + url + " cannot be read, its probably in use");
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(url));
				String row;
				while ((row = reader.readLine()) != null) {
					String[] data = row.split(",");
					if (data[0].equals("Name")) {
						continue;
					} else {
						Item item = new Item(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]));
						itemsProp.add(item);
					}
				}
				reader.close();
			} catch (Exception e) {
				Logger.warn("Failed reading a item list - " + url);
				e.printStackTrace();
			} 
			
		}
	}
	
	public void addToGlobalAsNamesList(String name) {
		addToGlobalFromURL(Utils.RES_URL + "/ItemLists/" + name + ".csv");
	}
	
	public void addToGlobalFromURL(String url) {
		File file = new File(url);
		if (!file.exists()) {
			Logger.warn("File at " + url + " does not exist");
		} else if (!file.canRead()) {
			Logger.warn("File at " + url + " cannot be read, its probably in use");
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(url));
				String row;
				while ((row = reader.readLine()) != null) {
					String[] data = row.split(",");
					if (data[0].equals("Name")) {
						continue;
					} else {
						Item item = new Item(data[0], Double.parseDouble(data[1]), Integer.parseInt(data[2]));
						addToGlobal(item);
					}
				}
				reader.close();
				
			} catch (Exception e) {
				Logger.warn("Failed reading a item list - " + url);
				e.printStackTrace();
			} 
		}
	}
	
	public static void addToGlobal(Item item) {
		global.add(item);
	}
	
	public static Optional<Item> getFromGlobal(String itemName) {
		return global.stream().filter(item -> itemName.equals(item.getName())).findFirst();
	}
	
	public static void addToGlobal(Items items) {
		Items temp = items;
		temp.add(global);
		Items.global.removeAll(Items.global);
		Iterator<Item> itr = temp.itemsProp.iterator();
		while (itr.hasNext()) {
			Items.global.add(itr.next());
		}
	}
	
	public static void removeFromGlobal(Item item) {
		Items temp = new Items(global);
		temp.remove(item);
		global.setAll(temp.itemsProp);
	}
	
	public static void removeFromGlobal(Items items) {
		Items temp = new Items(global);
		temp.remove(items);
		global = temp.itemsProp;
	}
	
	public void add(Items items) {
		Iterator<Item> itr = items.itemsProp.iterator();
		while (itr.hasNext()) {
			this.add(itr.next());
		}
	}
	
	public void remove(Items items) {
		Iterator<Item> itr = items.itemsProp.iterator();
		while (itr.hasNext()) {
			this.remove(itr.next());
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
			if (existingItems.get().getS_Quantity() < item.getS_Quantity()) {
				Logger.warn("You tried to remove " + item.getQuantity() + " of " + item.getName() + " but you only own "
						+ existingItems.get().getQuantity() + ". Therefore removing " + existingItems.get().getQuantity());
				item.setS_Quantity(existingItems.get().getS_Quantity());
				remove(item);
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
	
	public void add(ObservableList<Item> list) {
		add(new Items(list));
	}
	
	public void remove(ObservableList<Item> list) {
		remove(new Items(list));
	}
	
	public Items(ObservableList<Item> list) {
		itemsProp = list;
	}
	
	public Items(Item item) {
		itemsProp = FXCollections.observableArrayList();
		add(item);
	}
	
	public Items(Items items) {
		itemsProp = FXCollections.observableArrayList();
		add(items);
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
