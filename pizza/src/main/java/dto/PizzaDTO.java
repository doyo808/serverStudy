package dto;

import java.sql.Date;

public class PizzaDTO {
	Integer pizza_id;
	String pizza_name;
	Date made_date;
	
	public PizzaDTO(Integer pizza_id, String pizza_name, Date made_date) {
		super();
		this.pizza_id = pizza_id;
		this.pizza_name = pizza_name;
		this.made_date = made_date;
	}

	public Integer getPizza_id() {
		return pizza_id;
	}

	public void setPizza_id(Integer pizza_id) {
		this.pizza_id = pizza_id;
	}

	public String getPizza_name() {
		return pizza_name;
	}

	public void setPizza_name(String pizza_name) {
		this.pizza_name = pizza_name;
	}

	public Date getMade_date() {
		return made_date;
	}

	public void setMade_date(Date made_date) {
		this.made_date = made_date;
	}

	@Override
	public String toString() {
		return "PizzaDTO [pizza_id=" + pizza_id + ", pizza_name=" + pizza_name + ", made_date=" + made_date + "]";
	}
	
	
}
