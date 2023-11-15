package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import entidade.Authors;
import LivrariaDAO.ConexaoDAO;

/** 
 * @Class: PainelAutor 
 * 	Essa classe é responsável de encapsular componentes que serão estruturados apenas para a aba de autor. 
 *  Contem vários componentes para os inputs de dados, descritivos ao usuário e de disparo de eventos .
 **/

@SuppressWarnings("serial")
public class PainelAutor extends JPanel implements View, ActionListener{

	// Variável constante estática que define os títulos das colunas da tabela
	private static final Object[] COLUMNS = {"Id", "Name", "FNAME"};
	
	//Componentes visuais que serão inseridos em tela
	JPanel guiaAutores = new JPanel();
	ButtonGroup buttonGroupAutores = new ButtonGroup();
	JRadioButton insertAuthor = new JRadioButton("Inserir");
	JRadioButton updateAuthor = new JRadioButton("Atualizar");
	JRadioButton deleteAuthor = new JRadioButton("Deletar");
	JRadioButton searchAuthor = new JRadioButton("Buscar");
	JLabel authorIdLabel = new JLabel();
	JLabel authorNameLabel = new JLabel();
	JLabel authorFNAMELabel = new JLabel();
	JLabel userInfoText1 = new JLabel();
	JLabel userInfoText2 = new JLabel();
	JTextField authorId = new JTextField();
	JTextField authorName = new JTextField();
	JTextField authorFNAME = new JTextField();
	JSeparator authorTabSeparator1 = new JSeparator();
	JSeparator authorTabSeparator2 = new JSeparator();
	JSeparator authorTabSeparator3 = new JSeparator();
	JScrollPane authorScrollPane = new JScrollPane();
	DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0);
    JTable authorTable = new JTable(tableModel);
	JButton insertAuthorButton = new JButton("Inserir");
    JButton updateAuthorButton = new JButton("Atualizar");
    JButton deleteAuthorButton = new JButton("Deletar");
    JButton searchAuthorButton = new JButton("Buscar");
    
	//Array de botões já criados, para que eu reduza o código e adicione eventListener em todos os botões
    JButton[] botoes = {insertAuthorButton, updateAuthorButton, deleteAuthorButton, searchAuthorButton};

	// Método publico para executar a inicialização da criação da guia Autor
	public JPanel createAutorTab() {
		init();
		return guiaAutores;
	}

	// Método herdado da classe abstrada View, onde inicializará os componentes e será criados seus respectivos espaços em tela
	// mantendo uniformidade 
	@Override
	public void init() {
		// Coloco no buttonGroup os radioButtons
		buttonGroupAutores.add(insertAuthor);
		buttonGroupAutores.add(updateAuthor);
		buttonGroupAutores.add(deleteAuthor);		
		buttonGroupAutores.add(searchAuthor);
		
		// Defino inicialmente que os botões de eventos que afetarão o BD serão inicialmente disabled
		insertAuthorButton.setEnabled(false);
		updateAuthorButton.setEnabled(false);
		deleteAuthorButton.setEnabled(false);
		searchAuthorButton.setEnabled(false);
		
		// Coloco os eventListeners nos botões que afetarão o BD
		setActionsListeners();
		
		// Crio eventListener nos radioButtons para que habilitem os botões de evento de BD
        setUpdateRadioListener(updateAuthor);
        setSearchRadioListener(searchAuthor);
        setDeleteRadioListener(deleteAuthor);
        setInsertRadioListener(insertAuthor);

		// Define a orietação (Vertical ou Horizal) para o separator
        authorTabSeparator1.setOrientation(SwingConstants.VERTICAL);

		// Defino os labels que indicará o dado que deve ser entrado
        authorIdLabel.setFont(new Font("Segoe UI", 1, 12));
        authorIdLabel.setText("Id");

        authorNameLabel.setFont(new Font("Segoe UI", 1, 12));
        authorNameLabel.setText("Nome");
        
        authorTabSeparator3.setOrientation(SwingConstants.VERTICAL);
        
        authorFNAMELabel.setFont(new Font("Segoe UI", 1, 12));
        authorFNAMELabel.setText("FNAF");
        
        authorScrollPane.setViewportView(authorTable);

        userInfoText1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userInfoText1.setText("Coloque os dados para pesquisar. ");

        userInfoText2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userInfoText2.setText("Obs: Se não inserir dados, fará uma pesquisa sem filtros.");
        
		//	Daqui em diante, é uma sequência de códigos que servirão para criar de forma estrturada os componentes em tela 

        GroupLayout guiaAutoresLayout = new javax.swing.GroupLayout(guiaAutores);
        
        guiaAutores.setLayout(guiaAutoresLayout);
        guiaAutoresLayout.setHorizontalGroup(
        		guiaAutoresLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(
        				guiaAutoresLayout.createSequentialGroup().addContainerGap()
        									.addGroup(
        											guiaAutoresLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        											.addComponent(insertAuthor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        											.addComponent(updateAuthor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        						                    .addComponent(deleteAuthor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        						                    .addComponent(searchAuthor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        									)
        					                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                .addComponent(authorTabSeparator1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
        					                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        					                .addGroup(
        					                		guiaAutoresLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        					                		.addComponent(authorTabSeparator2)
        					                		.addGroup(
        					                			guiaAutoresLayout.createSequentialGroup()
        					                				.addGroup(
        					                					guiaAutoresLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
        					                						.addGroup(
        					                							guiaAutoresLayout.createSequentialGroup()
        					                                            .addComponent(authorIdLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        					                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                                            .addComponent(authorId, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        					                						)
        					                						.addGroup(guiaAutoresLayout.createSequentialGroup()
        					                							.addComponent(authorNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
        					                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        					                                            .addComponent(authorName, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
            					                					)
        					                				)
        					                				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        					                                .addComponent(authorTabSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                                .addComponent(authorFNAMELabel,GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        					                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                                .addComponent(authorFNAME, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        					                                .addGap(331, 331, 331)
        					                		)
        					                        .addComponent(authorScrollPane)
        					                        .addGroup(
        					                        	guiaAutoresLayout.createSequentialGroup()
        					                            .addGroup(
        					                            	guiaAutoresLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
        					                                .addComponent(userInfoText2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					                                .addGroup(
        					                                	guiaAutoresLayout.createSequentialGroup()
        					                                    .addComponent(userInfoText1)
        					                                    .addGap(0, 0, Short.MAX_VALUE)
        					                                )
        					                            )
        					                            .addGap(18, 18, 18)
        					                            .addComponent(insertAuthorButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                            .addComponent(updateAuthorButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                            .addComponent(deleteAuthorButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        					                            .addComponent(searchAuthorButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        					                            .addContainerGap()
        					                        )
        					                )
        		)
        );
        
        guiaAutoresLayout.setVerticalGroup(
                guiaAutoresLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(guiaAutoresLayout.createSequentialGroup().addContainerGap().addComponent(authorTabSeparator1))
                .addGroup(
                	guiaAutoresLayout.createSequentialGroup()
                    	.addGap(204, 204, 204)
                    	.addComponent(updateAuthor)
                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	.addComponent(searchAuthor, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	.addComponent(deleteAuthor)
                    	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    	.addComponent(insertAuthor)
                    	.addGap(6, 237, Short.MAX_VALUE)
                )
                .addGroup(
                	guiaAutoresLayout.createSequentialGroup()
                    	.addGroup(
                    		guiaAutoresLayout
                    			.createParallelGroup(GroupLayout.Alignment.LEADING)
                    			.addGroup(
                    				guiaAutoresLayout.createSequentialGroup()
                    					.addGap(40, 40, 40)
                    					.addGroup(
                    						guiaAutoresLayout
                    							.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    							.addComponent(authorId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    							.addComponent(authorIdLabel)
                    					)
                    				.addGap(18, 18, 18)
                    				.addGroup(
                    					guiaAutoresLayout
                    						.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    						.addComponent(authorName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    						.addComponent(authorNameLabel)
                    				)
                    			)
                        .addGroup(guiaAutoresLayout.createSequentialGroup().addGap(16, 16, 16).addComponent(authorTabSeparator3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
                        .addGroup(
                        	guiaAutoresLayout.createSequentialGroup()
                            	.addGap(40, 40, 40)
                            	.addGroup(
                            		guiaAutoresLayout
                            			.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            			.addComponent(authorFNAMELabel)
                            			.addComponent(authorFNAME, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            	)
                        )
                      )
                .addGroup(
                    guiaAutoresLayout
                    	.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(
                        	guiaAutoresLayout
                        		.createSequentialGroup()
                        		.addGap(99, 99, 99)
                        		.addGroup(
                        			guiaAutoresLayout
                        			.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        			.addComponent(insertAuthorButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        			.addComponent(updateAuthorButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        			.addComponent(deleteAuthorButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchAuthorButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                )
                        )
                        .addGroup(GroupLayout.Alignment.TRAILING, guiaAutoresLayout
                        	.createSequentialGroup()
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userInfoText1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(userInfoText2)
                        )
                )
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(authorTabSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(authorScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        
	}

	// Evento de RadioButton que vai colocar o botão de inserir como enabled ou disabled
	private void setInsertRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					insertAuthorButton.setEnabled(true);
				} else {
					insertAuthorButton.setEnabled(false);
				}
			}
		});	
	}

	// Evento de RadioButton que vai colocar o botão de atualizar como enabled ou disabled
	private void setUpdateRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					updateAuthorButton.setEnabled(true);
				} else {
					updateAuthorButton.setEnabled(false);
				}
			}
		});	
	}
	
	// Evento de RadioButton que vai colocar o botão de deletar como enabled ou disabled
	private void setDeleteRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					deleteAuthorButton.setEnabled(true);
					authorName.setEnabled(false);
					authorFNAME.setEnabled(false);
				} else {
					deleteAuthorButton.setEnabled(false);
					authorName.setEnabled(true);
					authorFNAME.setEnabled(true);
				}
			}
		});	
	}
	
	// Evento de RadioButton que vai colocar o botão de buscar como enabled ou disabled
	private void setSearchRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					searchAuthorButton.setEnabled(true);
				} else {
					searchAuthorButton.setEnabled(false);
				}
			}
		});	
	}

	// Coloco eventos nos botões que farão alguma ação no BD 
	private void setActionsListeners() {
		for(JButton button: botoes) { button.addActionListener(this); }
	}

	// Método que faz o dispatch da ação, verificará qual botão foi clicado
	// Após identificar qual botão foi clicado, ele fará a ação para o objeto autor no BD
	@Override
	public void dispatchBDEvent(String context) {
		
		Long 	id = authorId.getText().isBlank()? null: Long.valueOf(authorId.getText());
		String 	name 	= authorName.getText(),
				fname	= authorFNAME.getText();
		
		Authors autor = new Authors(id, name, fname); 
		
		switch (context) {
			case "Buscar":	
				populaTable(new ConexaoDAO().buscarAutores(id, name, fname));
				cleanFields();
				break;
				
			case "Inserir":
				new ConexaoDAO().incluirAutor(autor);
				cleanFields();
				break;
				
			case "Atualizar":
				new ConexaoDAO().atualizarAutor(autor);
				cleanFields();
				break;
				
			case "Deletar":
				new ConexaoDAO().apagarAutor(autor.getId());
				cleanFields();
				break;
				
			default: break;
		}
	}
	
	// Método para limpar os campos após alguma ação no BD
	private void cleanFields() {
		authorId.setText("");
		authorName.setText("");
		authorFNAME.setText("");
	}
	
	// Ação para popular o banco de dados de acordo com os dados procurados
	private void populaTable(List<Authors> autores) {
		tableModel.setRowCount(0);

		for(Authors autor: autores) {
			tableModel.addRow(new Object[] { autor.getId(), autor.getName(), autor.getFname() });
		}
	}

	// Quando clicado no botão, essa ação override vai pegar o target, que seria o botão clicado, e chamar
	// o método que identiica qual ação deveria ser executada para executar
	@Override
	public void actionPerformed(ActionEvent e) {
		String context = ((JButton) e.getSource()).getText();
		dispatchBDEvent(context);
	}

}
