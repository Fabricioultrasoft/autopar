package autopar.window;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class TelaInfo1 extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInfo frame = new TelaInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaInfo1() {
		setBounds(100, 100, 340, 204);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(131, 71, 46, 14);
		getContentPane().add(lblNewLabel);

	}

}
