package main.java.Game.House;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.java.Game.Jobs.Job;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class Houses {

	// Note: Items should be named uniquely

		public ObservableList<House> houses;
		
		//Optional global item set
		
		public static ObservableList<House> global = FXCollections.observableArrayList();
		
		public void addAsNamedList(String name) {
			addFromURL(Utils.RES_URL + "/HouseLists/" + name + ".csv");
		}
		
		public void addFromURL(String url) {
			House house;
			File file = new File(url);
			if (!file.exists()) {
				Logger.warn("File at " + url + " does not exist");
			} else if (!file.canRead()) {
				Logger.warn("File at " + url + " cannot be read, its probably in use");
			} else {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(url));
					String row;
					reader.readLine();
					while ((row = reader.readLine()) != null) {
						String[] data = row.split(",");
						if (data[0].equals("Name")) {
							continue;
						} else {
							if (data[2].equals("NA")) {
								house = new House(data[0], Double.parseDouble(data[1]));
							} else {
								house = new House(data[0], Double.parseDouble(data[1]), true);
							}
							houses.add(house);
						}
					}
					reader.close();
				} catch (Exception e) {
					Logger.warn("Failed reading a item list - " + url);
					e.printStackTrace();
				} 
				
			}
		}
		
		public void addToGlobalAsNamedList(String name) {
			addToGlobalFromURL(Utils.RES_URL + "/HouseLists/" + name + ".csv");
		}
		
		public void addToGlobalFromURL(String url) {
			House house;
			File file = new File(url);
			if (!file.exists()) {
				Logger.warn("File at " + url + " does not exist");
			} else if (!file.canRead()) {
				Logger.warn("File at " + url + " cannot be read, its probably in use");
			} else {
				try {
					BufferedReader reader = new BufferedReader(new FileReader(url));
					String row;
					reader.readLine();
					while ((row = reader.readLine()) != null) {
						String[] data = row.split(",");
						if (data[0].equals("Name")) {
							continue;
						} else {
							if (data[2].equals("NA")) {
								house = new House(data[0], Double.parseDouble(data[1]), true);
							} else {
								house = new House(data[0], Double.parseDouble(data[1]));
								house.loadImage(data[2]);
							}
							addToGlobal(house);
						}
					}
					reader.close();
				} catch (Exception e) {
					Logger.warn("Failed reading a item list - " + url);
					e.printStackTrace();
				} 
				
			}
		}
		
		public static void addToGlobal(House item) {
			global.add(item);
		}
		
		public static Optional<House> getFromGlobal(String houseName) {
			return global.stream().filter(house -> houseName.equals(house.getName())).findFirst();
		}
		
		public static void addToGlobal(Houses houses) {
			Houses temp = houses;
			temp.add(global);
			global = temp.houses;
		}
		
		public static void removeFromGlobal(House house) {
			Houses temp = new Houses(global);
			temp.remove(house);
			global = temp.houses;
		}
		
		public static void removeFromGlobal(Houses houses) {
			Houses temp = new Houses(global);
			temp.remove(houses);
			global = temp.houses;
		}
		
		public void add(Houses houses) {
			Iterator<House> itr = houses.houses.iterator();
			while (itr.hasNext()) {
				this.add(itr.next());
			}
		}
		
		public void remove(Houses houses) {
			Iterator<House> itr = houses.houses.iterator();
			while (itr.hasNext()) {
				this.remove(itr.next());
			}
		}

		public Houses() {
			houses = FXCollections.observableArrayList();
			global = FXCollections.observableArrayList();
		}

		public Optional<House> getHouse(String name) {
			return houses.stream().filter(house -> name.equals(house.getName())).findFirst();
		}

		public boolean remove(House house) {
			Optional<House> existingHouse = houses.stream().filter(h -> house.getName().equals(h.getName()))
					.findFirst();
			if (!existingHouse.isPresent()) {
				Logger.warn(
						"You tried to remove " + house.getName() + " but there wasn't one there");
				return false;
			} else {
				houses.remove(existingHouse.get());
				return true;
			}
		}

		public void add(House house) {
			houses.add(house);
		}
		
		public void add(ObservableList<House> list) {
			add(new Houses(list));
		}
		
		public void remove(ObservableList<House> list) {
			remove(new Houses(list));
		}
		
		public Houses(ObservableList<House> list) {
			houses = list;
		}
		
		public Houses(House house) {
			houses = FXCollections.observableArrayList();
			add(house);
		}
		
		public Houses(Houses houses) {
			this.houses = FXCollections.observableArrayList();
			add(houses);
			global = FXCollections.observableArrayList();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			java.util.Iterator<House> itr = houses.iterator();

			while (itr.hasNext()) {
				sb.append(itr.next().toString());
				sb.append("\n");
			}
			return sb.toString();
		}
		
		public static String globalToString() {
			StringBuilder sb = new StringBuilder();
			Iterator<House> itr = Houses.global.iterator();
			while (itr.hasNext()) {
				sb.append(itr.next().toString());
				sb.append("\n");
			}
			return sb.toString();
		}
	
}
