package autopar.window;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import autopar.controller.db.MySQLDBController;
import autopar.model.Produto;

public class TelaUploadImagens extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultListModel<String> _dados;
	private JList<String> v_imagens;
	private Produto _p;
	
	private JButton v_excluirButton, v_enviarButton, v_FecharButton; 

	/**
	 * Create the frame.
	 */
	public TelaUploadImagens(JFrame telaPrincipal, Produto p) {	
		super(telaPrincipal);
		
		this._p = p;
		
		this.setModalityType(Dialog.ModalityType.TOOLKIT_MODAL);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setTitle("Upload de Imagens");
		setBounds(100, 100, 518, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		v_excluirButton = new JButton("Excluir imagens selecionadas");
		v_excluirButton.setBounds(323, 72, 169, 23);
		v_excluirButton.setActionCommand("DELETE_FILE");
		contentPane.add(v_excluirButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Imagens", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 36, 310, 195);
		contentPane.add(panel);
		panel.setLayout(null);
		
		_dados = new DefaultListModel<String>();
		
		for (String img : _p.getImagens()) {
			_dados.addElement(img);
		}
		
		v_imagens = new JList<String>(_dados);
		v_imagens.setBounds(10, 23, 290, 161);
		panel.add(v_imagens);
		
		v_imagens.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		v_imagens.setVisibleRowCount(-1);
		v_imagens.setLayoutOrientation(JList.VERTICAL);

		JScrollPane _listScroller = new JScrollPane(v_imagens);
		_listScroller.setBackground(new Color(255, 255, 255, 0));
		//_listScroller.setPreferredSize(new Dimension(250, 80));
		_listScroller.setBounds(10, 22, 290, 162);
		panel.add(_listScroller);
		
		v_enviarButton = new JButton("Enviar nova imagem");
		
		v_enviarButton.setBounds(323, 46, 169, 23);
		v_enviarButton.setActionCommand("SEND_FILE");
		contentPane.add(v_enviarButton);
		
		JLabel lblNewLabel_1 = new JLabel("Produto:");
		lblNewLabel_1.setBounds(5, 11, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel v_nomeProduto = new JLabel(_p.getNome());
		v_nomeProduto.setBounds(55, 11, 440, 14);
		contentPane.add(v_nomeProduto);
		
		v_FecharButton = new JButton("Concluir");
		v_FecharButton.setActionCommand("CLOSE");
		v_FecharButton.setBounds(323, 208, 169, 23);
		contentPane.add(v_FecharButton);
		
	}
	
	public JList<String> getV_imagens() {
		return v_imagens;
	}

	public void setV_imagens(JList<String> v_imagens) {
		this.v_imagens = v_imagens;
	}

	public void setListener(ActionListener al) {
		v_enviarButton.addActionListener(al);
		v_excluirButton.addActionListener(al);
		v_FecharButton.addActionListener(al);
	}

	public Produto get_p() {
		return _p;
	}

	public void set_p(Produto _p) {
		this._p = _p;
	}

	public DefaultListModel<String> get_dados() {
		return _dados;
	}

	public void set_dados(DefaultListModel<String> _dados) {
		this._dados = _dados;
	}
	
}
