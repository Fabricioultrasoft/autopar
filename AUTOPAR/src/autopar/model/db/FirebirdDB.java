package autopar.model.db;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;
import java.sql.Statement; 

public class FirebirdDB {
	
	private String user = "sysdba";
	private String pass = "masterkey";
	private String path = "C:\\FB_DB\\CATALOGO.FDB";
	private String server = "jdbc:firebirdsql:127.0.0.1/3050:";
	private Statement stmnt;
	private Connection conn;
	
	//MAPEAMENTO BANCO DE DADOS
	public String TAB_PRODUTO = "PRODUTO";
	
	public String CODIGO = "CODIGO";
	public String NOME = "DESCRICAO";
	public String DESCRICAO = "DESCRICAOCOMPLEMENTAR";
	public String PRECO = "PRECO";
	public String CODIGO_MARCA = "CODMARCA";
	public String CODIGO_GRUPO = "CODGRUPOGERAL";
	public String CODIGO_SUB_GRUPO = "CODGRUPO";
	
	public FirebirdDB (String u, String p, String pt) {
		try 
		{
			user = u;
			pass = p;
			path = pt;
			
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			conn = DriverManager.getConnection(server+path, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	public FirebirdDB () {
		try 
		{	
			Class.forName("org.firebirdsql.jdbc.FBDriver");
			conn = DriverManager.getConnection(server+path, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}

	public Statement getStmnt() {
		return stmnt;
	}

	public Connection getConn() {
		return conn;
	}
	
}
