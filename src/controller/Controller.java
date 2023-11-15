package controller;

import LivrariaDAO.ConexaoDAO;
import view.Janela;

/**
 * Classe Controladora, feita para reaizar as operações de negócio do front-end  
 */

public class Controller {

	ConexaoDAO dao;
	Janela view;
	
	public Controller(Janela view, ConexaoDAO dao) {
		this.view = view;
		this.dao = dao;
	}
	
	public void init() {
		dao.testaConnection();
		view.init();		
	}
}
