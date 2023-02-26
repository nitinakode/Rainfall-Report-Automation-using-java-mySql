package welcome;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RainfallReport 
{
	List<Annualrainfall>generateRainfallReport(String filePath) throws FileNotFoundException
	{
		List<Annualrainfall>report=new ArrayList<Annualrainfall>();
		Scanner s = new Scanner (new BufferedReader(new FileReader(filePath)));
		 String line="";
		 while(s.hasNext() && (line=s.nextLine())!=null) 
		 {
			 String[] arr=line.split(",");
				if(validate(arr[0])) {
					Annualrainfall arf=new Annualrainfall();
					arf.setCityPincode(Integer.parseInt(arr[0]));
					arf.setCityName(arr[1]);
					double[] monthlyRainfall=new double[arr.length-2];
					int j=0;
					for(int i=2;i<arr.length;i++) {
						monthlyRainfall[j++]=Double.parseDouble(arr[i]);
					}
					arf.calculateAverageAnnualRainfall(monthlyRainfall);
					report.add(arf);	
				}else {
					try {
						throw new Exception("Invalid Pincode");
					}catch(Exception e) {
						System.out.println(arr[0]+" is "+e.getMessage());
					}
				}
			}
			
			s.close();
		return report;
			 
		 
	
		
		
		
	}
	boolean validate(String	cityPincode)
	{
		if(cityPincode.length()==5) {
			return true;
		}
		return false;
		
	}
	List<Annualrainfall>findMaximumRainfallCities () throws ClassNotFoundException, SQLException, IOException
	{ArrayList<Annualrainfall> maxList=new ArrayList<>();
	DBHandler d=new DBHandler();
	Connection con=d.establishconnection();
	PreparedStatement ps=con.prepareStatement("SELECT * FROM annualrainfall where CityAverageRainfall=(SELECT max(CityAverageRainfall) FROM annualrainfall)");
	ResultSet rs=ps.executeQuery();
	while(rs.next()) {
		Annualrainfall a=new Annualrainfall();
		a.setCityPincode(rs.getInt(1));
		a.setCityName(rs.getString(2));
		a.setAverageAnnualRainfall(rs.getDouble(3));
		maxList.add(a);
	}
	return maxList;
	}

}
