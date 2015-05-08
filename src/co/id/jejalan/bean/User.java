package co.id.jejalan.bean;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="User")
public class User extends BaseBean{

	@XmlTransient
	public final static String USERNAME = "username";
	@XmlTransient
	public final static String PASSWORD = "password";
	@XmlTransient
	public final static String COMPLETE_NAME = "complete_name";
	@XmlTransient
	public final static String REGION = "region";
	@XmlTransient
	public final static String EMAIL = "email";
	@XmlTransient
	public final static String HP = "HP";
	@XmlTransient
	public final static String PROFILE_PICTURE = "profilePicture";
	
	private String username;
	private String password;
	private String completeName;
	private int region;
	private String email;
	private String hp;
	private String profilePicture;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

}