package welcome;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler 
{
	

	public Connection establishconnection() throws ClassNotFoundException, SQLException, IOException 
	{
		FileInputStream fis=new FileInputStream("database.properties");
		Properties properties=new Properties();
		properties.load(fis);
		Class.forName(properties.getProperty("DB_DRIVER_CLASS"));
		Connection con=DriverManager.getConnection(properties.getProperty("DB_URL"),properties.getProperty("DB_USERNAME"),properties.getProperty("DB_PASSWORD"));
		
		return con;
	}

}
