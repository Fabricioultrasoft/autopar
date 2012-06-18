package autopar.window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JScrollPane;

import autopar.model.Produto;
import autopar.model.table.ProdutosTable;
import autopar.model.table.ProdutosTableCellRenderer;
import autopar.model.table.ProdutosTableListSelectionModel;
import autopar.model.table.ProdutosTableModel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JButton buttonSetaCima;
	private JButton buttonSetaBaixo;
	public JScrollPane scrollPaneLocal;
	public JScrollPane scrollPaneWeb;
	public ProdutosTable tableLocal;
	public ProdutosTable tableWeb;
	public ProdutosTableModel modelLocal;
	public ProdutosTableModel modelWeb;
	public ProdutosTableListSelectionModel selectionLocal;
	public ProdutosTableListSelectionModel selectionWeb;
	public ProdutosTableCellRenderer rendererLocal;
	public ProdutosTableCellRenderer rendererWeb;
	public JLabel lblCarregandoLocal;
	public JLabel lblCarregandoWeb;

	public TelaPrincipal() {		
		//FRAME
		super();
		setTitle("Autopar - Auto Peças // Sincronizador site");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//MENU
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 784, 21);
		contentPane.add(menuBar);
		JMenu mnOpes = new JMenu("Op\u00E7\u00F5es");
		menuBar.add(mnOpes);
		
		//SETAS
		buttonSetaBaixo = new JButton(); 
		buttonSetaBaixo.setBackground(Color.LIGHT_GRAY);
		buttonSetaBaixo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/autopar/baixo.png")));
		buttonSetaBaixo.setBounds(279, 267, 40, 44);
		buttonSetaBaixo.setActionCommand("ADD_TO_WEB");
		contentPane.add(buttonSetaBaixo);		
		
		buttonSetaCima = new JButton();  
		buttonSetaCima.setBackground(Color.LIGHT_GRAY);
		buttonSetaCima.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/autopar/cima.png")));  
		buttonSetaCima.setBounds(463, 268, 40, 44);
		buttonSetaCima.setActionCommand("REMOVE_FROM_WEB");
		contentPane.add(buttonSetaCima);
		
		/*
		 * LOCAL
		 */
		JPanel panelLocal = new JPanel();
		panelLocal.setBackground(new Color(95, 158, 160));
		panelLocal.setBounds(10, 32, 764, 224);
		contentPane.add(panelLocal);
		panelLocal.setLayout(null);
		
		scrollPaneLocal = new JScrollPane();
		scrollPaneLocal.setVisible(false);
		scrollPaneLocal.setBounds(10, 26, 744, 187);
		panelLocal.add(scrollPaneLocal);
		scrollPaneLocal.setBackground(new Color(95, 158, 160));
		
		tableLocal = createProdutosTableLocal();
		scrollPaneLocal.setViewportView(tableLocal);
		
		JLabel lblLocal = new JLabel("Itens locais");
		lblLocal.setForeground(Color.WHITE);
		lblLocal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLocal.setBounds(10, 1, 166, 14);
		panelLocal.add(lblLocal);
		
		lblCarregandoLocal = new JLabel("Carregando ...");
		lblCarregandoLocal.setBackground(Color.WHITE);
		lblCarregandoLocal.setForeground(Color.DARK_GRAY);
		lblCarregandoLocal.setBounds(186, 33, 385, 14);
		panelLocal.add(lblCarregandoLocal);
		
		
		/*
		 * WEB
		 */
		//TODO: Implementar WEB
		JPanel panelWeb = new JPanel();
		panelWeb.setBackground(Color.LIGHT_GRAY);
		panelWeb.setBounds(10, 327, 764, 224);
		contentPane.add(panelWeb);
		panelWeb.setLayout(null);
		
		scrollPaneWeb = new JScrollPane();
		scrollPaneWeb.setVisible(false);
		scrollPaneWeb.setBounds(10, 26, 744, 187);
		panelWeb.add(scrollPaneWeb);
		scrollPaneWeb.setBackground(new Color(95, 158, 160));
		
		tableWeb = createProdutosTableWeb();
		scrollPaneWeb.setViewportView(tableWeb);
		
		JLabel lblWeb = new JLabel("Itens Web");
		lblWeb.setForeground(Color.WHITE);
		lblWeb.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWeb.setBounds(10, 1, 166, 14);
		panelWeb.add(lblWeb);
		
		lblCarregandoWeb = new JLabel("Carregando ...");
		lblCarregandoWeb.setBackground(Color.WHITE);
		lblCarregandoWeb.setForeground(Color.DARK_GRAY);
		lblCarregandoWeb.setBounds(186, 33, 385, 14);
		panelWeb.add(lblCarregandoWeb);
	}
	
	public ProdutosTable createProdutosTableLocal() {
		Color foregroundColor = Color.red;
		ProdutosTable t = new ProdutosTable();
		ProdutosTableCellRenderer renderer = rendererLocal;
		ProdutosTableCellRenderer rendererNome = new ProdutosTableCellRenderer(foregroundColor); //O Renderer do nome tem que ser diferente
		renderer = new ProdutosTableCellRenderer(foregroundColor);
        modelLocal = new ProdutosTableModel();
        selectionLocal = new ProdutosTableListSelectionModel();
        
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setForeground(foregroundColor);
        rendererNome.setForeground(foregroundColor);
		t.setModel(modelLocal);
		t.setSelectionModel(selectionLocal);
		t.getColumnModel().getColumn(0).setMaxWidth(70);
		t.getColumnModel().getColumn(2).setMaxWidth(70);  
		t.getColumnModel().getColumn(0).setCellRenderer(renderer);
		t.getColumnModel().getColumn(2).setCellRenderer(renderer);
		t.getColumnModel().getColumn(1).setCellRenderer(rendererNome);
		
		return t;
	}
	
	public ProdutosTable createProdutosTableWeb() {
		Color foregroundColor = Color.black;
		ProdutosTable t = new ProdutosTable();
		ProdutosTableCellRenderer renderer = rendererWeb;
		ProdutosTableCellRenderer rendererNome = new ProdutosTableCellRenderer(foregroundColor); //O Renderer do nome tem que ser diferente
		renderer = new ProdutosTableCellRenderer(foregroundColor);  
        modelWeb = new ProdutosTableModel();
        selectionWeb = new ProdutosTableListSelectionModel();
        
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setForeground(foregroundColor);
        rendererNome.setForeground(foregroundColor);
		t.setModel(modelWeb);
		t.setSelectionModel(selectionWeb);
		t.getColumnModel().getColumn(0).setMaxWidth(70);
		t.getColumnModel().getColumn(2).setMaxWidth(70);  
		t.getColumnModel().getColumn(0).setCellRenderer(renderer);
		t.getColumnModel().getColumn(2).setCellRenderer(renderer);
		t.getColumnModel().getColumn(1).setCellRenderer(rendererNome);
		
		return t;
	}
	
	/*
	 * LOCAL
	 */
	public void loadLocal(ArrayList<Produto> produtos) {
		modelLocal.setProdutos(produtos);
		lblCarregandoLocal.setVisible(false);
		showLocalTable();
	}
	public void resetLocal() {
		lblCarregandoLocal.setVisible(true);
		hideLocalTable();
	}
	public void showLocalTable() {
		scrollPaneLocal.setVisible(true);
	}
	public void hideLocalTable() {
		scrollPaneLocal.setVisible(false);
	}
	public void disableTableLocalRow(int row) {
		//System.out.println(this.getClass()+" - Metodo disableTableLocalRow");
		selectionLocal.disable(row);
	}
	public void enableTableLocalRow(int row) {
		//System.out.println(this.getClass()+" - Metodo enableTableLocalRow");
		selectionLocal.enable(row);
	}
	public void alertModificationLocalRow(int row) {
		//System.out.println(this.getClass()+" - Metodo alertModificationLocalRow");
		tableLocal.alertModification(row);
		selectionLocal.addSelectionInterval(row, row);//Atualizar renderer
		selectionLocal.clearSelection();
	}
	public void removeAlertModificationLocalRow(int row) {
		//System.out.println(this.getClass()+" - Metodo removeAlertModificationLocalRow");
		tableLocal.removeAlertModification(row);
		selectionLocal.addSelectionInterval(row, row);//Atualizar renderer
		selectionLocal.clearSelection();
	}
	
	/*
	 * WEB
	 */
	public void loadWeb(ArrayList<Produto> produtos) {
		modelWeb.setProdutos(produtos);
		lblCarregandoWeb.setVisible(false);
		showWebTable();
	}
	public void resetWeb() {
		lblCarregandoWeb.setVisible(true);
		hideWebTable();
	}
	public void showWebTable() {
		scrollPaneWeb.setVisible(true);
	}
	public void hideWebTable() {
		scrollPaneWeb.setVisible(false);
	}
	
	//LISTENER
	public void setListener(ActionListener al)
	{
		buttonSetaBaixo.addActionListener(al);
		buttonSetaCima.addActionListener(al);
	}
}
