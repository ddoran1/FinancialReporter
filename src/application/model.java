package application;

import java.text.ParseException;
import java.util.ArrayList;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class model {
	private SimpleListProperty<row> rowListProperty;
	public int temp_PK = 0; //acts as counter for the moment, but will be replaced sql command
	
	public model() {
		ArrayList<row> rowList = new ArrayList<row>();
		ObservableList<row> observableList = (ObservableList<row>) FXCollections.observableArrayList(rowList);
		rowListProperty = new SimpleListProperty<row>(observableList);
	}

	public SimpleListProperty<row> rowListProperty() {
		return rowListProperty;
	}
	
		//INSERT OBJECT TYPE
		public void addRow0(/*attributes to go here*/) throws ParseException {
			row newRow = new row(/*attributes to go here*/ temp_PK);
			temp_PK++;
			rowListProperty.add(newRow);
			Main.sqlConn.insertRow(/*attributes to go here*/);
		}

		//INSERT OBJECT TYPE
		public void addRow1(/*attributes to go here*/) throws ParseException {
			row newRow = new row(/*attributes to go here*/ temp_PK);
			temp_PK++;
			rowListProperty.add(newRow);
			Main.sqlConn.insertRow(/*attributes to go here*/);
		}

		//INSERT OBJECT TYPE
		public void addRow2(/*attributes to go here*/) throws ParseException {
			row newRow = new row(/*attributes to go here*/ temp_PK);
			temp_PK++;
			rowListProperty.add(newRow);
			Main.sqlConn.insertRow(/*attributes to go here*/);
		}

		public void deleteRow(row row) {
			for(row r: rowListProperty) {
				if(r.getPK() == row.getPK()) {
					rowListProperty.remove(r);
					//Main.model.removeItem(r); --->>> This would be for another model if needed
					Main.sqlConn.deleteRow(row.getPK(),row.getType());
					break;
				}
			}
		}
}
