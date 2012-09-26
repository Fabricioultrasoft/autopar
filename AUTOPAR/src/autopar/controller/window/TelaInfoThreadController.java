package autopar.controller.window;

import autopar.window.TelaInfo;

public class TelaInfoThreadController extends Thread {
	TelaInfo tela;
	
	public TelaInfoThreadController (TelaInfo tela) {
		this.tela = tela;
	}
	
	public void run () {
		show();
	}
	
	public void show() {
		System.out.println("showInfo");
		tela.setVisible(true);
	}
	public void hide() {
		System.out.println("hideInfo");
		tela.setVisible(false);
	}
}
