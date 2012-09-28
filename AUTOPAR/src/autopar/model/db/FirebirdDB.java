package autopar.model.db;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.Properties;

import autopar.window.Msg;

public class FirebirdDB {
	
	private final Properties config = autopar.Main.config;
	
	private String user = config.getProperty("userFB");
	private String pass = config.getProperty("passFB");
	private String path = config.getProperty("pathFB");
	private String server = config.getProperty("serverFB");
	private Statement stmnt;
	private Connection conn;
	
	private Msg msg = autopar.Main.msg;
	
	//MAPEAMENTO BANCO DE DADOS
	
	/*
	 * PRODUTO
	 */
	public String TAB_PRODUTO = "PRODUTO";
	public String P_CODIGO = "CODIGO";
	public String P_NOME = "DESCRICAO";
	public String P_DESCRICAO = "DESCRICAOCOMPLEMENTAR";
	public String P_PRECO = "PRECO";
	public String P_CODIGO_MARCA = "CODMARCA";
	public String P_CODIGO_GRUPO = "CODGRUPOGERAL";
	public String P_CODIGO_SUB_GRUPO = "CODGRUPO";
	
	/*
	 * GRUPO
	 */
	public String TAB_GRUPO = "GRUPOGERAL";
	public String G_CODIGO_GRUPO = "CODGRUPOGERAL";
	public String G_NOME = "DESCRICAO";
	
	/*
	 * SUB GRUPO
	 */
	public String TAB_SUB_GRUPO = "GRUPO";
	public String SG_CODIGO_SUB_GRUPO = "CODGRUPO";
	public String SG_NOME = "DESCRICAO";
	
	/*
	 * MARCA
	 */
	public String TAB_MARCA = "MARCA";
	public String M_CODIGO_MARCA = "CODMARCA";
	public String M_NOME = "DESCRICAO";
	
	public FirebirdDB (String s, String u, String p, String pt) {
		try 
		{
			this.server = s;
			user = u;
			pass = p;
			path = pt;
			
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			conn = DriverManager.getConnection(server+path, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { msg.msgError("Erro no driver firebird! \n"+e.getMessage()); } 
		catch (SQLException e) { msg.msgError("Erro ao conectar no banco de dados local! \n"
								+"Certifique-se de que o servidor local está rodando corretamente. \n\n" 
								+e.getMessage()); }
	}
	
	public FirebirdDB () {
		try 
		{	
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			conn = DriverManager.getConnection(server+path, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { msg.msgError("Erro no driver firebird! \n"+e.getMessage()); } 
		catch (SQLException e) { msg.msgError("Erro ao conectar no banco de dados local! \n"
								+"Certifique-se de que o servidor local está rodando corretamente. \n\n" 
								+e.getMessage()); }
	}

	public Statement getStmnt() {
		return stmnt;
	}

	public Connection getConn() {
		return conn;
	}
	
}
