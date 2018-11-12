package sns;

import java.util.Date;

public class ReplyDTO {
	private int rid;
	private int mid;
	private String uid;
	private String date;
	private String rmsg;
	public int getRid() {
		return rid;
	}
	public int getMid() {
		return mid;
	}
	public String getUid() {
		return uid;
	}
	public String getDate() {
		return date;
	}
	public String getRmsg() {
		return rmsg;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
	
	
}
