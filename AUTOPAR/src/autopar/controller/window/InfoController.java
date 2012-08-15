package autopar.controller.window;

import autopar.window.Info;

public class InfoController {
	Info frame;
	
	public InfoController(Info frame) {
		this.frame = frame;
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void setMsg(String msg) {
		frame.lbl.setText(msg);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
}
