package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		double amt = 75.56;
		//DATE FORMAT  ===>>>  YYYY-MM-DD
		insertIntoExpenses("Phone", amt, "Verizon cell service", "2020-11-30", "2020-11-28");
		amt = 75500.78;
		insertIntoExpenses("Tesla", amt, "Verizon cell service", "2020-11-30", "2020-11-28");
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
		initExpenses();
		populate();
		viewExpensesTable();
		
		//initTableB();
		//initTableC();
	}
	
	public void initExpenses() {
		Connection conn = null;
		try {
			String query = getQuery("src\\application\\SQLQueries\\createExpensesTable.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void initEarnings() {
		Connection conn = null;
		try {
			String query = getQuery("src\\application\\SQLQueries\\createEarningsTable.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("CREATE TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void insertIntoExpenses(String name, double expense, String description, String datePaid, String dateAcquired) {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\application\\SQLQueries\\insertIntoExpenses.sql");
			query = query.replace("$name", name);
			query = query.replace("$expense", Double.toString(expense));
			query = query.replace("$description", description);
			query = query.replace("$datePaid", datePaid);
			query = query.replace("$dateAcquired", dateAcquired);
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);	
			conn.close();
		}catch(Exception e) {
			System.out.println("INSERT FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void viewExpensesTable() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\application\\SQLQueries\\viewExpensesTable.sql");
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			System.out.print("\nExpenses Table:  \n");
			
			while(rs.next()){
					System.out.print("\tprimary_key = " + rs.getString("primary_key") + "\t");
					System.out.print("\tname  = " + rs.getString("name") + "\t");
					System.out.print("\texpense = " + rs.getString("expense") + "\t");
					System.out.print("\tdescription = " + rs.getString("description") + "\t");
					System.out.print("\tdatePaid = " + rs.getString("datePaid") + "\t");
					System.out.print("\tdateAcquired = " + rs.getString("dateAcquired") + "\t");
					System.out.print("\n");
	        }
			conn.close();
		}catch(Exception e) {
			System.out.println("TABLE VIEW FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void insertIntoEarnings(String name, double expense, String description, String datePaid, String dateAcquired) {
		Connection conn = null;
		
		try {
			String query = "INSERT INTO Test(name , expense, description, datePaid, dateAcquired)" +
					"  VALUES('" + name + "', '" + expense + "', '" + description + "', '" + datePaid + "', '" + dateAcquired + "')";
			
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);
			conn.close();
		}catch(Exception e) {
			System.out.println("INSERT INTO TABLE FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public void clearDB() {
		Connection conn = null;
		
		try {
			String query = getQuery("src\\application\\SQLQueries\\dropExpenses.sql");
			conn = getConnection();
			Statement statement = conn.createStatement();
			statement.executeUpdate(query);

			conn.close();
		}catch(Exception e) {
			System.out.println("DROP TABLE(S) FAILED" + "\n\tconn = " + conn);
			e.printStackTrace();
		}
	}
	
	public String getQuery(String filePath) {
		String query = "";
	    try{
	        BufferedReader buffer = new BufferedReader(new FileReader(filePath));
	        StringBuilder sb = new StringBuilder();
	        String line = "";
	        while((line = buffer.readLine()) != null) {
	            sb.append(line);
	        }
	        query = sb.toString();
	        buffer.close();
	        
	        
	    }
	    catch(FileNotFoundException e){
	        e.printStackTrace();
	        //Prints out file names at given point in directory
//	        File file = new File(".");
//	        for(String fileNames : file.list()) System.out.println(fileNames);
	    }
	    catch(IOException e){
	        e.printStackTrace();
	    }
	    
	    return query;
	}

}
