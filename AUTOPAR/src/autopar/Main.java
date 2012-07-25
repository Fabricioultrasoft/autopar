package autopar;

import autopar.controller.GruposController;
import autopar.controller.MarcasController;
import autopar.controller.SubGruposController;
import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.controller.window.TelaPrincipalController;
import autopar.model.FlowThread;
import autopar.model.db.FirebirdDB;
import autopar.model.db.MySQLDB;
import autopar.window.TelaPrincipal;

public class Main {
	
	private static TelaPrincipal tela;
	private static FirebirdDB fb;
	private static MySQLDB ms;
	
	private static TelaPrincipalController telaController;
	private static FirebirdDBController fbController;
	private static MySQLDBController msController;
	/*private static GruposController gruposController;
	private static SubGruposController subGruposController;
	private static MarcasController marcasController;*/
	
	// ADICIONEI UM COMMENT
	
	private static FlowThread threadProdutosLocal;
	private static FlowThread threadProdutosWeb;
	private static FlowThread threadGrupos;
	private static FlowThread threadSubGrupos;
	private static FlowThread threadMarcas;
	
	public static void main(String[] args) throws InterruptedException {
		
		//showSplash();
		
		startTela();
		startDBs();
		startControllers();   
		telaController.show();
		
		loadProdutos();
		updateEstruturaWeb();
		
		waitFor(threadProdutosLocal);
		waitFor(threadProdutosWeb);
		telaController.compareLocalToWeb();
			
		waitFor(threadGrupos);
		waitFor(threadMarcas);
		waitFor(threadSubGrupos);
		
		//hideSplash();
		//telaController.show();
	}
	
	private static void startTela() {
		tela = new TelaPrincipal();
	}
	
	private static void startDBs() {
		fb = new FirebirdDB();
		ms = new MySQLDB();
	}
	
	private static void startControllers() {
		fbController = new FirebirdDBController(fb);
		msController = new MySQLDBController(ms);
		telaController = new TelaPrincipalController(tela);
		telaController.setFireBirdController(fbController);
		telaController.setMySQLController(msController);
	}
	
	private static void loadProdutos() {
		threadProdutosWeb = new FlowThread(telaController, "loadWeb");
		threadProdutosWeb.start();
		threadProdutosLocal = new FlowThread(telaController, "loadLocal");
		threadProdutosLocal.start();
	}
	
	private static void updateEstruturaWeb() {
		GruposController gruposController = new GruposController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(ms));
		SubGruposController subGruposController = new SubGruposController(new FirebirdDBController(new FirebirdDB()), 
																		  new MySQLDBController(new MySQLDB()));
		MarcasController marcasController = new MarcasController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(new MySQLDB()));
		
		threadSubGrupos = new FlowThread(subGruposController, "updateWeb");
		threadSubGrupos.start();
		threadGrupos = new FlowThread(gruposController, "updateWeb");
		threadGrupos.start();
		threadMarcas = new FlowThread(marcasController, "updateWeb");
		threadMarcas.start();
	}
	
	private static void waitFor(FlowThread t) throws InterruptedException {
		while (t.isAlive())
			Thread.sleep(100);
	}
}
