package Game.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import UI.controllers.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Logger {

	public static TreeMap<Integer, Log> log = new TreeMap<Integer, Log>();

	public static Integer uid = 0;

	public static final String WARN = "Warn", ERROR = "Error", INFO = "Info", UNKNOWN = "Unknown Log Type",
			INITIALISE = "Initialise", UI = "UI";

	public static StringProperty stringLog = new SimpleStringProperty();

	public static MainController mc;

	public static void init(MainController l_mc) {
		Logger.mc = l_mc;
	}

	public static void log(String message) {
		Logger.log.put(uid, new Log(uid, UNKNOWN, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void log(String level, String message) {
		Logger.log.put(uid, new Log(uid, level, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void warn(String message) {
		Logger.log.put(uid, new Log(uid, WARN, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void error(String message) {
		Logger.log.put(uid, new Log(uid, ERROR, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void initialise(String message) {
		Logger.log.put(uid, new Log(uid, INITIALISE, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void info(String message) {
		Logger.log.put(uid, new Log(uid, INFO, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void ui(String message) {
		Logger.log.put(uid, new Log(uid, UI, message));
		Logger.uid++;
		Logger.updateBinding();
	}

	public static void updateBinding() {
		Logger.stringLog.set(getDebugText());
	}

	// NB This is very slow. If logs get long we will want to do an incremental
	// system
	public static String getDebugText() {

		StringBuilder sb = new StringBuilder();
		NavigableMap desc = Logger.log.descendingMap();
		Iterator<Entry<Integer, Log>> itr = desc.entrySet().iterator();

		// Form a list of acceptable levels
		ArrayList<String> whitelist = new ArrayList<String>();
		if (mc.debugPaneController.errorRadio.isSelected()) {
			whitelist.add(ERROR);
		}
		if (mc.debugPaneController.warnRadio.isSelected()) {
			whitelist.add(WARN);
		}
		if (mc.debugPaneController.infoRadio.isSelected()) {
			whitelist.add(INFO);
		}
		if (mc.debugPaneController.unknownRadio.isSelected()) {
			whitelist.add(UNKNOWN);
		}
		if (mc.debugPaneController.initRadio.isSelected()) {
			whitelist.add(INITIALISE);
		}
		if (mc.debugPaneController.uiRadio.isSelected()) {
			whitelist.add(UI);
		}

		// Push whitelisted messages
		while (itr.hasNext()) {
			Map.Entry entry = itr.next();
			Log line = (Log) entry.getValue();
			if (whitelist.contains(line.getLevel())) {
				sb.append(
						entry.getKey() + " : " + line.getLevel() + " : " + line.getMessage() + " at " + line.getTime());
				sb.append("\n");
			}
		}

		return sb.toString();

	}

}
