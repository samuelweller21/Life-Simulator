package main.java.Game.Utils;

import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import main.java.Game.Activities.Activity;
import main.java.Game.Character.SkillsTable;
import main.java.Game.Jobs.Job;

public class Utils {

	public static final int UNINITIATED_MEMORY = 0;
	public static final String RES_URL = "src/main/resources";
	private static final double WAGE_CONST = 5;
	private static final double WAGE_FACTOR = 0.03;
	public static SoundSystem ss = new SoundSystem();

	public static int[] arrayRandomTotalAllocation(int length, int total) {
		// Returns an integer array of length length with randomly assigned integers
		// totalling total
		Random r = new Random();
		DoubleStream sDouble = r.doubles(length - 1);
		double[] DoubleN = sDouble.toArray();
		double dRunningTotal = 0;
		for (int i = 0; i < DoubleN.length; i++) {
			dRunningTotal += DoubleN[i];
		}
		for (int i = 0; i < DoubleN.length; i++) {
			DoubleN[i] = DoubleN[i] / dRunningTotal * (((double) (length - 1)) / length) * total;
		}
		int[] IntN = new int[length];
		int runningTotal = 0;
		for (int i = 0; i < IntN.length - 1; i++) {
			IntN[i] = (int) Math.round(DoubleN[i]);
			runningTotal += IntN[i];
		}

		IntN[length - 1] = total - runningTotal;
		return IntN;
	}
	
	public static double getJobWage(Job job) {
		//Wages should grow exponentially.  Assume wage = WAGE_CONST + WAGE_FACTOR*exp(sum(reqSkills))
		int sum = 0;
		Iterator itr = job.getReqSkills().getSkills().keySet().iterator();
		while (itr.hasNext()) {
			sum += job.getReqSkills().getSkills().get(itr.next());
		}
		return WAGE_CONST + Math.exp(WAGE_FACTOR*sum);
	}

	public static String formatAsPrice(Double price) {
		DecimalFormat formatter = new DecimalFormat("0.00");
		return formatter.format(price);
	}
	
	public static void addActivityToLabel(Label label, Activity activity) {
		StringBuilder sb = new StringBuilder();
		sb.append(activity.getActivityName() + "\n");
		sb.append("Cost: £" + activity.getPrice() + "\n");
		sb.append("Duration: " + activity.getTime() + " minutes \n");
		Iterator<SkillsTable> itr = activity.skillsToAdd.skillsProp.iterator();
		while (itr.hasNext()) {
			SkillsTable skill = itr.next();
			if (Integer.parseInt(skill.getValue()) != 0) {
				sb.append(skill.getSkill() + ": " + skill.getValue() + "\n");
			} 
		}
		label.setText(sb.toString());
		GridPane.setHalignment(label, HPos.RIGHT);
	}
	
	public static String largePriceFormatter(double price) {
		if (price == 0) {
			return "0";
		}
		DecimalFormat formatter = new DecimalFormat("00,000,000");
		String s = formatter.format(price);
		//Remove leading zeroes
		while ((s.substring(0,1).equals("0")) || (s.substring(0,1).equals(","))) {
			s = s.substring(1, s.length());
		}
		return s;
	}
	
	public static String removeWAV(String file) {
		int sub = file.lastIndexOf("/");
		file = file.substring(sub+1, file.length());
		return file.substring(0,file.length() - 4);
	}
	
	public static List<Path> getPathsFromResourceJAR(String folder) {
		System.out.println(folder);
		List<Path> paths = null;
		try {
			String jarPath = Utils.class.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			URI uri = URI.create("jar:file" + jarPath);
			FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap());
			paths = Files.walk(fs.getPath(folder)).filter(Files::isRegularFile).collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Problem with JAR Navigator");
		}
		return paths;
	}
	
}
