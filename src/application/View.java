package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class View extends BorderPane {
//	private final ToggleButton list = new ToggleButton("Borrower List");
//	private final ToggleButton graph = new ToggleButton("Borrower Graph");
//	MenuBar menuBar = new MenuBar();
//	private Menu help = new Menu("Help");
//	private Menu back = new Menu("Back");
//	private MenuItem mainMenu = new MenuItem("Main Menu");
//	private MenuItem aboutUs = new MenuItem("About Us");
//	private MenuItem howTo = new MenuItem("How To");
//	private ToolBar toolbar = new ToolBar();
//	private enum State {TABLE, GRAPH};
//	private State state = State.TABLE;
//	private VBox box = new VBox();
	
	private String budget = Model.getBudget();
	private Text budget_text = new Text();

	private VBox box = new VBox();

	public View() {
		pageSetUp();
	}

	private void pageSetUp() {
		budget_text.setFont(new Font(20));
		budget_text.setText("Net Monthly Earnings:  $" + budget);
		box.getChildren().addAll(budget_text);
		
		this.setTop(box);
	}
	
}
