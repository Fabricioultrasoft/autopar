package autopar.controller.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.Produto;
import autopar.model.table.ProdutosTableModel;
import autopar.window.TelaPrincipal;

public class TelaPrincipalController implements ActionListener {
	
	private TelaPrincipal tela;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	
	public TelaPrincipalController(TelaPrincipal tela)
	{
		this.tela = tela;
		tela.setListener(this);
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
			removeFromWeb();
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
		//tela.modelWeb.addProduto(new Produto(prodsLocal.get(2).getCodigo(), prodsLocal.get(2).getNome()+"BOI"));
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
			if (tela.modelWeb.getCodigos().contains(cod))
				tela.alertModificationLocalRow(enableCods.get(cod));
		}
	}
	
	public void addToWeb() throws SQLException {
		int [] rows = tela.tableLocal.getSelectedRows();
		ArrayList<Produto> prodsLocal = tela.modelLocal.getProdutos();
		ProdutosTableModel web = tela.modelWeb;
		
		for (int i : rows) //Primeiro desabilita, para evitar re-envio
			tela.disableTableLocalRow(i);
		
		for (int i : rows) {
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
	}
	
	public void removeFromWeb() {
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
}
