package entidade;

public class BooksAuthors {
	private String isbn;
	private Long author_id;
	private Long seq_no;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Long getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(Long author_id) {
		this.author_id = author_id;
	}

	public Long getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(Long seq_no) {
		this.seq_no = seq_no;
	}
}