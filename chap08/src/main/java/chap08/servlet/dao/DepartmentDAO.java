package chap08.servlet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chap08.dto.DepartmentDTO;

public class DepartmentDAO {
	Connection conn;
	
	public DepartmentDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<DepartmentDTO> getAllDeps() {
		String sql = "SELECT * FROM departments";
		try (
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			List<DepartmentDTO> deps = new ArrayList<>();
			
			while (rs.next()) {
				deps.add(new DepartmentDTO(
						rs.getInt("department_id")
						, rs.getString("department_name")
						, rs.getInt("manager_id")
						, rs.getInt("location_id")));
			}
			
			return deps;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
