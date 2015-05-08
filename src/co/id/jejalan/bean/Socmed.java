package co.id.jejalan.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Socmed")
public class Socmed extends BaseBean{

	@XmlTransient
	public final static String NAMA_SOCMED = "nama_socmed";
	
	private String namaSocmed;

	public String getNamaSocmed() {
		return namaSocmed;
	}

	public void setNamaSocmed(String namaSocmed) {
		this.namaSocmed = namaSocmed;
	}

}