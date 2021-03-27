package main.java.Game.Jobs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.Game.Character.Skills;
import main.java.Game.Utils.Logger;
import main.java.Game.Utils.Utils;

public class Jobs {

	public static ArrayList<Job> globalJobs = new ArrayList<Job>();;
	public ArrayList<Job> jobs;
	public String name;
	
	public void getJobsFromURL(String url) {
		File file = new File(url);
		if (!file.exists()) {
			Logger.warn("File at " + url + " does not exist");
		} else if (!file.canRead()) {
			Logger.warn("File at " + url + " cannot be read, its probably in use");
		} else {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(url));
				String row;
				String[] colHeadings = reader.readLine().split(",");
				while ((row = reader.readLine()) != null) {
					String[] data = row.split(",");
					int length = data.length;
					Job job = new Job();
					Skills skills = new Skills();
					for (int i = 0; i < length; i++) {
						if (colHeadings[i].equals("Name")) {
							job.setName(data[i]);
						} else if (colHeadings[i].equals("Wage")) {
							job.setWage(Double.parseDouble(data[i]));
						} else if (colHeadings[i].equals(Skills.INTELLIGENCE)) {
							skills.addIntelligence(Integer.parseInt(data[i]));
						} else if (colHeadings[i].equals(Skills.STRENGTH)) {
							skills.addStrength(Integer.parseInt(data[i]));
						} else if (colHeadings[i].equals(Skills.HUMOUR)) {
							skills.addHumour(Integer.parseInt(data[i]));
						} else if (colHeadings[i].equals(Skills.CARDIOVASCULAR)) {
							skills.addCardiovascular(Integer.parseInt(data[i]));
						}
					}
					job.setReqSkills(skills);
					jobs.add(job);
				}
			} catch (Exception e) {
				Logger.warn("Failed reading a item list - " + url);
				e.printStackTrace();
			} 
		}
	}
	
	public void add(Job job) {
		//To Do: Maybe avoid duplication
		jobs.add(job);
	}
	
	//To Do: Add a remove method
	
	public Jobs() {
		this.name = "Unnamed jobs list";
		jobs = new ArrayList<Job>();
	}
	
	public Jobs(String name) {
		this.name = name;
		jobs = new ArrayList<Job>();
	}
	
	public void getJobsFromNamedList(String name) {
		getJobsFromURL(Utils.RES_URL + "/JobLists/" + name + ".csv");
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Job> itr = jobs.iterator();
		while (itr.hasNext()) {
			Job job = itr.next();
			sb.append(job.getJobName() + " - £" + job.getPay() + " - " + job.getSkills().toString());
		}
		return sb.toString();
	}
	
	public static String globalToString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Job> itr = globalJobs.iterator();
		while (itr.hasNext()) {
			Job job = itr.next();
			sb.append(job.getJobName() + " - £" + job.getPay() + " - " + job.getSkills().toString());
		}
		return sb.toString();
	}
	
	
}
