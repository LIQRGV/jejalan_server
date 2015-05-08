package co.id.jejalan.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Tag")
public class Tag extends BaseBean{

	@XmlTransient
	public final static String TITLE = "title";
	
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}