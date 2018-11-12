package sns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import member.MemberDAO;
import util.DBManager;

public class MessageDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//private static final Logger logger = LoggerFactory.getLogger(MemberDAO.class);

	public boolean newMsg(MessageDTO message) {
		conn = DBManager.getConnection();
		String sql = "insert into s_message(uid, msg, date) values(?,?,now());";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, message.getUid());
			pstmt.setString(2, message.getMsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		} finally {
			disconnect();
		}
		return true;
	}

	public boolean delMsg(int mid) {
		conn = DBManager.getConnection();
		String sql = "delete from s_message where mid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean newReply(ReplyDTO reply) {
		conn = DBManager.getConnection();
		String sql = "insert into s_reply(mid, uid, rmsg, date) values(?,?,?,now())";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply.getMid());
			pstmt.setString(2, reply.getUid());
			pstmt.setString(3, reply.getRmsg());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public boolean delReply(int rid) {
		conn = DBManager.getConnection();
		String sql = "delete from s_reply where rid = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			return false;
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public ArrayList<MessageSetDTO> getAll(int cnt, String suid) {
		conn = DBManager.getConnection();
		ArrayList<MessageSetDTO> dataList = new ArrayList<MessageSetDTO>();
		try {
			if ((suid == null) || (suid.equals(""))) {
				String sql = "select * from s_message order by date desc limit 0,?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, cnt);
			} else {
				String sql = "select * from s_message where uid=? order by date desc limit 0,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, suid);
				pstmt.setInt(2, cnt);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MessageSetDTO ms = new MessageSetDTO();
				MessageDTO m = new MessageDTO();
				ArrayList<ReplyDTO> rlist = new ArrayList<ReplyDTO>();
				m.setMid(rs.getInt("mid"));
				m.setMsg(rs.getString("msg"));
				m.setDate(rs.getDate("date") + " / " + rs.getTime("date"));
				m.setFavcount(rs.getInt("favcount"));
				m.setUid(rs.getString("uid"));
				m.setReplycount(rs.getInt("replycount"));
				String sql = "select * from s_reply where mid=? order by date desc;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, rs.getInt("mid"));
				ResultSet rrs = pstmt.executeQuery();
				while (rrs.next()) {
					ReplyDTO rDto = new ReplyDTO();
					rDto.setRid(rrs.getInt("rid"));
					rDto.setUid(rrs.getString("uid"));
					rDto.setRmsg(rrs.getString("rmsg"));
					rDto.setDate(rrs.getDate("date") + " / " + rrs.getTime("date"));
					rlist.add(rDto);
				}
				rrs.last();
				m.setReplycount(rrs.getRow());
				ms.setMessage(m);
				ms.setRlist(rlist);
				dataList.add(ms);
				rrs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}
	
	public void favorite(int mid) {
		conn = DBManager.getConnection();
		String sql = "update s_message set favcount=favcount+1 where mid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mid);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getErrorCode());
		}
		finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
