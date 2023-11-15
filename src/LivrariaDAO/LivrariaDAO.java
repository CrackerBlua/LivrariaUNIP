package LivrariaDAO;

import java.util.List;
import entidade.Authors;
import entidade.Publishers;
import entidade.Books;

/** 
 * @Interface: LivrariaDAO 
 * 	Essa classe é responsável de abstrair os métodos, para que as classes concretas implementem
 *  e possam implementar regras de negócios diferentes envolvendo a conexão com o banco de dados
 **/

public interface LivrariaDAO {	
	public List<Authors> buscarAutores(Long id, String name, String fnaf);
	public List<Publishers> buscarEditoras(Long id, String name, String url);
	public List<Books> buscarLivros(Long publisher_id, String title, Double price, String isBn);
	public void incluirAutor(Authors autor);
	public void incluirEditora(Publishers editora);
	public void incluirLivro(Books livro);
	public void atualizarAutor(Authors autor);
	public void atualizarEditora(Publishers editora);
	public void atualizarLivro(Books livro);
	public void apagarAutor(Long id);
	public void apagarEditora(Long id);
	public void apagarLivro(Long id, String isbn);
}
