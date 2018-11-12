package member;

import java.util.Date;

public class MemberDTO {
	private String name;
	private String uid;
	private String passwd;
	private String email;
	private Date date;
	
	public String getName() {
		return name;
	}
	public String getUid() {
		return uid;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getEmail() {
		return email;
	}
	public Date getDate() {
		return date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
