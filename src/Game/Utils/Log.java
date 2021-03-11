package Game.Utils;

import java.util.Calendar;
import java.util.Date;

public class Log implements Comparable<Log> {

	private Integer UID;
	private String level;
	private String message;
	private Date time;
	
	public String getLevel() {
		return level;
	}

	public String getMessage() {
		return message;
	}

	//NB: For convenience time is logged in the constructor -> Do not hold logging for a long time
	public Log(Integer uid, String level, String message) {
		this.UID = uid;
		this.level = level;
		this.message = message;
		this.time = Calendar.getInstance().getTime();
	}
	
	public String getTime() {
		return time.toString();
	}
	
	public String getUID() {
		return Integer.toString(UID);
	}
	
	public int getUIDn() {
		return UID;
	}

	@Override
	public int compareTo(Log log) {
		return (int) (this.UID - log.getUIDn());
	}
	
	
}
