package jspboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jspboard.dto.JspBoard;

public class BoardDao {
	
	private static BoardDao instance = new BoardDao();
	private Connection conn;
	private BoardDao() {}
	
	public static BoardDao getInstance(Connection conn) {
		instance.setConnection(conn);
		return instance;
	}
	
	private void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	
	// DB에서 자료 조회
	public List<JspBoard> selectAll() {
		List<JspBoard> list = new ArrayList<>();
		
		try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM jspboard");
			 ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				list.add(new JspBoard(
							rs.getInt("board_id"),
							rs.getString("board_title"),
							rs.getString("board_writer"),
							rs.getString("board_writer_ip_addr"),
							rs.getString("board_password"),
							rs.getString("board_content"),
							rs.getDate("board_write_date"),
							rs.getInt("board_view_count"),
							rs.getInt("board_pos_count"),
							rs.getInt("board_neg_count")
						));
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public JspBoard selectOne(int board_id) {
		String sql = "SELECT * FROM jspboard WHERE board_id=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, board_id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				return rs.next() ? new JspBoard(
						rs.getInt("board_id"),
						rs.getString("board_title"),
						rs.getString("board_writer"),
						rs.getString("board_writer_ip_addr"),
						rs.getString("board_password"),
						rs.getString("board_content"),
						rs.getDate("board_write_date"),
						rs.getInt("board_view_count"),
						rs.getInt("board_pos_count"),
						rs.getInt("board_neg_count")
					) : null;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	// DB에 입력
	public int insert(String board_title, 
					  String board_writer, 
					  String board_writer_ip_addr,
					  String board_password,
					  String board_content) {
		
		String sql = "INSERT INTO jspboard(board_id, board_title, board_writer, board_writer_ip_addr,"
				+ "board_password, board_content, board_write_date, board_view_count, board_pos_count, board_neg_count)"
				+ "VALUES(jspboard_board_id_seq.nextval, ?, ?, ?, ?, ?, sysdate, 0, 0, 0)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_writer_ip_addr);
			pstmt.setString(4, board_password);
			pstmt.setString(5, board_content);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 조회수 증가
	public int increaseViewCount(int board_id) {
		String sql = "UPDATE jspboard SET board_view_count = board_view_count + 1"
				+ "WHERE board_id = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, board_id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	// 좋아요 처리소
	public int IncreasePosCount(int board_id) {
		String sql = "UPDATE jspboard SET board_pos_count = board_pos_count + 1"
				+ "WHERE board_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, board_id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public int DecreasePosCount(int board_id) {
		String sql = "UPDATE jspboard SET board_pos_count = board_pos_count - 1"
				+ "WHERE board_id = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, board_id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public boolean isAlreadyLiked(int board_id, String ip) {
		String sql = "SELECT 1 FROM board_like_history WHERE board_id=? AND ip_addr=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, board_id);
			pstmt.setString(2, ip);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				return rs.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public int insertLikeHistory(int board_id, String ip) {
		String sql = "INSERT INTO board_like_history (board_id, ip_addr) "
				+ "VALUES(?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, board_id);
			pstmt.setString(2, ip);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public int deleteLikeHistory(int board_id, String ip) {
		String sql = "DELETE FROM board_like_history "
				+ "WHERE board_id=? AND ip_addr=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, board_id);
			pstmt.setString(2, ip);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
}
