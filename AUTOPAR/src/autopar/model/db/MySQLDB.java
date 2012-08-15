package autopar.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDB {
	
	private String user = "autopar1";
	private String pass = "welovetevo1";
	private String server = "jdbc:mysql://dbmy0062.whservidor.com:3306/autopar1?autoReconnectForPools=true";
	private Connection conn;
	
	//MAPEAMENTO BANCO DE DADOS
	
	/*
	 * MARCAS (fabricantes)
	 */
	public String TAB_MARCA = "fabricantes";
	public String M_CODIGO_MARCA = "id";
	public String M_NOME = "nome";
	
	/*
	 * GRUPO
	 */
	public String TAB_GRUPO = "grupo";
	public String G_CODIGO_GRUPO = "id";
	public String G_NOME = "nome";
	
	/*
	 * PRODUTOS
	 */
	public String TAB_PRODUTO = "produtos";
	public String P_CODIGO = "id";
	public String P_NOME = "nome";
	public String P_DESCRICAO = "descricao";
	public String P_PRECO = "preco_prazo";
	public String P_CODIGO_MARCA = "fabricante";
	public String P_CODIGO_GRUPO = "grupo";
	public String P_CODIGO_SUB_GRUPO = "subgrupo";
			
	/*
	 * PRODUTOS_FOTOS
	 */
	public String TAB_PRODUTOS_FOTOS = "produtos_fotos";
	public String PF_CODIGO = "id";
	public String PF_NOME = "nome";
	public String PF_PRODUTO = "produto";
	
	/*
	 * SUB GRUPO
	 */
	public String TAB_SUB_GRUPO = "subgrupo";
	public String SG_CODIGO_SUB_GRUPO = "id";
	public String SG_NOME = "nome";
	
	public MySQLDB (String u, String p) {
		try 
		{
			user = u;
			pass = p;
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(server, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	public MySQLDB () {
		try 
		{	
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(server, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); }
	}
	
	public void reconnect() {
		System.out.println("Reconnect");
		try 
		{	
			conn.close();
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(server, user, pass);
		} 
		
		/* EXCEPTIONS */
		catch (ClassNotFoundException e) { e.printStackTrace(); } 
		catch (SQLException e) { e.printStackTrace(); } 
		catch (InstantiationException e) { e.printStackTrace(); } 
		catch (IllegalAccessException e) { e.printStackTrace(); }
	}
	
	public Statement getStmnt() {
		try {
			
			if (!conn.isValid(1000) || conn.isClosed())
				reconnect();
			/*else
				System.out.println("Conexao OK");*/
			
			return conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public Connection getConn() {
		return conn;
	}
	
}
