package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PizzaDTO;

public class PizzaDAO {
	Connection conn;
	
	public PizzaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<PizzaDTO> getAllPizzas() {
		
		List<PizzaDTO> pizzas = new ArrayList<>();
		
		try (
	    	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM pizzas");
	    	ResultSet rs = pstmt.executeQuery();
		) {
			
			while (rs.next()) {
				pizzas.add( 
						new PizzaDTO(
							rs.getInt("pizza_id"),
							rs.getString("pizza_name"),
							rs.getDate("made_date"))
						);
			}
			
			return pizzas;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
