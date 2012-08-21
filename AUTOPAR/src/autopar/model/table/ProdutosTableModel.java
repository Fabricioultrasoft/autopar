package autopar.model.table;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import autopar.model.Produto;

public class ProdutosTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Produto> produtos;
	private ArrayList<String> atributos;
	private ArrayList<String> colunas; 
	
	public ProdutosTableModel () {
		produtos = new ArrayList<Produto>();
		atributos = new ArrayList<String>();
		colunas = new ArrayList<String>(); 
		
		addAtributo("Código", "Codigo");
		addAtributo("Nome", "Nome");
		addAtributo("Imagens", "NumImagens");
	}
	
	/**
	 * 
	 * @param coluna: Descrição da coluna que aparece na table;
	 * @param metodo: Nome do metodo na classe (sem o get, ou o set). Ex: getNome, param metodo=Nome;
	 */
	public void addAtributo (String coluna, String metodo) {
		colunas.add(coluna);
		atributos.add(metodo);
	}
	
	public void addProduto(Produto p) {
		produtos.add(p);
		fireTableRowsInserted(produtos.size(), produtos.size());
	}
	public void atualizaProduto(Produto p) {
		for (Produto x : produtos) {
			if (x.getCodigo().equals(p.getCodigo()))
				produtos.set(produtos.indexOf(x), p);
		}
	}
	public void removeProduto(Produto p, int linha) {
		produtos.remove(p);
		fireTableRowsDeleted(linha, linha);
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
		fireTableDataChanged();
	}
	public Produto getProduto(int i) {
		return produtos.get(i);
	}
	public ArrayList<Integer> getCodigos() {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (Produto p : produtos)
			ret.add(Integer.parseInt(p.getCodigo()));
		
		return ret;
	}
	
	@Override
	public int getColumnCount() {
		return colunas.size();
	}

	@Override
	public int getRowCount() {
		return produtos.size();
	}
	
	@Override
	public Object getValueAt(int linha, int coluna) {
		try {
			Produto prod = produtos.get(linha);
			String metodo = atributos.get(coluna);
			
			return prod.getClass().getMethod("get"+metodo).invoke(prod);
		} catch (Exception e) {
			//TODO: alert!
			System.out.println("Erro getValueAt: (L:"+linha+", C:"+coluna+")"+ e.getMessage());
			return null;
		}
	}
	
	@Override
	public void setValueAt(Object obj, int linha, int coluna) 
	{
		try {
			Produto prod = produtos.get(linha);
			String atrib = atributos.get(coluna);
			
			prod.getClass().getMethod("set"+atrib).invoke(prod, obj);
			fireTableCellUpdated(linha, coluna);  
		} catch (Exception e) {
			System.out.println("Erro setValueAt: (L:"+linha+", C:"+coluna+")"+ e.getMessage());
			//TODO: alert!
		}
	};
	
	@Override
	public String getColumnName(int col) {
		return colunas.get(col);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
}
