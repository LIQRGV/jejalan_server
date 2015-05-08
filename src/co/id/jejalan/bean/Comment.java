package co.id.jejalan.bean;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import co.id.jejalan.rest.adapterClass.DateTimeAdapter;

@XmlRootElement(name="Comment")
public class Comment extends BaseBean{

	@XmlTransient
	public final static String POSTID = "post_id";
	@XmlTransient
	public final static String USERID = "user_id";
	@XmlTransient
	public final static String CONTENT = "content";
	@XmlTransient
	public final static String REVISION_OF = "revisionOf";
	@XmlTransient
	public final static String REMOVED = "removed";
	@XmlTransient
	public final static String DATE_CREATED = "date_created";
	
	private int userID;
	private int postID;
	private String content;
	private int revisionOf;
	@XmlJavaTypeAdapter(value = DateTimeAdapter.class)
	private Timestamp dateCreated;
	private boolean removed;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRevisionOf() {
		return revisionOf;
	}
	public void setRevisionOf(int revisionOf) {
		this.revisionOf = revisionOf;
	}
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	public boolean isRemoved() {
		return removed;
	}
	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

}
