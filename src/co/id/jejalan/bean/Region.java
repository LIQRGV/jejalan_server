package co.id.jejalan.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Region")
public class Region extends BaseBean{
	
	@XmlTransient
	public final static String NAMA_REGION = "nama_region";
	
	private String namaRegion;

	public String getNamaRegion() {
		return namaRegion;
	}

	public void setNamaRegion(String namaRegion) {
		this.namaRegion = namaRegion;
	}

}
