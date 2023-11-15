package view;

import javax.swing.*;

/** 
 * @Class: GuiasObjeto 
 * 	Essa classe é responsável de encapsular componentes de abas separadamente de cada Objeto do Banco de dados
 **/

@SuppressWarnings("serial")
public class GuiasObjeto extends JTabbedPane{
	
    private JTabbedPane guias;
	
    //Inicilização da classe, onde é definido as guias presentes no painel
	public JTabbedPane initialize() {
        guias = new JTabbedPane();
        guias.addTab("Autores", new PainelAutor().createAutorTab());
        guias.addTab("Editoras", new PainelEditoras().createEditoraTab());
        guias.addTab("Livros", new PainelLivros().createLivroTab());
        
        return guias;
	}
}