package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.sqlite.SQLiteDataSource;
import javafx.beans.property.SimpleListProperty;

public class SQLConnection {

	public SQLiteDataSource ds = null;
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
		Connection conn = null;
		try {
			
	        try {
	            ds = new SQLiteDataSource();
	            ds.setUrl("jdbc:sqlite:appDB.db");
	        } catch ( Exception e ) {
	            e.printStackTrace();
	            System.exit(0);
	        }
	        
	        System.out.println( "Opened database successfully" );

	        
	        
	        try{
	        	conn = ds.getConnection();
	        } catch ( SQLException e ) {
	            e.printStackTrace();
	            System.exit( 0 );
	        }

	        System.out.println( "Created database successfully" );

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
		Connection conn =  null;
		
		try {
			rowListProperty = Main.model.rowListProperty();
			String query = "select * from TABLE";
			conn = getConnection();
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
		getConnection();
		clearDB();
		initTable();
		insertToTable();
		
		//initTableB();
		//initTableC();
	}
	
	public void initTable() {
		Connection conn = null;
		
		try {
			String query = "CREATE TABLE IF NOT EXISTS Test(" +
						"  primary_key INTEGER PRIMARY KEY," +
						"  type VARCHAR(25) NOT NULL, name VARCHAR(25) NOT NULL" +
						");";
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void insertToTable() {
		Connection conn = null;
		String type = "VALUE";
		String name = "STUFF";
		
		try {
			String query = "INSERT INTO Test(type , name)" +
					"  VALUES('" + type + "', '" + name + "')";
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			
			query = "select * from Test WHERE type='"+ type +"' AND "
					+ "name='" + name + "';";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
				System.out.println("The Row: " + rs.getInt("primary_key") + rs.getString("type") + rs.getString("name"));
			
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void clearDB() {
		Connection conn = null;
		
		try {
			String query = "DROP TABLE IF EXISTS Test;";
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);

			conn.close();
		}catch(Exception e) {
			System.out.println("DROP TABLE(S) FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}

}
