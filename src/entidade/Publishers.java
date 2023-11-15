package entidade;

/**
 * Classe feita como encapsulamento de dados, materializando um objeto
 * podendo assim, trabalhar com os dados de editoras vindos do BD
 * e facilitando a transformação de dados do objeto para ser enviado ao BD  
 */

public class Publishers {
	private Long publisher_id;
	private String name;
	private String url;
	
	public Publishers(Long publisher_id, String name, String url) {
		this.publisher_id = publisher_id;
		this.name = name;
		this.url = url;
	}

	public Long getPublisher_id() {
		return publisher_id;
	}

	public void setPublisher_id(Long publisher_id) {
		this.publisher_id = publisher_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
