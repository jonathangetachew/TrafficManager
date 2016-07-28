package www.jdtech.databaseManager;

public class Configuration {
	
	private static String m_serverAddress = "localhost";
	private static int m_serverPort = 1433;
	private static String m_databaseName = "Traffic";
	private static String m_driverTable = "DRIVER";
	
	public static String getServerAddress() {
		return m_serverAddress;
	}
	
	public static void setServerAdress(String ServerAddress) {
		m_serverAddress = ServerAddress;
	}
	
	public static int getServerPort() {
		return m_serverPort;
	}
	
	public static void setServerPort(int serverPort) {
		m_serverPort = serverPort;
	}
	
	public static String getDatabaseName() {
		return m_databaseName;
	}
	
	public static void setDatabaseName(String databaseName) {
		m_databaseName = databaseName;
	}
	
	public static String getDriverTable() {
		return m_driverTable;
	}
	
	public static void setDriverTable(String driverTable) {
		m_driverTable = driverTable;
	}	
	
}
