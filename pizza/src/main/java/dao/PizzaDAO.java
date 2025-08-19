package dao;

import java.sql.Connection;
import java.sql.Date;
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
	    	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM pizzas ORDER BY pizza_id");
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
	
	public int addPizza(PizzaDTO pizza) {
		String sql = "INSERT INTO pizzas (pizza_id, pizza_name, made_date)"
				+ " VALUES (pizzas_seq.nextval, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pizza.getPizza_name());
			pstmt.setDate(2, pizza.getMade_date());
			
			return pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public PizzaDTO getPizza(int pizza_id) {
		String sql = "SELECT * FROM pizzas WHERE pizza_id=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, pizza_id);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				rs.next();
				return new PizzaDTO (pizza_id, 
							rs.getString("pizza_name"),
							rs.getDate("made_date"));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int updatePizza(PizzaDTO pizza) {
		String sql = "UPDATE pizzas SET "
				+ "pizza_name=?, made_date=? "
				+ "WHERE pizza_id=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, pizza.getPizza_name());
			pstmt.setDate(2, pizza.getMade_date());
			pstmt.setInt(3, pizza.getPizza_id());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	
	public int deletePizzaById(int id) {
		String sql = "DELETE FROM pizzas WHERE pizza_id=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	public int deletePizzasByDate(Date date) {
		String sql = "DELETE FROM pizzas WHERE made_date=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setDate(1, date);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
