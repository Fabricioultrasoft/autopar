package autopar.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDB {
	
	//depois mudar (usuário, senha).
	private String user = "root";
	private String pass = "123456";
	//nome do bd no final (mudar).
	private String server = "jdbc:mysql://127.0.0.1/autopar";
	private Statement stmnt;
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
	
	/*
	 * SUB GRUPO HAS GRUPO
	 */
	public String TAB_SUBGRUPO_HAS_GRUPO = "subgrupo_has_grupo";
	public String SHG_GRUPO_CODIGO = "grupo_id";
	public String SHG_SUBGRUPO_GRUPO_CODIGO = "subgrupo_id";
	
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

	public Statement getStmnt() {
		return stmnt;
	}

	public Connection getConn() {
		return conn;
	}
	
	
}
