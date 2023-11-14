package entidade;

public class Books {
	
	private String title;
	private String isbn;
	private Long publisher_id;
	private Double price;

	public Books(String title, String isbn, Long publisher_id, Double price) {
		this.title = title;
		this.isbn = isbn;
		this.publisher_id = publisher_id;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
