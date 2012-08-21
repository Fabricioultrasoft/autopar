package autopar.controller.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.Produto;
import autopar.model.table.ProdutosTableModel;
import autopar.window.TelaPrincipal;
import autopar.window.TelaUploadImagens;

public class TelaPrincipalController extends MouseAdapter implements ActionListener {
	
	private TelaPrincipal tela;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	private InfoController infoc;
	
	public TelaPrincipalController(TelaPrincipal tela)
	{
		this.tela = tela;
		tela.setListener(this);
		tela.setMouseListener(this);
		infoc = new InfoController(new autopar.window.Info());
	}

	public void show()
	{
		tela.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		//TODO: Implementar completamente
		System.out.println("Comando:"+ae.getActionCommand());
		
		if (ae.getActionCommand().equals("ADD_TO_WEB")) {
			try {
				addToWeb();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ae.getActionCommand().equals("REMOVE_FROM_WEB")) {
			try {
				removeFromWeb();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * LOCAL
	 */
	public void loadLocal() {
		try {
			tela.loadLocal(fbc.getProdutos());
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		for(Produto localProd : prodsLocal) {
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
		
		int rowCount = rows.length;
		infoc.setMsg("Enviando produtos ("+rowCount+")...");
		infoc.show();
		for (int i : rows) {
			infoc.setMsg("Enviando produto "+i+" de "+rowCount+".");
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
		infoc.hide();
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
	
	public void setFireBirdController(FirebirdDBController fbc) {
		this.fbc = fbc;
	}
	public void setMySQLController(MySQLDBController msc) {
		this.msc = msc;
	}
	
	public void mouseClicked(MouseEvent e) {
		if((e.getClickCount() >= 2) && (e.getButton() == MouseEvent.BUTTON1))
		{
			int indice = tela.tableWeb.getSelectedRow();
			ArrayList<Produto> prodsWeb = tela.modelWeb.getProdutos();
			TelaUploadImagensController t = new TelaUploadImagensController(new TelaUploadImagens(tela, prodsWeb.get(indice)));
			t.setMsc(msc);
			t.show();
		}
	}
}
