package shark.dto;

import java.util.Date;

public class Book {
	private String isbn;
	private String book_name;
	private String author;
	private Integer price;
	private Integer max_page;
	private Date pub_date;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	public Book(String isbn, String book_name, String author, Integer price, Integer max_page, Date pub_date) {
		this.isbn = isbn;
		this.book_name = book_name;
		this.author = author;
		this.price = price;
		this.max_page = max_page;
		this.pub_date = pub_date;
	}
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getMax_page() {
		return max_page;
	}
	public void setMax_page(Integer max_page) {
		this.max_page = max_page;
	}
	public Date getPub_date() {
		return pub_date;
	}
	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}
	
	@Override
	public String toString() {
		return "책입니다.";
	}
}
