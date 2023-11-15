package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import view.GuiasObjeto;

/** 
 * @Class: Janela 
 * 	Essa classe é responsável de encapsular todos os componentes visuais da tela em uma janela JFrame
 *  Nessa classe é chamado subsequentemente os componentes e encapsula.
 **/

@SuppressWarnings({ "serial", "unused" })
public class Janela extends JFrame{
	
	public JTabbedPane guias = new GuiasObjeto().initialize();

	//Inicializa toda estrutura visual e renderiza tudo que foi, até então, encapsulado
	public void init() {
		initializeScreen();
		createTabs(guias);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	//Método que define como será renderizado o layout da JFrame
	private void initializeScreen() {
		this.setTitle("Livraria BD");
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 	
	}
	
	//Método que vai definir exibição das guias no JFrame, tal como a posição que será renderizado
	private void createTabs(JTabbedPane guias) {
        GroupLayout layout = new GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(guias));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(guias));
        
        this.add(guias);
	}
}
