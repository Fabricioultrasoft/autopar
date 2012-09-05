package autopar.controller.window;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.Produto;
import autopar.model.table.ProdutosTableModel;
import autopar.window.TelaPrincipal;
import autopar.window.TelaUploadImagens;

import autopar.window.Msg;

public class TelaPrincipalController extends MouseAdapter implements ActionListener {
	
	private TelaPrincipal tela;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	private Msg msg = autopar.Main.msg;
	
	public TelaPrincipalController(TelaPrincipal tela)
	{
		this.tela = tela;
		tela.setListener(this);
		tela.setMouseListener(this);
	}

	public void show()
	{
		tela.setVisible(true);
	}
	
	/*
	 * EVENTOS
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("ADD_TO_WEB")) {
			try {
				addToWeb();
			} catch (SQLException e) {
				msg.msgError(e, this);
			}
		}
		if (ae.getActionCommand().equals("REMOVE_FROM_WEB")) {
			try {
				removeFromWeb();
			} catch (SQLException e) {
				msg.msgError(e, this);
			}
		}
		if (ae.getActionCommand().equals("SAIR")) {
			System.exit(0);
		}
		if (ae.getActionCommand().equals("ALT_DESTAQUE")) {
			editTituloDestaque();
		}
		if (ae.getActionCommand().equals("IR_PG")) {
			openSite();
		}
	}
	public void mouseClicked(MouseEvent e) {
		if((e.getClickCount() >= 2) && (e.getButton() == MouseEvent.BUTTON1))
		{
			int indice = tela.tableWeb.getSelectedRow();
			ArrayList<Produto> prodsWeb = tela.modelWeb.getProdutos();
			TelaUploadImagensController t = new TelaUploadImagensController(tela, msc, prodsWeb.get(indice));
			t.show();
		}
	}
	/*
	 * FIM EVENTOS
	 */
	
	/*
	 * LOCAL
	 */
	public void loadLocal() {
		try {
			tela.loadLocal(fbc.getProdutos());
		} 
		catch (SQLException e) {
			msg.msgError(e, this);
		}
	}
	public void reloadLocal() {
		tela.resetLocal();
		loadLocal();
	}
	
	/*
	 * WEB
	 */
	public void loadWeb() {
		try {
			tela.loadWeb(msc.getProdutos());
		} 
		catch (SQLException e) {
			msg.msgError(e, this);
		}
	}
	public void reloadWeb() {
		tela.resetWeb();
		loadWeb();	
	}
	
	/*
	 * COMPARE
	 */
	public void compareLocalToWeb() 
	{
		ArrayList<Produto> prodsLocal = tela.modelLocal.getProdutos();
		ArrayList<Produto> prodsWeb = tela.modelWeb.getProdutos();
		
		HashMap<String, Integer> enableCods = new HashMap<String, Integer>();
		
		int i = 0;
		int p = prodsLocal.size() / 10;
		
		for(Produto localProd : prodsLocal) {
			if (i % p == 0)
				autopar.Main.splashController
				.setProgress("Comparando produtos locais ("+prodsLocal.size()+") "
						 +"com web ("+prodsWeb.size()+")", 2);
			
			if (prodsWeb.contains(localProd))
				tela.disableTableLocalRow(i);
			else
				enableCods.put(prodsLocal.get(i).getCodigo(), i);
			i++;
		}
		
		for (String cod : enableCods.keySet())
		{
			if (tela.modelWeb.getCodigos().contains(Integer.parseInt(cod)))
				tela.alertModificationLocalRow(enableCods.get(cod));
		}
	}
	
	public void addToWeb() throws SQLException {
		int [] rows = tela.tableLocal.getSelectedRows();
		ArrayList<Produto> prodsLocal = tela.modelLocal.getProdutos();
		ProdutosTableModel web = tela.modelWeb;
		
		for (int i : rows) //Primeiro desabilita, para evitar re-envio
			tela.disableTableLocalRow(i);
		
		//int rowCount = rows.length;
		//infoc.setMsg("Enviando produtos ("+rowCount+")...");
		//infoc.show();
		for (int i : rows) {
			//infoc.setMsg("Enviando produto "+i+" de "+rowCount+".");
			Produto p = prodsLocal.get(i);
			if (tela.tableLocal.modfiedRows.contains(prodsLocal.indexOf(p))) {
				if (msc.atualizaProduto(p)) {
					web.atualizaProduto(p);
					tela.selectionLocal.clearSelection();
					int row = tela.modelWeb.getProdutos().indexOf(p);
					tela.selectionWeb.setSelectionInterval(row, row);
					tela.tableLocal.removeAlertModification(i);
				}
				else
					tela.enableTableLocalRow(i);
			}
			else {
				if (msc.addProduto(p)) {
					web.addProduto(p);
					tela.selectionLocal.clearSelection();
					tela.tableLocal.removeAlertModification(i);
				}
				else 
					tela.enableTableLocalRow(i);
			}
		}
		//infoc.hide();
	}
	
	public void removeFromWeb() throws SQLException {
		ArrayList<Produto> prodsWeb = tela.modelWeb.getProdutos();
		ArrayList<Produto> prodsLocal = tela.modelLocal.getProdutos();
		ProdutosTableModel web = tela.modelWeb;
		
		int i;
		int rows = tela.tableWeb.getSelectedRows().length;
		
		for (int x=0; x<rows; x++) {
			i = tela.tableWeb.getSelectedRows()[0];
			Produto p = prodsWeb.get(i);
			if (msc.removeProduto(p)) {
				web.removeProduto(p, i);
				int row = prodsLocal.indexOf(p);
				if (row != -1)
				{
					tela.enableTableLocalRow(row);
					tela.selectionLocal.addSelectionInterval(row, row);//Atualizar renderer
					tela.selectionLocal.clearSelection();
				}
			}
		}
	}
	
	public void editTituloDestaque() {
		String newTitulo = msg.input(tela, "Alterar título de destaque", "Título destaque:", autopar.Main.tituloDestaque);
		if (newTitulo != null && !newTitulo.isEmpty())
			if (msc.atualizaDestaque(newTitulo))
				autopar.Main.tituloDestaque = newTitulo;
	}
	
	public void openSite() {
		 Desktop desktop = null;  
		 desktop = Desktop.getDesktop();  
		 URI uri = null;  
		 try {  
		            uri = new URI("http://www.autopar.com.br");  
		            desktop.browse(uri);  
		 }  
		 catch(Exception ioe) {  
		            msg.msg("Erro ao abrir o site!"); 
		 }
	}
	
	public void setFireBirdController(FirebirdDBController fbc) {
		this.fbc = fbc;
	}
	public void setMySQLController(MySQLDBController msc) {
		this.msc = msc;
	}
	
	public void reDesenharTableWeb() {
		tela.modelWeb.fireTableDataChanged();
	}
}
