package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import main.java.Game.Character.Player;
import main.java.Game.Jobs.Job;
import main.java.Game.Jobs.Jobs;
import main.java.Game.Utils.Clock;
import main.java.Game.Utils.Logger;

public class WorkPaneController {

	// Components
	@FXML
	Pane workPane;
	@FXML
	Label jobLabel, payLabel, work_jobLabel, work_payLabel;
	@FXML 
	TableView jobsTable;
	@FXML
	Button applyButton;
	
	@FXML
	HomepageController homepageController;
	
	private Jobs jobsList;
	public TableColumn nameColumn, wageColumn, intelligenceColumn, strengthColumn, humourColumn, cardiovascularColumn;
	
	public void apply() {
		Job job = null;
		try {
			job = (Job) jobsTable.getSelectionModel().getSelectedItem();
		} catch (Exception e) {
			Logger.warn("Could not cast job applied for as a job");
			e.printStackTrace();
		}
		if (job == null) {
			Logger.warn("The job you are applying for is null");
		} else {
			Logger.log("Applying for :" + jobsTable.getSelectionModel().getSelectedItem().toString());
			//To Do: Actual decide if someone can have a job and then assign it to them
			if (Player.skills.isSufficient(job.getReqSkills())) {
				Player.setJob(job);
			} else {
				//Set up info pane and say you don't have the required skills
				Logger.player("You do not have the required skills for this job");
			}
		}
	}

	public void init(HomepageController homepageController) {
		Logger.initialise("Initializing hompage controller in workpane controller");
		this.homepageController = homepageController;
		
		//Initialise bindings of jobs
		jobLabel.textProperty().bind(Player.job.name);
		payLabel.textProperty().bind(Player.job.wage);
		
		//Initialise jobs list
		jobsList = new Jobs();
		jobsList.getJobsFromNamedList("Jobs");
		Jobs.addToGlobal(jobsList);
		
		GridPane.setHalignment(work_jobLabel, HPos.RIGHT);
		GridPane.setHalignment(work_payLabel, HPos.RIGHT);
		
		// Init columns
		nameColumn = new TableColumn("Name");
		nameColumn.setSortable(false);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		wageColumn = new TableColumn("Wage");
		wageColumn.setSortable(false);
		wageColumn.setCellValueFactory(new PropertyValueFactory<>("wage"));
		
		intelligenceColumn = new TableColumn("Intelligence");
		intelligenceColumn.setSortable(false);
		intelligenceColumn.setCellValueFactory(new PropertyValueFactory<>("intelligence"));
		
		strengthColumn = new TableColumn("Strength");
		strengthColumn.setSortable(false);
		strengthColumn.setCellValueFactory(new PropertyValueFactory<>("strength"));
		
		humourColumn = new TableColumn("Humour");
		humourColumn.setSortable(false);
		humourColumn.setCellValueFactory(new PropertyValueFactory<>("humour"));
		
		cardiovascularColumn = new TableColumn("Cardiovascular");
		cardiovascularColumn.setSortable(false);
		cardiovascularColumn.setCellValueFactory(new PropertyValueFactory<>("cardiovascular"));
		
		jobsTable.setItems(Jobs.globalJobs);
		jobsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		jobsTable.getColumns().addAll(nameColumn, wageColumn, cardiovascularColumn, humourColumn, intelligenceColumn, strengthColumn);
		jobsTable.setFocusTraversable(false);
		
	}

	public void work() {
		Player.work(Clock.toTime(1, 0));
	}

}
