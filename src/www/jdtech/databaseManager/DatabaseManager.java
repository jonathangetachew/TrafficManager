package www.jdtech.databaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import www.jdtech.gui.AlertWindow;


public class DatabaseManager {
	
	private static Connection m_connection;
	private static PreparedStatement m_prepareStmt;

    // Method to get data from database
    public static ArrayList<String> retrieveDrivers() {
    	ArrayList<String> databaseContent = new ArrayList<String>();    	
		 
    	try {
			
    		m_connection = DriverManager.getConnection("jdbc:sqlserver://" + Configuration.getServerAddress() + ":" + Configuration.getServerPort() + ";" +
														"databaseName=" + Configuration.getDatabaseName() + ";integratedSecurity=true;");
    		m_prepareStmt = m_connection.prepareStatement("SELECT CONCAT(FirstName, ' ', MiddleName) FROM " + Configuration.getDriverTable());

			ResultSet rs = m_prepareStmt.executeQuery();
							
			while(rs.next()) {
				
				databaseContent.add(rs.getString(1).toUpperCase());								
				
			}

			// Closing opened connection
			m_prepareStmt.close();			
			if(m_connection != null) m_connection.close();

		} catch (SQLException e) {
			AlertWindow.display("Unable To Open Database", e.getLocalizedMessage());
			e.printStackTrace();
		}
    	
		return databaseContent;
    }
    
    ///> Method to search by ID of Driver
    public static ArrayList<String> searchByID(int id) {
    	ArrayList<String> databaseContent = new ArrayList<String>();    	
		 
    	try {
			
    		m_connection = DriverManager.getConnection("jdbc:sqlserver://" + Configuration.getServerAddress() + ":" + Configuration.getServerPort() + ";" +
														"databaseName=" + Configuration.getDatabaseName() + ";integratedSecurity=true;");
    		m_prepareStmt = m_connection.prepareStatement("SELECT CONCAT(FirstName, ' ', MiddleName, ' ', LastName), Gender, birthDate, City, Sub_City, Kebele, Point "
    															+ "FROM " + Configuration.getDriverTable() + 
    															" WHERE DriverID=" + id);

			ResultSet rs = m_prepareStmt.executeQuery();
							
			while(rs.next()) {
				databaseContent.add(rs.getString(1));
				databaseContent.add(rs.getString(2));
				databaseContent.add(rs.getString(3));
				databaseContent.add(rs.getString(4));
				databaseContent.add(rs.getString(5));
				databaseContent.add(rs.getString(6));
				databaseContent.add(rs.getString(7));				
			}

			// Closing opened connection
			m_prepareStmt.close();			
			if(m_connection != null) m_connection.close();

		} catch (SQLException e) {
			AlertWindow.display("Unable To Open Database", e.getLocalizedMessage());
			e.printStackTrace();
		}
		return databaseContent;
    }
    
    ///> Method to search drivers by name
    public static ArrayList<String> searchByName(String name) {
    	
    	name = "%" + name + "%";
    	
    	ArrayList<String> databaseContent = new ArrayList<String>();    	
		 
    	try {
			
    		m_connection = DriverManager.getConnection("jdbc:sqlserver://" + Configuration.getServerAddress() + ":" + Configuration.getServerPort() + ";" +
														"databaseName=" + Configuration.getDatabaseName() + ";integratedSecurity=true;");
    		m_prepareStmt = m_connection.prepareStatement("SELECT CONCAT(FirstName, ' ', MiddleName, ' ', LastName), Gender, birthDate, City, Sub_City, Kebele, Point, DriverID "
    															+ "FROM " + Configuration.getDriverTable() + 
    															" WHERE CONCAT(FirstName, ' ', MiddleName, ' ', LastName) LIKE '" + name +"'" );

			ResultSet rs = m_prepareStmt.executeQuery();
							
			while(rs.next()) {
				databaseContent.add(rs.getString(1));
				databaseContent.add(rs.getString(2));
				databaseContent.add(rs.getString(3));
				databaseContent.add(rs.getString(4));
				databaseContent.add(rs.getString(5));
				databaseContent.add(rs.getString(6));
				databaseContent.add(rs.getString(7));
				databaseContent.add(rs.getString(8));
			}

			// Closing opened connection
			m_prepareStmt.close();			
			if(m_connection != null) m_connection.close();

		} catch (SQLException e) {
			AlertWindow.display("Unable To Open Database", e.getLocalizedMessage());
			e.printStackTrace();
		}
    	if(!databaseContent.isEmpty()) {
    		return databaseContent;
    	} else {
    		return null;
    	}
    }
    
    ///> Method to update points
    public static void updatePoints(int amount, int id) {

    	try {
    		int point = getPoints(id);
    		
    		m_connection = DriverManager.getConnection("jdbc:sqlserver://" + Configuration.getServerAddress() + ":" + Configuration.getServerPort() + ";" +
					"databaseName=" + Configuration.getDatabaseName() + ";integratedSecurity=true;");
    		m_prepareStmt = m_connection.prepareStatement("UPDATE " + Configuration.getDriverTable() + " SET Point = ? WHERE DriverID = " + id);
    		m_prepareStmt.setInt(1, (point + amount));
    		m_prepareStmt.executeUpdate();
			
    		AlertWindow.display("Success", "Punished Successfully!");
    		
			// Closing opened processes
			m_prepareStmt.close();			
			if(m_connection != null) m_connection.close();			
			
		} catch (SQLException e) {
			AlertWindow.display("Unable To Open Database", e.getLocalizedMessage());
			e.printStackTrace();
		}
    }

    ///> method to get the current points of a driver
	private static int getPoints(int id) {
		int points = 0;    	
		 
    	try {
			
    		m_connection = DriverManager.getConnection("jdbc:sqlserver://" + Configuration.getServerAddress() + ":" + Configuration.getServerPort() + ";" +
														"databaseName=" + Configuration.getDatabaseName() + ";integratedSecurity=true;");
    		m_prepareStmt = m_connection.prepareStatement("SELECT Point FROM " + Configuration.getDriverTable() + " WHERE DriverID = " + id);

			ResultSet rs = m_prepareStmt.executeQuery();
			
			while(rs.next()) {
				points = rs.getInt(1);		
			}
			// Closing opened connection
			m_prepareStmt.close();			
			if(m_connection != null) m_connection.close();

		} catch (SQLException e) {
			AlertWindow.display("Unable To Open Database", e.getLocalizedMessage());
			e.printStackTrace();
		}
    	
		return points;
	}

    
}