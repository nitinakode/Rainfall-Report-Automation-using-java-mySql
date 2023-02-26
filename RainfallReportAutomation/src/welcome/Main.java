package welcome;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException 
	{
		RainfallReport rfr=new RainfallReport();
		List<Annualrainfall> ar=rfr.generateRainfallReport("rainfall.txt");
		DBHandler d=new DBHandler();
		Connection con=d.establishconnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO annualrainfall values(?,?,?)");
		for(Annualrainfall a:ar) {
			ps.setInt(1, a.getCityPincode());
			ps.setString(2, a.getCityName());
			ps.setDouble(3, a.getAverageAnnualRainfall());
			ps.executeUpdate();
		}
		List<Annualrainfall> result=rfr.findMaximumRainfallCities();
		System.out.println("The following are the cities  which having Maximum Average Rainfall: ");
		System.out.println("CityPincode"+"   "+"CityName"+"   "+"CityAverageRainfall");
		for(Annualrainfall a: result) {
			System.out.println(a.getCityPincode()+"         "+a.getCityName()+"         "+a.getAverageAnnualRainfall());
		// TODO Auto-generated method stub

	}

}
}
