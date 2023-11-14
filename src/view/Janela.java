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

@SuppressWarnings({ "serial", "unused" })
public class Janela extends JFrame{
	
	public JTabbedPane guias = new GuiasObjeto().initialize();

	public void init() {
		initializeScreen();
		createTabs(guias);
		
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	private void initializeScreen() {
		this.setTitle("Livraria BD");
		this.setResizable(false);
		this.setLayout(new FlowLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 	
	}
	
	private void createTabs(JTabbedPane guias) {
        GroupLayout layout = new GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(guias));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(guias));
        
        this.add(guias);
	}
}
