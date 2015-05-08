package co.id.jejalan.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import co.id.jejalan.rest.adapterClass.DateTimeAdapter;

@XmlRootElement(name="Post")
public class Post extends BaseBean{

	@XmlTransient
	public final static String ID = "id";
	@XmlTransient
	public final static String TITLE = "title";
	@XmlTransient
	public final static String CREATOR = "creator";
	@XmlTransient
	public final static String CITY = "city";
	@XmlTransient
	public final static String CONTENT = "content";
	@XmlTransient
	public final static String HIT = "hit";
	@XmlTransient
	public final static String REVISION_OF = "revision_Of";
	@XmlTransient
	public final static String REMOVED = "removed";
	@XmlTransient
	public final static String DATE_CREATED = "date_created";
	
	private String title;
	private int creator;
	private int city;
	private String content;
	private int hit;
	private int revisionOf;
	@XmlJavaTypeAdapter(value = DateTimeAdapter.class)
	private Timestamp dateCreated;
	private boolean removed;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getRevisionOf() {
		return revisionOf;
	}

	public void setRevisionOf(int revisionOf) {
		this.revisionOf = revisionOf;
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

}
