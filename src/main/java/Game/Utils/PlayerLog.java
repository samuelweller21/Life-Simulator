package main.java.Game.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import main.java.UI.controllers.MainController;

public class PlayerLog {

	public static TreeMap<Integer, Log> log = new TreeMap<Integer, Log>();

	public static Integer uid = 0;

	public static StringProperty stringLog = new SimpleStringProperty();

	public static MainController mc;

	public static void init(MainController l_mc) {
		PlayerLog.mc = l_mc;
	}

	public static void log(String message) {
		PlayerLog.log.put(uid, new Log(uid, "Player Log", message));
		PlayerLog.uid++;
		PlayerLog.updateBinding();
	}

	public static void updateBinding() {
		PlayerLog.stringLog.set(getPlayerText());
	}

	// NB This is very slow. If logs get long we will want to do an incremental
	// system
	public static String getPlayerText() {

		StringBuilder sb = new StringBuilder();
		NavigableMap<Integer, Log> desc = PlayerLog.log.descendingMap();
		Iterator<Entry<Integer, Log>> itr = desc.entrySet().iterator();
		
		while (itr.hasNext()) {
			Entry<Integer, Log> entry = itr.next();
			Log line = (Log) entry.getValue();
			sb.append(entry.getKey() + " : " + line.getMessage() + " at " + line.getTime());
			sb.append("\n");
		}

		return sb.toString();

	}
	
}
