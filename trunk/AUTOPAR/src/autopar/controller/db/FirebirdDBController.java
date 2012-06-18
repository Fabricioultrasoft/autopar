package autopar.controller.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import autopar.model.Grupo;
import autopar.model.Marca;
import autopar.model.Produto;
import autopar.model.SubGrupo;
import autopar.model.db.FirebirdDB;

public class FirebirdDBController {
	
	FirebirdDB fb;
	Statement stmnt;
	Connection conn;
	
	public FirebirdDBController(FirebirdDB fb) {
		this.fb = fb;
		this.stmnt = fb.getStmnt();
		this.conn = fb.getConn();
	}
	
	public ArrayList<Produto> getProdutos() throws SQLException {
		ArrayList<Produto> ret = new ArrayList<Produto>();
		ResultSet rs = doQuery("SELECT * FROM "+fb.TAB_PRODUTO+" ORDER BY "+fb.NOME);
		while (rs.next()) {
			Produto p = new Produto(rs.getString(fb.CODIGO) 
								   ,rs.getString(fb.NOME)
								   ,rs.getString(fb.DESCRICAO)
								   ,rs.getString(fb.PRECO)
								   ,rs.getString(fb.CODIGO_MARCA)
								   ,rs.getString(fb.CODIGO_GRUPO));
			ret.add(p);
		}
		return ret;
	}
	
	public ArrayList<Grupo> getGrupos() {
		//TODO: IMPLEMENTAR
		return new ArrayList<Grupo>();
	}
	
	public ArrayList<SubGrupo> getSubGrupos() {
		//TODO: IMPLEMENTAR
		return new ArrayList<SubGrupo>();
	}
	
	public ArrayList<Marca> getMarcas() {
		//TODO: IMPLEMENTAR
		return new ArrayList<Marca>();
	}
	
	public ResultSet doQuery (String query) {
		ResultSet res = null;
		try 
		{
			stmnt = conn.createStatement();
			res = stmnt.executeQuery(query);
		} 
		catch (SQLException e) { e.printStackTrace(); }
		
		return res;
	}
	
	/*public ResultSet doQuery (String query, String[] params) {
		ResultSet res = null;
		PreparedStatement prst;
		try 
		{
			prst = conn.prepareStatement(query); 
			prst.setString(0,bla);
			res = prst.e
		} 
		catch (SQLException e) { e.printStackTrace(); }
		
		return res;
	}*/
}
