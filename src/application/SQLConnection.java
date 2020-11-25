package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.beans.property.SimpleListProperty;

public class SQLConnection {
	
	public Connection conn = null;
	public static PreparedStatement pst = null;
	public static ResultSet rs = null;
	private SimpleListProperty<Row> rowListProperty;

	public SQLConnection() {
		try {
			init();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			conn = null;
			//EXPERIMENTAL
			//Class.forName("org.sqlite.JDBC");
			//conn = DriverManager.getConnection("jdbc:sqlite::resource:file:Library/ListView.db");

			//WORKING
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:ListView.db");
			return conn;
		}catch(Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	public void populate() {
		/*
		 * A method that will populate the fields after drawing from the DB
		 * 
		 * this.populateFromTableName();
		 */
	}
	
	public void populateFromTableName() {
		try {
			rowListProperty = Main.model.rowListProperty();
			String query = "select * from TABLE";
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
//			while(rs.next()){
//			    Row newRow = new Row(rs.getString("columnName"));
//			    rowListProperty.add(newRow);
//			}
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE POPULATE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}

	public void insertRow() {
		// TODO Auto-generated method stub
		
	}

	public void deleteRow(Object pk, Object type) {
		// TODO Auto-generated method stub
		
	}
	
	public void init() throws ClassNotFoundException {
		//getConnection();
		//initTableA();
		//initTableB();
		//initTableC();
	}
	
	public void initTable() {
//		try {
//			String query = "CREATE TABLE IF NOT EXISTS tableName(" +
//						"  primary_key INTEGER PRIMARY KEY," +
//						"  type VARCHAR(25) NOT NULL, name VARCHAR(25) NOT NULL" +
//						");";
//			
//			Connection conn = getConnection();
//			Statement statement = conn.createStatement();
//			statement.executeUpdate(query);
//			conn.close();
//		}catch(Exception e) {
//			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
//			e.printStackTrace();
//		}
	}

}
