package autopar.model.table;

import java.util.ArrayList;

import javax.swing.JTable;


public class ProdutosTable extends JTable {
	
	private static final long serialVersionUID = 1L;
	public ArrayList<Integer> disabledRows;
	public ArrayList<Integer> modfiedRows;
	
	public ProdutosTable() {
		modfiedRows = new ArrayList<Integer>();
	}
	
	/*
	 * Esse metodo está sobreescrito para que, caso o modelo da tabela seja o ProdutosTableModel,
	 * ele aponte p/ o ArrayList de linhas desabilitadas (disabledRows).
	 */
	public void setSelectionModel(ProdutosTableListSelectionModel newModel) {
		//System.out.println(this.getClass()+" - Metodo setSelectionModel()");
		disabledRows = newModel.getDisabledRows();
		super.setSelectionModel(newModel);
	}
	
	public void alertModification(Integer i) {
		modfiedRows.add(i);
	}
	public void removeAlertModification(Integer i) {
		modfiedRows.remove(i);
	}
}
