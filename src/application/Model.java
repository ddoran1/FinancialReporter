package application;

import java.text.ParseException;
import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	private static String budget = "1000.00";
	private SimpleListProperty<Row> rowListProperty;
	public int temp_PK = 0; // acts as counter for the moment, but will be replaced sql command

	public Model() {
		ArrayList<Row> rowList = new ArrayList<Row>();
		ObservableList<Row> observableList = (ObservableList<Row>) FXCollections.observableArrayList(rowList);
		rowListProperty = new SimpleListProperty<Row>(observableList);
	}

	public SimpleListProperty<Row> rowListProperty() {
		return rowListProperty;
	}

	// Expenses
	public void addRow0(/* attributes to go here */) throws ParseException {
		Row newRow = new Row(/* attributes to go here */ temp_PK);
		temp_PK++;
		rowListProperty.add(newRow);
		Main.sqlConn.insertRow(/* attributes to go here */);
	}

	// Earnings
	public void addRow1(/* attributes to go here */) throws ParseException {
		Row newRow = new Row(/* attributes to go here */ temp_PK);
		temp_PK++;
		rowListProperty.add(newRow);
		Main.sqlConn.insertRow(/* attributes to go here */);
	}

	// Debts
	public void addRow2(/* attributes to go here */) throws ParseException {
		Row newRow = new Row(/* attributes to go here */ temp_PK);
		temp_PK++;
		rowListProperty.add(newRow);
		Main.sqlConn.insertRow(/* attributes to go here */);
	}

	public void deleteRow(Row row) {
		for (Row r : rowListProperty) {
			if (r.getPK() == row.getPK()) {
				rowListProperty.remove(r);
				// Main.model.removeItem(r); --->>> This would be for another model if needed
				Main.sqlConn.deleteRow(row.getPK(), row.getType());
				break;
			}
		}
	}

	public static String getBudget() {
		return budget;
	}

	public static void setBudget(String budget) {
		Model.budget = budget;
	}
}
