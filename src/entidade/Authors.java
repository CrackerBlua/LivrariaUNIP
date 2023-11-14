package entidade;

public class Authors {
	private Long id;
	private String name;
	private String fname;
	
	public Authors(Long id, String name, String fname) {
		super();
		this.id = id;
		this.name = name;
		this.fname = fname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
}
