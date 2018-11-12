package sns;

import java.util.Date;

public class MessageDTO {
	private int mid;
	private String uid;
	private String msg;
	private int favcount;
	private int replycount;
	private String date;
	
	public int getMid() {
		return mid;
	}
	public String getUid() {
		return uid;
	}
	public String getMsg() {
		return msg;
	}
	public int getFavcount() {
		return favcount;
	}
	public int getReplycount() {
		return replycount;
	}
	public String getDate() {
		return date;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setFavcount(int favcount) {
		this.favcount = favcount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
