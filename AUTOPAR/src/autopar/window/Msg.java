package autopar.window;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Msg {
	public void msg(Component comp, String msg) {
		JOptionPane.showMessageDialog(comp, msg); 
	}
	
	public void msg(String msg) {
		JOptionPane.showMessageDialog(null, msg); 
	}
	
	public void msgError(Component comp, String msg) {
		JOptionPane.showMessageDialog(comp, msg); 
		System.exit(0);
	}
	
	public void msgError(String msg) {
		JOptionPane.showMessageDialog(null, msg); 
		System.exit(0);
	}
	
	public void msgError(Exception e, Object obj) {
		JOptionPane.showMessageDialog(null, e.getMessage()+"\n\n"+obj.getClass()); 
		System.exit(0);
	}
	
	public void msgError(Exception e) {
		JOptionPane.showMessageDialog(null, e.getCause().getMessage()); 
		System.exit(0);
	}
	
	public String input(Component comp, String titulo, String msg, String valorInicial) {
		String ret = 
		(String) JOptionPane.showInputDialog(comp, 
									msg, 
									titulo, 
									JOptionPane.PLAIN_MESSAGE, 
									null, 
									null, 
									valorInicial);
		return ret;
	}
}