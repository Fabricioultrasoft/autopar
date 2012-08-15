package autopar.window;

import javax.swing.*;

import java.awt.*;

public class SplashScreen extends JWindow {

	private static final long serialVersionUID = 1L;
	private static String splashImg = "/autopar/splash.png";
	
	BorderLayout borderLayout1 = new BorderLayout();
	JLabel imageLabel = new JLabel();
	JPanel southPanel = new JPanel();
	FlowLayout southPanelFlowLayout = new FlowLayout();
	public JProgressBar progressBar = new JProgressBar();
	ImageIcon imageIcon;

	public SplashScreen() {
	    this.imageIcon = new ImageIcon(SplashScreen.class.getResource(splashImg));
	    try {
	      jbInit();
	    }
	    catch(Exception ex) {
	      ex.printStackTrace();
	    }
	}

  // note - this class created with JBuilder
  void jbInit() throws Exception {
    imageLabel.setIcon(imageIcon);
    this.getContentPane().setLayout(borderLayout1);
    southPanel.setLayout(southPanelFlowLayout);
    southPanel.setBackground(null);
    this.getContentPane().add(imageLabel, BorderLayout.CENTER);
    this.getContentPane().add(southPanel, BorderLayout.SOUTH);
    progressBar.setPreferredSize(new Dimension(350,15));
    southPanel.add(progressBar, null);
    this.pack();
  }

}