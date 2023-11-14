package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import LivrariaDAO.ConexaoDAO;
import entidade.Publishers;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

@SuppressWarnings("serial")
public class PainelEditoras extends JPanel implements View, ActionListener{

	private static final Object[] COLUMNS = {"Id", "Name", "URL"};

	JPanel guiaEditoras = new JPanel();
	ButtonGroup buttonGroupEditoras = new ButtonGroup();
	JRadioButton insertPublisher = new JRadioButton("Inserir");
	JRadioButton updatePublisher = new JRadioButton("Atualizar");
	JRadioButton deletePublisher = new JRadioButton("Deletar");
	JRadioButton searchPublisher = new JRadioButton("Buscar");
	JSeparator publisherTabSeparator1 = new JSeparator();
    JSeparator publisherTabSeparator2 = new JSeparator();
    JSeparator publisherTabSeparator3 = new JSeparator();
    JLabel publisherIdLabel = new JLabel("Id");
    JLabel publisherNameLabel = new JLabel("Nome");
    JLabel publisherURLLabel = new JLabel("URL");
    JTextField publisherId = new JTextField();
    JTextField publisherName = new JTextField();
    JTextField publisherURL = new JTextField();
    JScrollPane publisherScrollPane = new JScrollPane();
	DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0);
    JTable publisherTable = new JTable(tableModel);
    JButton insertPublisherButton = new JButton("Inserir");
    JButton updatePublisherButton = new JButton("Atualizar");
    JButton deletePublisherButton = new JButton("Deletar");
    JButton searchPublisherButton = new JButton("Buscar");
    JLabel userInfoText3 = new JLabel();
    JLabel userInfoText4 = new JLabel();
    
    JButton[] botoes = {insertPublisherButton, updatePublisherButton, searchPublisherButton, deletePublisherButton};

	
	public JPanel createEditoraTab() {
		init();
		setActionsListeners();
		return guiaEditoras;
	}
	
	private void setInsertRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					insertPublisherButton.setEnabled(true);
				} else {
					insertPublisherButton.setEnabled(false);
				}
			}
		});	
	}
	
	private void setUpdateRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					updatePublisherButton.setEnabled(true);
				} else {
					updatePublisherButton.setEnabled(false);
				}
			}
		});	
	}
	
	private void setDeleteRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					deletePublisherButton.setEnabled(true);
					publisherName.setEnabled(false);
					publisherURL.setEnabled(false);
				} else {
					deletePublisherButton.setEnabled(false);
					publisherName.setEnabled(true);
					publisherURL.setEnabled(true);
				}
			}
		});	
	}
	
	private void setSearchRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					searchPublisherButton.setEnabled(true);
				} else {
					searchPublisherButton.setEnabled(false);
				}
			}
		});	
	}
	
	private void setActionsListeners() {
		for(JButton button: botoes) {
			button.addActionListener(this);
		}
	}
	
	private void cleanFields() {
		publisherId.setText("");
		publisherName.setText("");
		publisherURL.setText("");
	}
	
	private void populaTable(List<Publishers> editoras) {
		tableModel.setRowCount(0);

		for(Publishers editora: editoras) {
			tableModel.addRow(new Object[] {editora.getPublisher_id(), editora.getName(), editora.getUrl()});
		}
	}
	
	@Override
	public void dispatchBDEvent(String context) {
		
		Long 	id 		= publisherId.getText().isBlank()? null: Long.valueOf(publisherId.getText());
		String 	name 	= publisherName.getText(),
				url		= publisherURL.getText();
		
		Publishers editora = new Publishers(id, name, url); 

		switch (context) {
			case "Buscar":	
				populaTable(new ConexaoDAO().buscarEditoras(id, name, url));
				cleanFields();
				break;
				
			case "Inserir":
				new ConexaoDAO().incluirEditora(editora);
				cleanFields();
				break;
				
			case "Atualizar":
				new ConexaoDAO().atualizarEditora(editora);
				cleanFields();
				break;
				
			case "Deletar":
				new ConexaoDAO().apagarEditora(editora.getPublisher_id());
				cleanFields();
				break;
				
			default:
				break;
		}
	}

	@Override
	public void init() {
        buttonGroupEditoras.add(insertPublisher);
        buttonGroupEditoras.add(updatePublisher);
        buttonGroupEditoras.add(deletePublisher);
        buttonGroupEditoras.add(searchPublisher);
        
        insertPublisherButton.setEnabled(false);
        updatePublisherButton.setEnabled(false);
        deletePublisherButton.setEnabled(false);
        searchPublisherButton.setEnabled(false);
                
        setUpdateRadioListener(updatePublisher);
        setSearchRadioListener(searchPublisher);
        setDeleteRadioListener(deletePublisher);
        setInsertRadioListener(insertPublisher);

        publisherTabSeparator1.setOrientation(SwingConstants.VERTICAL);

        publisherIdLabel.setFont(new Font("Segoe UI", 1, 12)); 
        publisherNameLabel.setFont(new Font("Segoe UI", 1, 12));
        publisherURLLabel.setFont(new Font("Segoe UI", 1, 12)); 

        publisherTabSeparator3.setOrientation(SwingConstants.VERTICAL);

        publisherScrollPane.setViewportView(publisherTable);
        
        userInfoText3.setFont(new Font("Segoe UI", 1, 12));
        userInfoText3.setText("Coloque os dados para pesquisar. ");

        userInfoText4.setFont(new Font("Segoe UI", 1, 12));
        userInfoText4.setText("Obs: Se não inserir dados, fará uma pesquisa sem filtros.");
        
        GroupLayout guiaEditorasLayout = new GroupLayout(guiaEditoras);
        guiaEditoras.setLayout(guiaEditorasLayout);
        guiaEditorasLayout
        .setHorizontalGroup(
            guiaEditorasLayout
            	.createParallelGroup(GroupLayout.Alignment.LEADING)
            		.addGroup(
            			guiaEditorasLayout
            				.createSequentialGroup()
            				.addContainerGap()
            				.addGroup(
            					guiaEditorasLayout
            						.createParallelGroup(GroupLayout.Alignment.LEADING)
            						.addComponent(insertPublisher, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
            						.addComponent(updatePublisher, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
            						.addComponent(deletePublisher, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
            						.addComponent(searchPublisher, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
            				)
            				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            				.addComponent(publisherTabSeparator1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
            				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            				.addGroup(
            					guiaEditorasLayout
            						.createParallelGroup(GroupLayout.Alignment.LEADING)
            						.addComponent(publisherTabSeparator2)
            						.addComponent(publisherScrollPane)
            						.addGroup(
            							guiaEditorasLayout
            								.createSequentialGroup()
            								.addGroup(
            									guiaEditorasLayout
            										.createParallelGroup(GroupLayout.Alignment.LEADING)
            										.addGroup(
            											guiaEditorasLayout
            											.createSequentialGroup()
            											.addGroup(
            												guiaEditorasLayout
            												.createParallelGroup(GroupLayout.Alignment.TRAILING)
            												.addGroup(
            													guiaEditorasLayout
            														.createSequentialGroup()
            														.addComponent(publisherIdLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
            														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            														.addComponent(publisherId, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
            												)
            												.addGroup(
            													guiaEditorasLayout
            														.createSequentialGroup()
            														.addComponent(publisherNameLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
            														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            														.addComponent(publisherName, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
            												)
            											)
            											.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            											.addComponent(publisherTabSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            											.addComponent(publisherURLLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
            											.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            											.addComponent(publisherURL, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
            											.addGap(0, 0, Short.MAX_VALUE)
            										)
            										.addGroup(
            											guiaEditorasLayout
            												.createSequentialGroup()
            												.addGroup(
            													guiaEditorasLayout
            														.createParallelGroup(GroupLayout.Alignment.LEADING)
            														.addComponent(userInfoText4, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
            														.addGroup(
            															guiaEditorasLayout
            																.createSequentialGroup()
            																.addComponent(userInfoText3)
            																.addGap(0, 0, Short.MAX_VALUE)
            														)
            												)
            												.addGap(18, 18, 18)
            												.addComponent(insertPublisherButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
            												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            												.addComponent(updatePublisherButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
            												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            												.addComponent(deletePublisherButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
            												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            												.addComponent(searchPublisherButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
            										)
            								)
            						.addContainerGap()
            					)
            			)
            	)
        );
        
        guiaEditorasLayout
        	.setVerticalGroup(
                guiaEditorasLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                	.addGroup(
                		guiaEditorasLayout
                			.createSequentialGroup()
                			.addContainerGap()
                			.addComponent(publisherTabSeparator1)
                	)
                	.addGroup(
                		guiaEditorasLayout
                			.createSequentialGroup()
                			.addGap(204, 204, 204)
                			.addComponent(updatePublisher)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(searchPublisher, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(deletePublisher)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(insertPublisher)
                			.addGap(6, 237, Short.MAX_VALUE)
                	)
                	.addGroup(
                		guiaEditorasLayout
                			.createSequentialGroup()
                			.addGroup(
                				guiaEditorasLayout
                					.createParallelGroup(GroupLayout.Alignment.LEADING)
                					.addGroup(
                						guiaEditorasLayout
                							.createSequentialGroup()
                							.addGap(40, 40, 40)
                							.addGroup(
                								guiaEditorasLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(publisherId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                									.addComponent(publisherIdLabel)
                							)
                							.addGap(18, 18, 18)
                							.addGroup(
                								guiaEditorasLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(publisherName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                									.addComponent(publisherNameLabel)
                							)
                					)
                					.addGroup(
                						guiaEditorasLayout
                							.createSequentialGroup()
                							.addGap(16, 16, 16)
                							.addComponent(publisherTabSeparator3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                					)
                					.addGroup(
                						guiaEditorasLayout
                							.createSequentialGroup()
                							.addGap(40, 40, 40)
                							.addGroup(
                								guiaEditorasLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(publisherURLLabel)
                									.addComponent(publisherURL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							)
                					)
                			)
                			.addGroup(
                				guiaEditorasLayout
                					.createParallelGroup(GroupLayout.Alignment.LEADING)
                					.addGroup(
                						guiaEditorasLayout
                							.createSequentialGroup()
                							.addGap(99, 99, 99)
                							.addGroup(
                								guiaEditorasLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(insertPublisherButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(updatePublisherButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(deletePublisherButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(searchPublisherButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                							)
                					)
                					.addGroup(GroupLayout.Alignment.TRAILING, 
                						guiaEditorasLayout
                							.createSequentialGroup()
                							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                							.addComponent(userInfoText3)
                							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                							.addComponent(userInfoText4)
                					)
                			)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(publisherTabSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                			.addGap(12, 12, 12)
                			.addComponent(publisherScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                	)
            );
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String context = ((JButton) e.getSource()).getText();
		dispatchBDEvent(context);
	}
}
