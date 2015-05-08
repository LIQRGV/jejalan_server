package co.id.jejalan.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="CityList")
public class CityList extends BaseBean{

	@XmlTransient
	public final static String CITYNAME = "city_name";
	@XmlTransient
	public final static String REGION = "region";
	
	private String cityName;

	private int region;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}


}