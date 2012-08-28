package autopar;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import autopar.controller.GruposController;
import autopar.controller.MarcasController;
import autopar.controller.SubGruposController;
import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.controller.window.TelaPrincipalController;
import autopar.controller.window.SplashScreenController;
import autopar.model.db.FirebirdDB;
import autopar.model.db.MySQLDB;
import autopar.model.flow.Flow;
import autopar.model.flow.FlowThread;
import autopar.window.Msg;
import autopar.window.TelaPrincipal;
import autopar.window.SplashScreen;


public class Main {
	
	private static SplashScreen splash;
	private static TelaPrincipal tela;
	private static FirebirdDB fb;
	private static MySQLDB ms;
	
	public static SplashScreenController splashController;
	private static TelaPrincipalController telaController;
	private static FirebirdDBController fbController;
	private static MySQLDBController msController;
	
	private static FlowThread threadProdutosLocal;
	private static FlowThread threadProdutosWeb;
	private static FlowThread threadGrupos;
	private static FlowThread threadSubGrupos;
	private static FlowThread threadMarcas;
	
	public static Flow flow;
	public static Msg msg;
	public static String tituloDestaque;
	
	public static void main(String[] args) {
		try {
			loadFlow();		
		} 
		catch (Exception e) {
			msg.msgError(e, Main.class);
		}
	}
	
	private static void startSplashAndSplashController() {
		splash = new SplashScreen();
		splashController = new SplashScreenController(splash);
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
	
	private static void loadTituloDestaque() throws SQLException {
		tituloDestaque = msController.getTituloDestaque();
	}
	
	private static void updateEstruturaWeb() throws InterruptedException {
		GruposController gruposController = new GruposController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(ms));
		SubGruposController subGruposController = new SubGruposController(new FirebirdDBController(new FirebirdDB()), 
																		  new MySQLDBController(new MySQLDB()));
		MarcasController marcasController = new MarcasController(new FirebirdDBController(new FirebirdDB()), 
																 new MySQLDBController(new MySQLDB()));
		
		threadMarcas = new FlowThread(marcasController, "updateWeb");
		threadMarcas.start();
		threadGrupos = new FlowThread(gruposController, "updateWeb");
		threadGrupos.start();
		waitFor(threadGrupos);
		threadSubGrupos = new FlowThread(subGruposController, "updateWeb");
		threadSubGrupos.start();
		
		waitFor(threadMarcas);
		waitFor(threadSubGrupos);
	}
	
	private static void waitFor(FlowThread t) throws InterruptedException {
		/*while (t.isAlive())
			Thread.sleep(100);*/
		t.join();
	}
	
	private static void loadFlow() throws InterruptedException, SQLException {
		msg = new Msg();
		
		flow = new Flow();
		
		startSplashAndSplashController();
		splashController.splashScreenInit();
		splashController.setProgress("Inicializando...", 5);
		
		startTela();
		startDBs();
		startControllers();   
		
		loadTituloDestaque();
		
		loadProdutos();
		updateEstruturaWeb();
		
		waitFor(threadProdutosLocal);
		waitFor(threadProdutosWeb);
		
		splashController
		.setProgress("Comparando produtos locais ("+tela.modelLocal.getProdutos().size()+") "
					 +"com web ("+tela.modelWeb.getProdutos().size()+")", 7);
		telaController.compareLocalToWeb();
		
		splashController.setProgress("Abrindo interface", 1);
		splashController.splashScreenDestruct();
		telaController.show();
	}
}
