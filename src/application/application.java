package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import view.Janela;
import entidade.Authors;
import entidade.Books;
import controller.Controller;
import LivrariaDAO.ConexaoDAO;

/**
 * Classe application
 * Feita para encapsular todo o fluxo da aplicação e executar todo o processo
 * Podendo executar para realizar os testes
 */

@SuppressWarnings("unused")
public class application {
	public static void main(String[] args) {
		new Controller(new Janela(), new ConexaoDAO()).init();
	}
}
