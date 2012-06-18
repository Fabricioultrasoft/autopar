package autopar;

import autopar.controller.GruposController;
import autopar.controller.MarcasController;
import autopar.controller.SubGruposController;
import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.controller.window.TelaPrincipalController;
import autopar.model.db.FirebirdDB;
import autopar.model.db.MySQLDB;
import autopar.window.TelaPrincipal;

public class Main {
	
	private static TelaPrincipal tela;
	private static TelaPrincipalController telaController;
	private static FirebirdDB fb;
	private static MySQLDB ms;
	private static FirebirdDBController fbController;
	private static MySQLDBController msController;
	
	public static void main(String[] args) {
		
		startTela();
		//showSplash();
		
		startDBs();
		telaController.setFireBirdController(fbController);
		telaController.setMySQLController(msController);
		
		//THREAD 1
		telaController.loadLocal();
		//THREAD 2
		telaController.loadWeb();
		//THREAD 3
		updateEstruturaWeb();
		
		//Aguarda 1 e 2 terminarem, entao:
			//THREAD 1
			telaController.compareLocalToWeb();
			
		//Aguarda 3 terminar, então:
			telaController.show();
	}
	
	private static void startTela() {
		tela = new TelaPrincipal();
		telaController = new TelaPrincipalController(tela);
	}
	
	private static void startDBs() {
		fb = new FirebirdDB();
		ms = new MySQLDB();
		fbController = new FirebirdDBController(fb);
		msController = new MySQLDBController(ms);
	}
	
	private static void updateEstruturaWeb() {
		GruposController gc = new GruposController(fbController, msController);
		SubGruposController sgc = new SubGruposController(fbController, msController);
		MarcasController mc = new MarcasController(fbController, msController);
		//THREAD 3.1
		sgc.updateWeb();
		//THREAD 3.2
		gc.updateWeb();
		//Aguarda 3.2 terminar, entao:
			mc.updateWeb();
	}
}
