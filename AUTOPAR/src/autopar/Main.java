package autopar;

import autopar.controller.GruposController;
import autopar.controller.MarcasController;
import autopar.controller.SubGruposController;
import autopar.controller.ThreadController;
import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.controller.window.TelaPrincipalController;
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
	
	private static ThreadController threadProdutosLocal;
	private static ThreadController threadProdutosWeb;
	private static ThreadController threadGrupos;
	private static ThreadController threadSubGrupos;
	private static ThreadController threadMarcas;
	
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
		threadProdutosWeb = new ThreadController(telaController, "loadWeb");
		threadProdutosWeb.start();
		threadProdutosLocal = new ThreadController(telaController, "loadLocal");
		threadProdutosLocal.start();
	}
	
	private static void updateEstruturaWeb() {
		GruposController gruposController = new GruposController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(ms));
		SubGruposController subGruposController = new SubGruposController(new FirebirdDBController(new FirebirdDB()), 
																		  new MySQLDBController(new MySQLDB()));
		MarcasController marcasController = new MarcasController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(new MySQLDB()));
		
		threadSubGrupos = new ThreadController(subGruposController, "updateWeb");
		threadSubGrupos.start();
		threadGrupos = new ThreadController(gruposController, "updateWeb");
		threadGrupos.start();
		threadMarcas = new ThreadController(marcasController, "updateWeb");
		threadMarcas.start();
	}
	
	private static void waitFor(ThreadController t) throws InterruptedException {
		while (t.isAlive())
			Thread.sleep(100);
	}
}
