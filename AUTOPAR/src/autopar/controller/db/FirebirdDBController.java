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
		ResultSet rs = doQuery("SELECT * FROM "+fb.TAB_PRODUTO+" ORDER BY "+fb.P_NOME);
		while (rs.next()) {
			Produto p = new Produto(rs.getString(fb.P_CODIGO) 
								   ,rs.getString(fb.P_NOME)
								   ,rs.getString(fb.P_DESCRICAO)
								   ,rs.getString(fb.P_PRECO)
								   ,rs.getString(fb.P_CODIGO_MARCA)
								   ,rs.getString(fb.P_CODIGO_SUB_GRUPO)
								   ,rs.getString(fb.P_CODIGO_GRUPO));
			ret.add(p);
		}
		return ret;
	}
	
	public ArrayList<Grupo> getGrupos() throws SQLException {
		ArrayList<Grupo> ret = new ArrayList<Grupo>();
		ResultSet rs = doQuery("SELECT * FROM "+fb.TAB_GRUPO);
		while (rs.next()) {
			Grupo g = new Grupo(rs.getString(fb.G_CODIGO_GRUPO)
							   ,rs.getString(fb.G_NOME));
			ret.add(g);
		}
		return ret;
	}
	
	public ArrayList<SubGrupo> getSubGrupos() throws SQLException {
		ArrayList<SubGrupo> ret = new ArrayList<SubGrupo>();
		ResultSet rs = doQuery("SELECT * FROM "+fb.TAB_SUB_GRUPO);
		while (rs.next()) {
			SubGrupo sg = new SubGrupo(rs.getString(fb.SG_CODIGO_SUB_GRUPO)
							   		  ,rs.getString(fb.SG_NOME));
			ret.add(sg);
		}
		return ret;
	}
	
	public ArrayList<Marca> getMarcas() throws SQLException {
		ArrayList<Marca> ret = new ArrayList<Marca>();
		ResultSet rs = doQuery("SELECT * FROM "+fb.TAB_MARCA);
		while (rs.next()) {
			Marca m = new Marca(rs.getString(fb.M_CODIGO_MARCA)
							   ,rs.getString(fb.M_NOME));
			ret.add(m);
		}
		return ret;
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
