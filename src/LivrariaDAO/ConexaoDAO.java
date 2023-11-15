package LivrariaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.ArrayList;
import entidade.Authors;
import entidade.Books;
import entidade.Publishers;

/** 
 * @Class: ConexaoDAO 
 * 	Essa classe implmenta a interface LivrariaDAO
 *  Ela implementa de forma concreta as regras de negócio para realizar operações no Bando de Dados
 **/

public class ConexaoDAO implements LivrariaDAO {

	// Chaves para acesso ao banco de dados
	private final static String USER = "dan"; //Username
	private final static String PASS = "H!%T0_Sp"; //Password
	private final static String DATABASE = "livraria"; //DB ao ser utilizado
	private final static String URL = "jdbc:mysql://localhost:3030/" + DATABASE; //URL

	// Metodo para testar conexão ao BD
	public boolean testaConnection() {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			displaySuccessMessage("Sucesso ao conectar ao banco de dados!");
			return true;
		} catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			
		
		return false;
	}

	// Busca os autores de livros
	// Nesse método ele verifica quais critérios o usuário entrou para a pesquisa
	// Se não entrou dados, pesquisa todos
	@Override
	public List<Authors> buscarAutores(Long id, String name, String fname) {
		List<Authors> autores = new ArrayList<Authors>();
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			Boolean buscaId = id != null;
			Boolean buscaName = !name.isBlank() ;
			Boolean buscaFname = !fname.isBlank() ;

			String query = "SELECT * FROM authors " ;
			String conditions = "";
			
			if(buscaId)
				conditions += "author_id = ?";
			
			if(buscaName)
				conditions += conditions.isBlank()? " LOWER(name) LIKE LOWER(?) ": " AND LOWER(name) LIKE LOWER(?) ";
			
			if(buscaFname)
				conditions += conditions.isBlank()? " LOWER(fname) LIKE LOWER(?) ": " AND LOWER(fname) LIKE LOWER(?) ";
			
			if(!conditions.isBlank()) {
				query += " WHERE " + conditions;
			}
			
			PreparedStatement ps = c.prepareStatement(query);
			Integer count = 0;
			
			if(buscaId) {
				count++;
				ps.setLong(count, id);
			}
			
			if(buscaName) {
				count++;
				ps.setString(count, name);
			}
			
			if(buscaFname) {
				count++;
				ps.setString(count, fname);
			}

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				autores.add(new Authors(rs.getLong("author_id"), rs.getString("name"), rs.getString("fname")));
			}
			
			if(autores.size() == 0)
				displayWarningMessage("Não foram encontrados registros!");
		
		} catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		}
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			

		return autores;
	}

	// Busca as editoras
	// Nesse método ele verifica quais critérios o usuário entrou para a pesquisa
	// Se não entrou dados, pesquisa todos
	@Override
	public List<Publishers> buscarEditoras(Long id, String name, String url) {
		List<Publishers> editoras = new ArrayList<Publishers>();
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			Boolean buscaId = id != null;
			Boolean buscaName = !name.isBlank();
			Boolean buscaURL = !url.isBlank();
		
			String query = "SELECT * FROM publishers " ;
			String conditions = "";
			
			if(buscaId)
				conditions += "publisher_id = ?";
			
			if(buscaName)
				conditions += conditions.isBlank()? " LOWER(name) LIKE LOWER(?) ": " AND LOWER(name) LIKE LOWER(?) ";
			
			if(buscaURL)
				conditions += conditions.isBlank()? " LOWER(url) LIKE LOWER(?) ": " AND LOWER(url) LIKE LOWER(?) ";
			
			if(!conditions.isBlank()) {
				query += " WHERE " + conditions;
			}
			
			PreparedStatement ps = c.prepareStatement(query);
			Integer count = 0;
			
			if(buscaId) {
				count++;
				ps.setLong(count, id);
			}
			
			if(buscaName) {
				count++;
				ps.setString(count, name);
			}
			
			if(buscaURL) {
				count++;
				ps.setString(count, url);
			}
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				editoras.add(new Publishers(rs.getLong("publisher_id"), rs.getString("name"), rs.getString("url")));
			}
			
			if(editoras.size() == 0)
				displayWarningMessage("Não foram encontrados registros!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {	
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			

		return editoras;
	}

	// Busca os livros
	// Nesse método ele verifica quais critérios o usuário entrou para a pesquisa
	// Se não entrou dados, pesquisa todos
	@Override
	public List<Books> buscarLivros(Long publisher_id, String title, Double price, String isBn) {
		List<Books> livros = new ArrayList<Books>();
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			Boolean buscaId = publisher_id != null;
			Boolean buscaTitle = !title.isBlank();
			Boolean buscaPrice = price != null;
			Boolean buscaIsBn = !isBn.isBlank();
			
			String query = "SELECT * FROM books " ;
			String conditions = "";

			if(buscaId)
				conditions += "publisher_id = ?";
			
			if(buscaTitle)
				conditions += conditions.isBlank()? " LOWER(title) LIKE LOWER(?) ": " AND LOWER(title) LIKE LOWER(?) ";
			
			if(buscaPrice)
				conditions += conditions.isBlank()? " price = ? ": " AND price = ? ";
			
			if(buscaIsBn)
				conditions += conditions.isBlank()? " LOWER(isbn) LIKE LOWER(?) ": " AND LOWER(isbn) LIKE LOWER(?) ";
			
			if(!conditions.isBlank()) {
				query += " WHERE " + conditions;
			}
			
			PreparedStatement ps = c.prepareStatement(query);
			Integer count = 0;
			
			if(buscaId) {
				count++;
				ps.setLong(count, publisher_id);
			}
			
			if(buscaTitle) {
				count++;
				ps.setString(count, title);
			}
			
			if(buscaPrice) {
				count++;
				ps.setDouble(count, price);
			}
			
			if(buscaIsBn) {
				count++;
				ps.setString(count, isBn);
			}
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				livros.add(new Books(rs.getString("title"), rs.getString("isbn"), rs.getLong("publisher_id"),  rs.getDouble("price")));
			}
			
			if(livros.size() == 0) {
				displayWarningMessage("Não foram encontrados registros!");
			}
						
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			

		return livros;
	}

	// Insere os autores 
	@Override
	public void incluirAutor(Authors autor) {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "INSERT INTO authors(author_id, name, fname) VALUES(?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setLong(1, autor.getId());
			ps.setString(2, autor.getName());
			ps.setString(3, autor.getFname());
			
			ps.executeUpdate();

			displaySuccessMessage("Registro inserido com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			
	}

	// Insere as editoras
	@Override
	public void incluirEditora(Publishers editora) {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "INSERT INTO publishers(publisher_id, name, url) VALUES(?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setLong(1, editora.getPublisher_id());
			ps.setString(2, editora.getName());
			ps.setString(3, editora.getUrl());
			
			ps.executeUpdate();

			displaySuccessMessage("Registro inserido com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			
	}

	// Insere os livros
	@Override
	public void incluirLivro(Books livro) {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "INSERT INTO books(title, isbn, publisher_id, price) VALUES(?, ?, ?, ?)";
			
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setString(1, livro.getTitle());
			ps.setString(2, livro.getIsbn());
			ps.setLong(3, livro.getPublisher_id());
			ps.setDouble(4, livro.getPrice());
			
			ps.executeUpdate();

			displaySuccessMessage("Registro inserido com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível inserir o registro! \n" + e.getMessage()); }			
	}

	// Atualiza no BD os autores
	@Override
	public void atualizarAutor(Authors autor) {
		Boolean buscaId = autor.getId() != null;
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "UPDATE authors SET author_id = ?, name = ?, fname = ? ";
			String conditions = " WHERE ";
						
			if(buscaId)
				conditions += "author_id = ?";
			
			if(!conditions.isBlank()) {
				command += conditions;
			}
			
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setLong(1, autor.getId());
			ps.setString(2, autor.getName());
			ps.setString(3, autor.getFname());
			
			Integer count = 3;
			
			if(buscaId) {
				count++;
				ps.setLong(count, autor.getId());
			}
						
			ps.executeUpdate();

			displaySuccessMessage("Registro atualizado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage()); }			
	}

	// Atualiza no BD o registro da editora
	@Override
	public void atualizarEditora(Publishers editora) {
		Boolean buscaId = editora.getPublisher_id() != null;
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "UPDATE publishers SET publisher_id = ?, name = ?, url = ? ";
			String conditions = " WHERE ";
						
			if(buscaId)
				conditions += "publisher_id = ?";
			
			if(!conditions.isBlank()) {
				command += conditions;
			}
			
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setLong(1, editora.getPublisher_id());
			ps.setString(2, editora.getName());
			ps.setString(3, editora.getUrl());
			
			Integer count = 3;
			
			if(buscaId) {
				count++;
				ps.setLong(count, editora.getPublisher_id());
			}
						
			ps.executeUpdate();

			displaySuccessMessage("Registro atualizado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage()); }			
	}

	// Atualiza no BD o registro de um livro
	@Override
	public void atualizarLivro(Books livro) {
		Boolean buscaId = livro.getPublisher_id() != null;
		Boolean buscaTitle = !livro.getTitle().isBlank();
		Boolean buscaIsBn = !livro.getIsbn().isBlank();
		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "UPDATE books SET title = ?, isbn = ?, publisher_id = ?, price = ?";
			String conditions = " WHERE";
						
			if(buscaId)
				conditions += " publisher_id = ?";
			
			if(buscaTitle)
				conditions += " AND LOWER(title) = LOWER(?)";
			
			if(buscaIsBn)
				conditions += " AND isbn = ?";
			
			if(!conditions.isBlank()) {
				command += conditions;
			}
			
			command += ";";
			PreparedStatement ps = c.prepareStatement(command);
			
			ps.setString(1, livro.getTitle());
			ps.setNString(2, livro.getIsbn());
			ps.setLong(3, livro.getPublisher_id());
			ps.setDouble(4, livro.getPrice());
			
			Integer count = 4;
			
			if(buscaId) {
				count++;
				ps.setLong(count, livro.getPublisher_id());
			}
			
			if(buscaTitle) {
				count++;
				ps.setString(count, livro.getTitle());
			}
			
			if(buscaIsBn) {
				count++;
				ps.setString(count, livro.getIsbn());
			}
						
			ps.executeUpdate();

			displaySuccessMessage("Registro atualizado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage());
		} 
		catch (SQLException e) { displayErrorMessage("Não foi possível atualizar o registro! \n" + e.getMessage()); }			
	}

	// Apaga do BD o registro de autor
	@Override
	public void apagarAutor(Long id) {		
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "DELETE FROM authors WHERE author_id = ?";
			
			PreparedStatement ps = c.prepareStatement(command);
			ps.setLong(1, id);
			
			ps.executeUpdate();

			displaySuccessMessage("Registro deletado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi deletado atualizar o registro!");
		} 
		catch (SQLException e) { e.printStackTrace(); }	
	}

	// Apaga do BD um registro de editora
	@Override
	public void apagarEditora(Long id) {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "DELETE FROM publishers WHERE publisher_id = ?";
			
			PreparedStatement ps = c.prepareStatement(command);
			ps.setLong(1, id);
			
			ps.executeUpdate();

			displaySuccessMessage("Registro deletado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi deletado atualizar o registro!");
		} 
		catch (SQLException e) { e.printStackTrace(); }	
	}

	// Apaga do BD um registro de livro 
	@Override
	public void apagarLivro(Long id, String isbn) {
		try(Connection c = DriverManager.getConnection(URL, USER, PASS)){
			String command = "DELETE FROM books WHERE publisher_id = ? AND isbn = ?";
			
			PreparedStatement ps = c.prepareStatement(command);
			ps.setLong(1, id);
			ps.setString(2, isbn);
			
			ps.executeUpdate();

			displaySuccessMessage("Registro deletado com sucesso!");
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			displayErrorMessage("Não foi deletado atualizar o registro!");
		} 
		catch (SQLException e) { e.printStackTrace(); }	
	}
	
	/**
	 * Os métodos abaixo são para notificar ao usuário um tipo de mensagem, seja de erro, sucesso ou aviso
	 * Para que ele tenha noção do que ocorre no backend 
	*/

	private void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
	}
	
	private void displaySuccessMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message);
	}
	
	private void displayWarningMessage(String message) {
		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.WARNING_MESSAGE);
	}
}
