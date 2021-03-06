package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
		
	private static final String dbUser = "root";
    private static final String dbPassword = "Quinnie17!!";
    private static final String dbDatabase = "lineup";
    static Connection conn = null;
	
    private DBConnection() {
    }
  
    //access singleton db and instantiate
    public static Connection getConnectionToDatabase() {
    	if (conn != null) {
    		return conn;
    	}
    	return getConnectionToDatabase(dbDatabase, dbUser, dbPassword);
    }
 
    //private for singleton design pattern
	private static Connection getConnectionToDatabase(String dbDatabase, String dbUser, String dbPassword) {

		try {
			// load the driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL JDBC Driver Registered!");

			// get hold of the DriverManager
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lineup", dbUser, dbPassword);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
		} 
        
        catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();

		}
		if (conn != null) {
			System.out.println("Connection made to DB!");
		}

        return conn;
		
	}

}
