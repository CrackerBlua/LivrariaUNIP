package view;

import javax.swing.*;

@SuppressWarnings("serial")
public class GuiasObjeto extends JTabbedPane{
	
    private JTabbedPane guias;
	
	public JTabbedPane initialize() {
        guias = new JTabbedPane();

        guias.addTab("Autores", new PainelAutor().createAutorTab());
        guias.addTab("Editoras", new PainelEditoras().createEditoraTab());
        guias.addTab("Livros", new PainelLivros().createLivroTab());
        
        return guias;
	}
}