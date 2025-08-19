package chap08.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap08.dto.BurgerDTO;

public class BurgerDAO {
	Connection conn;
	
	public BurgerDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<BurgerDTO> getAllBurgers() {
		
		try (
				PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM burgers");
				ResultSet rs = pstmt.executeQuery();
				) {
			
			List<BurgerDTO> burgers = new ArrayList<>();
			
			while(rs.next()) {
				burgers.add(new BurgerDTO(
						rs.getString("name"),
						rs.getInt("price"),
						rs.getString("taste")
						));
			}
			
			return burgers;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public int addBurger(BurgerDTO dto) {
		
		String sql = "INSERT INTO burgers (name, price, taste) VALUES (?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getTaste());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public BurgerDTO get(String burger_name) {
		String sql = "SELECT * FROM burgers WHERE name=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			
			pstmt.setString(1, burger_name);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				rs.next();
				return new BurgerDTO(
							burger_name,
							rs.getInt("price"),
							rs.getString("taste")
							);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String update(BurgerDTO burger) {
		String sql = "UPDATE burgers SET "
				+ "price=?, taste=? "
				+ "WHERE name=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, burger.getPrice());
			pstmt.setString(2, burger.getTaste());
			pstmt.setString(3, burger.getName());
			
			int undated_row = pstmt.executeUpdate();
			
			return undated_row > 0 ? burger.getName() : null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int delete(BurgerDTO burger) {
		String sql = "DELETE FROM burgers WHERE name=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, burger.getName());
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
}
