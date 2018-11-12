package sns;

import java.util.ArrayList;

public class MessageSetDTO {
	private MessageDTO message;
	private ArrayList<ReplyDTO> rlist;
	
	public MessageDTO getMessage() {
		return message;
	}
	public ArrayList<ReplyDTO> getRlist() {
		return rlist;
	}
	public void setMessage(MessageDTO message) {
		this.message = message;
	}
	public void setRlist(ArrayList<ReplyDTO> rlist) {
		this.rlist = rlist;
	}
	
	
}
