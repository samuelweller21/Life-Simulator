package main.java.UI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class AboutPageController {
	@FXML
	private TextArea aboutText;
	
	@FXML
	public Pane aboutPane;
	
	public MainController mc;
	
	
	public void init(MainController mc) {
		this.mc = mc;
		
		//Fill about text
		StringBuilder sb = new StringBuilder();
		sb.append("Life Simulator is a game/program created using JavaFX and scene builder" + "\n");
		sb.append("It demonstrates many principles such as: " + "\n");
		sb.append("- Nodes e.g. labels, buttons, images, tablesview, menus, " + "\n");
		sb.append("- Controller oriented programming style" + "\n");
		sb.append("- UI multi threading" + "\n");
		sb.append("- Property binding to observable items" + "\n");
		sb.append("- Abstraction of components" + "\n");
		sb.append("- Multi-monitor handling" + "\n");
		sb.append("- Basic styling in CSS" + "\n");
		sb.append("- FX Graphs with a semi-realistic stock BM market" + "\n");
		sb.append("- Sound system" + "\n");
		sb.append("- Keyboard shortcuts and tooltips" + "\n");
		sb.append("\n");
		sb.append("You can find the source code at: " + "https://github.com/samuelweller21/Life-Simulator" + "\n");
		sb.append("See www.samuelweller.com for more" + "\n");
		aboutText.setText(sb.toString());
	}

}
