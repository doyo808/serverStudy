package chap08.dto;

public class BurgerDTO {
	String name;
	Integer price;
	String taste;
	
	public BurgerDTO(String name, Integer price, String taste) {
		this.name = name;
		this.price = price;
		this.taste = taste;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	@Override
	public String toString() {
		return "BurgerDTO [name=" + name + ", price=" + price + ", taste=" + taste + "]";
	}
	
	
	
}
