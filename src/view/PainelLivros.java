package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import LivrariaDAO.ConexaoDAO;
import entidade.Books;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/** 
 * @Class: PainelLivros 
 * 	Essa classe é responsável de encapsular componentes que serão estruturados apenas para a aba de Livros. 
 *  Contem vários componentes para os inputs de dados, descritivos ao usuário e de disparo de eventos .
 **/

@SuppressWarnings("serial")
public class PainelLivros extends JPanel implements View, ActionListener{
	
	// Variável constante estática que define os títulos das colunas da tabela
	private static final Object[] COLUMNS = {"Id da editora", "Title", "Price", "isBn"};

	//Componentes visuais que serão inseridos em tela
	JPanel guiaLivros = new JPanel();
    ButtonGroup buttonGroupLivros = new ButtonGroup();
    JRadioButton insertBook = new JRadioButton("Inserir");
    JRadioButton updateBook = new JRadioButton("Atualizar");
    JRadioButton deleteBook = new JRadioButton("Deletar");
    JRadioButton searchBook = new JRadioButton("Buscar");
    JSeparator bookTabSeparator1 = new JSeparator();
    JSeparator bookTabSeparator2 = new JSeparator();
    JSeparator bookTabSeparator3 = new JSeparator();
    JLabel bookTitleLabel = new JLabel("Titulo");
    JLabel bookPriceLabel = new JLabel("Preço");
    JLabel bookIdPublisherLabel = new JLabel("Id da editora");
    JLabel bookIsBNLabel = new JLabel("isBN");
    JLabel userInfoText5 = new JLabel();
    JLabel userInfoText6 = new JLabel();
    JTextField bookTitle = new JTextField();
    JTextField bookPrice = new JTextField();
    JTextField bookIdPublisher = new JTextField();
    JTextField bookIsBN = new JTextField();
    JScrollPane bookScrollPane = new JScrollPane();
	DefaultTableModel tableModel = new DefaultTableModel(COLUMNS, 0);
    JTable bookTable = new JTable(tableModel);
    JButton insertBookButton = new JButton("Inserir");
    JButton updateBookButton = new JButton("Atualizar");
    JButton deleteBookButton = new JButton("Deletar");
    JButton searchBookButton = new JButton("Buscar");
    
	//Array de botões já criados, para que eu reduza o código e adicione eventListener em todos os botões
    JButton[] botoes = {insertBookButton, updateBookButton, deleteBookButton, searchBookButton};

	// Método publico para executar a inicialização da criação da guia Livros
	public JPanel createLivroTab() {
		init();
		setActionsListeners(); // Coloco os eventListeners nos botões que afetarão o BD
		return guiaLivros;
	}
	
	// Evento de RadioButton que vai colocar o botão de inserir como enabled ou disabled
	private void setInsertRadioListener(JRadioButton item) {
		item.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					insertBookButton.setEnabled(true);
				} else {
					insertBookButton.setEnabled(false);
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
					updateBookButton.setEnabled(true);
				} else {
					updateBookButton.setEnabled(false);
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
					deleteBookButton.setEnabled(true);
					bookPrice.setEnabled(false);
					bookTitle.setEnabled(false);
				} else {
					bookPrice.setEnabled(true);
					bookTitle.setEnabled(true);
					deleteBookButton.setEnabled(false);
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
					searchBookButton.setEnabled(true);
				} else {
					searchBookButton.setEnabled(false);
				}
			}
		});	
	}
	
	// Coloco eventos nos botões que farão alguma ação no BD 
	private void setActionsListeners() {
		for(JButton button: botoes) { button.addActionListener(this); }
	}
	
	// Método para limpar os campos após alguma ação no BD
	private void cleanFields() {
		bookIdPublisher.setText("");
		bookIsBN.setText("");
		bookPrice.setText("");
		bookTitle.setText("");
	}
	
	// Ação para popular o banco de dados de acordo com os dados procurados
	private void populaTable(List<Books> livros) {
		tableModel.setRowCount(0);

		for(Books livro: livros) {
			tableModel.addRow(new Object[] {livro.getPublisher_id(), livro.getTitle(), livro.getPrice(), livro.getIsbn()});
		}
	}

	// Método herdado da classe abstrada View, onde inicializará os componentes e será criados seus respectivos espaços em tela
	// mantendo uniformidade 
	@Override
	public void init() {
		// Coloco no buttonGroup os radioButtons
        buttonGroupLivros.add(updateBook);
        buttonGroupLivros.add(insertBook);
        buttonGroupLivros.add(deleteBook);
        buttonGroupLivros.add(searchBook);
        
		// Defino inicialmente que os botões de eventos que afetarão o BD serão inicialmente disabled
        insertBookButton.setEnabled(false);
        updateBookButton.setEnabled(false);
        deleteBookButton.setEnabled(false);
        searchBookButton.setEnabled(false);
        
		// Crio eventListener nos radioButtons para que habilitem os botões de evento de BD
        setUpdateRadioListener(updateBook);
        setSearchRadioListener(searchBook);
        setDeleteRadioListener(deleteBook);
        setInsertRadioListener(insertBook);
        
		// Define a orietação (Vertical ou Horizal) para o separator
        bookTabSeparator1.setOrientation(SwingConstants.VERTICAL);

		// Defino os labels que indicará o dado que deve ser entrado
        bookIdPublisherLabel.setFont(new Font("Segoe UI", 1, 12));
        bookTitleLabel.setFont(new Font("Segoe UI", 1, 12));
        bookPriceLabel.setFont(new Font("Segoe UI", 1, 12));
        bookIsBNLabel.setFont(new Font("Segoe UI", 1, 12));
        userInfoText5.setFont(new Font("Segoe UI", 1, 12));
        userInfoText6.setFont(new Font("Segoe UI", 1, 12));
        
        bookTabSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        bookScrollPane.setViewportView(bookTable);
        
        userInfoText5.setFont(new Font("Segoe UI", 1, 12)); 
        userInfoText5.setText("Coloque os dados para pesquisar. ");

        userInfoText6.setFont(new Font("Segoe UI", 1, 12));
        userInfoText6.setText("Obs: Se não inserir dados, fará uma pesquisa sem filtros.");

		//	Daqui em diante, é uma sequência de códigos que servirão para criar de forma estrturada os componentes em tela 
        GroupLayout guiaLivrosLayout = new javax.swing.GroupLayout(guiaLivros);
        
        guiaLivros.setLayout(guiaLivrosLayout);
        guiaLivrosLayout
        .setHorizontalGroup(
        	guiaLivrosLayout
        		.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addGroup(
        			guiaLivrosLayout
        				.createSequentialGroup()
        				.addContainerGap()
        				.addGroup(
        					guiaLivrosLayout
        						.createParallelGroup(GroupLayout.Alignment.LEADING)
        						.addComponent(insertBook, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        						.addComponent(updateBook, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        						.addComponent(deleteBook, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        						.addComponent(searchBook, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
        				)
        				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        				.addComponent(bookTabSeparator1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
        				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        				.addGroup(
        					guiaLivrosLayout
        						.createParallelGroup(GroupLayout.Alignment.LEADING)
        						.addComponent(bookTabSeparator2)
        						.addComponent(bookScrollPane)
        						.addGroup(
        							guiaLivrosLayout
        								.createSequentialGroup()
        								.addGroup(
        									guiaLivrosLayout
        										.createParallelGroup(GroupLayout.Alignment.LEADING)
        										.addGroup(
        											guiaLivrosLayout
        												.createSequentialGroup()
        												.addGroup(
        													guiaLivrosLayout
        														.createParallelGroup(GroupLayout.Alignment.TRAILING)
        														.addGroup(
        															guiaLivrosLayout
        																.createSequentialGroup()
        																.addComponent(bookTitleLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        																.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        														)
        														.addGroup(
        															guiaLivrosLayout
        																.createSequentialGroup()
        																.addComponent(bookPriceLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        																.addComponent(bookPrice, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
        														)
        												)
        												.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
        												.addComponent(bookTabSeparator3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        												.addGroup(
        													guiaLivrosLayout
        														.createParallelGroup(GroupLayout.Alignment.LEADING, false)
        														.addGroup(
        															guiaLivrosLayout
        																.createSequentialGroup()
        																.addComponent(bookIdPublisherLabel)
        																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        																.addComponent(bookIdPublisher, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        														)
        														.addGroup(
        															guiaLivrosLayout
        																.createSequentialGroup()
        																.addComponent(bookIsBNLabel)
        																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        																.addComponent(bookIsBN, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
        														)
        												)
        												.addGap(0, 0, Short.MAX_VALUE)
        										)
        										.addGroup(
        											guiaLivrosLayout
        												.createSequentialGroup()
        												.addGroup(
        													guiaLivrosLayout
        														.createParallelGroup(GroupLayout.Alignment.LEADING)
        														.addComponent(userInfoText6, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        														.addGroup(
        															guiaLivrosLayout
        																.createSequentialGroup()
        																.addComponent(userInfoText5)
        																.addGap(0, 0, Short.MAX_VALUE)
        														)
        												)
        												.addGap(18, 18, 18)
        												.addComponent(insertBookButton, GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
        												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        												.addComponent(updateBookButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        												.addComponent(deleteBookButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
        												.addComponent(searchBookButton, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
        										)
        								)
        						.addContainerGap()
        					)
        				)
        		)
        );
        
        guiaLivrosLayout
        	.setVerticalGroup(
                guiaLivrosLayout
                	.createParallelGroup(GroupLayout.Alignment.LEADING)
                	.addGroup(
                		guiaLivrosLayout
                			.createSequentialGroup()
                			.addContainerGap()
                			.addComponent(bookTabSeparator1)
                	)
                	.addGroup(
                		guiaLivrosLayout
                			.createSequentialGroup()
                			.addGap(204, 204, 204)
                			.addComponent(updateBook)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(searchBook, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(deleteBook, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(insertBook)
                			.addGap(6, 237, Short.MAX_VALUE)
                	)
                	.addGroup(
                		guiaLivrosLayout
                			.createSequentialGroup()
                			.addGroup(
                				guiaLivrosLayout
                					.createParallelGroup(GroupLayout.Alignment.LEADING)
                					.addGroup(
                						guiaLivrosLayout
                							.createSequentialGroup()
                							.addGap(40, 40, 40)
                							.addGroup(
                								guiaLivrosLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(bookTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                									.addComponent(bookTitleLabel)
                							)
                							.addGap(18, 18, 18)
                							.addGroup(
                								guiaLivrosLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(bookPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                									.addComponent(bookPriceLabel)
                							)
                					)
                					.addGroup(
                						guiaLivrosLayout
                							.createSequentialGroup()
                							.addGap(16, 16, 16)
                							.addComponent(bookTabSeparator3, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
                					)
                					.addGroup(
                						guiaLivrosLayout
                							.createSequentialGroup()
                							.addGap(40, 40, 40)
                							.addGroup(
                								guiaLivrosLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(bookIdPublisherLabel)
                									.addComponent(bookIdPublisher, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							)
                							.addGap(18, 18, 18)
                							.addGroup(
                								guiaLivrosLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(bookIsBNLabel)
                									.addComponent(bookIsBN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                							)
                					)
                			)
                			.addGroup(
                				guiaLivrosLayout
                					.createParallelGroup(GroupLayout.Alignment.LEADING)
                					.addGroup(
                						guiaLivrosLayout
                							.createSequentialGroup()
                							.addGap(99, 99, 99)
                							.addGroup(
                								guiaLivrosLayout
                									.createParallelGroup(GroupLayout.Alignment.BASELINE)
                									.addComponent(insertBookButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(updateBookButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(deleteBookButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                									.addComponent(searchBookButton, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                							)
                					)
                					.addGroup(GroupLayout.Alignment.TRAILING, 
                						guiaLivrosLayout
                							.createSequentialGroup()
                							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                							.addComponent(userInfoText5)
                							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                							.addComponent(userInfoText6)
                					)
                			)
                			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                			.addComponent(bookTabSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                			.addGap(12, 12, 12)
                			.addComponent(bookScrollPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                	)
            );
	}

	// Quando clicado no botão, essa ação override vai pegar o target, que seria o botão clicado, e chamar
	// o método que identiica qual ação deveria ser executada para executar
	@Override
	public void actionPerformed(ActionEvent e) {
		String context = ((JButton) e.getSource()).getText();
		dispatchBDEvent(context);
	}

	// Método que faz o dispatch da ação, verificará qual botão foi clicado
	// Após identificar qual botão foi clicado, ele fará a ação para o objeto autor no BD
	@Override
	public void dispatchBDEvent(String context) {
		
		Long 	id 		= bookIdPublisher.getText().isBlank()? null: Long.valueOf(bookIdPublisher.getText());
		Double 	price	= bookPrice.getText().isBlank()? null: Double.valueOf(bookPrice.getText());
		String 	title 	= bookTitle.getText(),
				isBn	= bookIsBN.getText();
		
		Books livro = new Books(title, isBn, id, price); 

		switch (context) {
			case "Buscar":	
				populaTable(new ConexaoDAO().buscarLivros(id, title, price, isBn));
				cleanFields();
				break;
				
			case "Inserir":
				new ConexaoDAO().incluirLivro(livro);
				cleanFields();
				break;
				
			case "Atualizar":
				new ConexaoDAO().atualizarLivro(livro);
				cleanFields();
				break;
				
			case "Deletar":
				new ConexaoDAO().apagarLivro(id, isBn);
				cleanFields();
				break;
				
			default:
				break;
		}
	}
}
