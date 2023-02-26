package welcome;

public class Annualrainfall 
{
	int cityPincode;
	String cityName;
	double	averageAnnualRainfall;
	public int getCityPincode() {
		return cityPincode;
	}
	public void setCityPincode(int cityPincode) {
		this.cityPincode = cityPincode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public double getAverageAnnualRainfall() {
		return averageAnnualRainfall;
	}
	public void setAverageAnnualRainfall(double averageAnnualRainfall) {
		this.averageAnnualRainfall = averageAnnualRainfall;
	}
	void calculateAverageAnnualRainfall (double	monthlyRainfall [ ] )
	{
		double total=0;
		for(double d:monthlyRainfall)
		{
			total+=d;
		}
		setAverageAnnualRainfall(total/monthlyRainfall.length);
	}

}
