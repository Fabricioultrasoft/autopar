package autopar.controller.window;

import autopar.window.SplashScreen;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class SplashScreenController {
	
	private SplashScreen screen;
	private int progress;
	public String msg;
	
	public SplashScreenController(SplashScreen screen) {
		this.screen = screen;
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
			autopar.Main.msg.msgError(e.getMessage());
		}
	}
	
	public void splashScreenDestruct() {
	    setScreenVisible(false);
	}

	public void splashScreenInit() {
	    screen.setLocationRelativeTo(null);
	    setProgressMax(100);
	    setScreenVisible(true);
	}
	
	public void setProgressMax(int maxProgress)
	  {
	    screen.progressBar.setMaximum(maxProgress);
	  }

	  public void setProgress(int progress)
	  {
	    final int theProgress = progress;
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	      screen.progressBar.setValue(theProgress);
	      }
	    });
	  }

	  public void setProgress(String message, int progress)
	  {
	    this.progress = this.progress + progress;
	    final int theProg = this.progress;
	    final String theMessage = message;
	    this.msg = message;
	    setProgress(progress);
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	screen.progressBar.setValue(theProg);
	        setMessage(theMessage);
	      }
	    });
	  }

	  public void setScreenVisible(boolean b)
	  {
	    final boolean boo = b;
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	      screen.setVisible(boo);
	      }
	    });
	  }

	  private void setMessage(String message)
	  {
	    if (message==null)
	    {
	      message = "";
	      screen.progressBar.setStringPainted(false);
	    }
	    else
	    {
	      screen.progressBar.setStringPainted(true);
	    }
	      screen.progressBar.setString(message);
	  }
}
