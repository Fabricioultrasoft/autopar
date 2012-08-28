package autopar.model.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class ProdutosTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private Color foregroundColor;
	
	public ProdutosTableCellRenderer (Color c) {
		foregroundColor = c;
	}
	
	public Component getTableCellRendererComponent (JTable table, Object value, boolean isSelected,
													boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
		if (table.getClass() != ProdutosTable.class) 
			return cell;

	    if (((ProdutosTable) table).disabledRows.contains(row))
	    	setCellDisabled(cell);
	    else {
	    	if (((ProdutosTable) table).modfiedRows.contains(row))
	    		setCellEnabled(cell, Color.yellow);
	    	else
	    		setCellEnabled(cell, foregroundColor);
	    }
	    return cell;
	}
	
	public void setCellDisabled(Component cell) {
		cell.setEnabled(false);
	}
	
	public void setCellEnabled(Component cell, Color color) {
		cell.setForeground(color);
		cell.setEnabled(true);
	}
}
