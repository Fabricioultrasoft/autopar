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
import autopar.model.db.MySQLDB;

public class MySQLDBController {
	MySQLDB ms;
	Statement stmnt;
	Connection conn;
	
	public MySQLDBController(MySQLDB ms) {
		this.ms = ms;
		this.stmnt = ms.getStmnt();
		this.conn = ms.getConn();
	}
	
	/*
	 * MARCAS (fabricantes)
	 */
	
	//ok
	public boolean addMarca(Marca m) throws SQLException {
		/*int val = stmnt.executeUpdate("INSERT INTO "+ms.TAB_MARCA+" ("
									  +ms.M_CODIGO_MARCA+","
									  +ms.M_NOME
									  +") VALUES ("
									  +m.getCodigoMarca()+","
									  +"'"+m.getNome()+"')");
		if(val == 1)
			return true;
		else
			return false;*/return true;
	}
	public boolean removeMarca(Marca m) {
		//TODO: IMPLEMENTAR
		return true;
	}

	//ok
	public ArrayList<Marca> getMarcas() throws SQLException {
		ArrayList<Marca> ret = new ArrayList<Marca>();
		ResultSet rs = doQuery("SELECT * FROM "+ms.TAB_MARCA);
		while (rs.next()) {
			Marca m = new Marca(rs.getString(ms.M_CODIGO_MARCA)
							   ,rs.getString(ms.M_NOME));
			ret.add(m);
		}
		return ret;
	}
	
	/*
	 * GRUPO
	 */
	public boolean addGrupo(Grupo g) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public boolean removeGrupo(Grupo g) {
		//TODO: IMPLEMENTAR
		return true;
	}
	
	//ok
	public ArrayList<Grupo> getGrupos() throws SQLException {
		ArrayList<Grupo> ret = new ArrayList<Grupo>();
		ResultSet rs = doQuery("SELECT * FROM "+ms.TAB_GRUPO);
		while (rs.next()) {
			Grupo g = new Grupo(rs.getString(ms.G_CODIGO_GRUPO)
							   ,rs.getString(ms.G_NOME));
			ret.add(g);
		}
		return ret;
	}
	
	/*
	 * PRODUTOS
	 */
	
	//ok
	public ArrayList<Produto> getProdutos() throws SQLException {
		ArrayList<Produto> ret = new ArrayList<Produto>();
		ResultSet rs = doQuery("SELECT * FROM "+ms.TAB_PRODUTO+" ORDER BY "+ms.P_NOME);
		while (rs.next()) {
			Produto p = new Produto(rs.getString(ms.P_CODIGO) 
								   ,rs.getString(ms.P_NOME)
								   ,rs.getString(ms.P_DESCRICAO)
								   ,rs.getString(ms.P_PRECO)
								   ,rs.getString(ms.P_CODIGO_MARCA)
								   ,rs.getString(ms.P_CODIGO_SUB_GRUPO)
								   ,rs.getString(ms.P_CODIGO_GRUPO));
			ret.add(p);
		}
		return ret;
	}
	
	//ok
	public boolean addProduto(Produto p) throws SQLException {
		int val = stmnt.executeUpdate("INSERT INTO "+ms.TAB_PRODUTO+" ("
							+ms.P_CODIGO+","
							+ms.P_NOME+","
							+ms.P_DESCRICAO+","
							+ms.P_PRECO+","
							+ms.P_CODIGO_MARCA+","
							+ms.P_CODIGO_GRUPO+","
							+ms.P_CODIGO_SUB_GRUPO
							+") VALUES ("+
							p.getCodigo()
							+",'"+p.getNome()+"'"
							+",'"+p.getDescricao()+"'"
							+","+p.getPreco()
							+","+p.getCodigoMarca()
							+","+p.getCodigoGrupo()
							+","+p.getCodigoSubGrupo()
							+")");
		if(val == 1)
			return true;
		else
			return false;
	}
	public boolean atualizaProduto(Produto p) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public boolean removeProduto(Produto p) {
		//TODO: IMPLEMENTAR
		return true;
	}
	
	
	/*
	 * Subgrupo
	 */
	public boolean addSubGrupo(SubGrupo sg) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public boolean removeSubGrupo(SubGrupo sg) {
		//TODO: IMPLEMENTAR
		return true;
	}
	
	//ok
	public ArrayList<SubGrupo> getSubGrupos() throws SQLException{
		ArrayList<SubGrupo> ret = new ArrayList<SubGrupo>();
		ResultSet rs = doQuery("SELECT * FROM "+ms.TAB_SUB_GRUPO);
		while (rs.next()) {
			SubGrupo sg = new SubGrupo(rs.getString(ms.SG_CODIGO_SUB_GRUPO)
							   		  ,rs.getString(ms.SG_NOME));
			ret.add(sg);
		}
		return ret;
	}
	
	
	//do query ok...
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

}
