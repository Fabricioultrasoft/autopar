package autopar.model.table;

import java.util.ArrayList;

import javax.swing.DefaultListSelectionModel;

public class ProdutosTableListSelectionModel extends DefaultListSelectionModel {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> disabledRows;
	
	public ProdutosTableListSelectionModel() {
		disabledRows = new ArrayList<Integer>();
	}
	
	public void disable(int i) {
		//System.out.println(this.getClass()+" : Disable "+i);
		disabledRows.add(i);
	}
	
	public void enable(int i) {
		//System.out.println(this.getClass()+" : Enable "+i);
		disabledRows.remove((Integer) i);
	}
	
	@Override
	public boolean isSelectedIndex(int index) {
		//System.out.println(this.getClass()+" : Metodo isSelectedIndex => "+index);
    	boolean isSelected;
    	if (disabledRows.contains(index))
    		isSelected = false;
    	else
    		isSelected = super.isSelectedIndex(index);

    	return isSelected;
    }
	
	public ArrayList<Integer> getDisabledRows() {
		return disabledRows;
	}
}
