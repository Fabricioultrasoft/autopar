package autopar.controller.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSetRow;

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
	
	public boolean addMarca(Marca m) throws SQLException {
		int val = ms.getStmnt().executeUpdate("INSERT INTO "+ms.TAB_MARCA+" ("
									  +ms.M_CODIGO_MARCA+","
									  +ms.M_NOME
									  +") VALUES ("
									  +m.getCodigoMarca()+","
									  +"'"+m.getNome()+"')");
		if(val == 1)
			return true;
		else
			return false;
	}
	
	public boolean removeMarca(Marca m) throws SQLException {
		
		int val = ms.getStmnt().executeUpdate("DELETE FROM "+ms.TAB_MARCA+" WHERE "+ms.M_CODIGO_MARCA+" = "+m.getCodigoMarca());
		
		if(val == 1)
			return true;
		else
			return false;
	}

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
	public boolean addGrupo(Grupo g) throws SQLException {
		int val = ms.getStmnt().executeUpdate("INSERT INTO "+ms.TAB_GRUPO+" ("
				  +ms.G_CODIGO_GRUPO+","
				  +ms.G_NOME
				  +") VALUES ("
				  +g.getCodigoGrupo()+","
				  +"'"+g.getNome()+"')");
		if(val == 1)
			return true;
		else
			return false;
	}
	
	public boolean removeGrupo(Grupo g) throws SQLException {
		int val = ms.getStmnt().executeUpdate("DELETE FROM "+ms.TAB_GRUPO+" WHERE "+ms.G_CODIGO_GRUPO+" = "+g.getCodigoGrupo());
		
		if(val == 1)
			return true;
		else
			return false;
	}
	
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
	
	public ArrayList<Produto> getProdutos() throws SQLException {
		ArrayList<Produto> ret = new ArrayList<Produto>();
		int progCoef = getRowCount(ms.TAB_PRODUTO) / 10;
		int i = 0;
		ResultSet rs = doQuery("SELECT * FROM "+ms.TAB_PRODUTO+" ORDER BY "+ms.P_NOME);
		while (rs.next()) {
			if (progCoef != 0 && i % progCoef == 0)
				splashProgress(i/progCoef+"0%");
			Produto p = new Produto(rs.getString(ms.P_CODIGO) 
								   ,rs.getString(ms.P_NOME)
								   ,rs.getString(ms.P_DESCRICAO)
								   ,rs.getString(ms.P_PRECO)
								   ,rs.getString(ms.P_CODIGO_MARCA)
								   ,rs.getString(ms.P_CODIGO_SUB_GRUPO)
								   ,rs.getString(ms.P_CODIGO_GRUPO));
			p.setImagens(this.getImagens(p));
			ret.add(p);
			i++;
		}
		return ret;
	}
	
	public boolean addProduto(Produto p) throws SQLException {
		int val = ms.getStmnt().executeUpdate("INSERT INTO "+ms.TAB_PRODUTO+" ("
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
	
	public boolean atualizaProduto(Produto p) throws SQLException {
		
		int val = ms.getStmnt().executeUpdate("UPDATE "+ms.TAB_PRODUTO+" SET "+ms.P_NOME+"='"+p.getNome()+"',"
																	 +ms.P_DESCRICAO+"='"+p.getDescricao()+"',"
																	 +ms.P_PRECO+"="+p.getPreco()+","
																	 +ms.P_CODIGO_MARCA+"="+p.getCodigoMarca()+","
																	 +ms.P_CODIGO_GRUPO+"="+p.getCodigoGrupo()+","
																	 +ms.P_CODIGO_SUB_GRUPO+"="+p.getCodigoSubGrupo()+" WHERE "+ms.P_CODIGO+"="+p.getCodigo()
																	 );
		
		if(val == 1)
			return true;
		else
			return false;
	}
	
	public boolean removeProduto(Produto p) throws SQLException {
		
		int val = ms.getStmnt().executeUpdate("DELETE FROM "+ms.TAB_PRODUTO+" WHERE "+ms.P_CODIGO+" = "+p.getCodigo());
		
		if(val == 1)
			return true;
		else
			return false;
		
	}
	
	/*
	 * FOTOS
	 */
	
	public boolean addImagem(Produto p, String nomeImagem) throws SQLException {
		int val = ms.getStmnt().executeUpdate("INSERT INTO "+ms.TAB_PRODUTOS_FOTOS+" ("
				  +ms.PF_NOME+","
				  +ms.PF_PRODUTO
				  +") VALUES ("
				  +"'"+nomeImagem+"',"
				  +"'"+p.getCodigo()+"')");
		if(val == 1) {
			p.addImagem(nomeImagem);
			return true;
		} else
			return false;
	}
	
	public boolean removeImagem(Produto p, String nomeImagem) throws SQLException {
		int val = ms.getStmnt().executeUpdate("DELETE FROM "+ms.TAB_PRODUTOS_FOTOS+" WHERE "+ms.PF_NOME+" = '"+nomeImagem+"'");
		
		if(val == 1){
			p.removeImagem(nomeImagem);
			return true;
		}else
			return false;
	}
	
	//select de todas as fotos...
	
	public ArrayList<String> getImagens(Produto p) throws SQLException{
		ArrayList<String> ret = new ArrayList<String>();
		
		ResultSet rs = doQuery("SELECT "+ms.PF_NOME+" FROM "+ms.TAB_PRODUTOS_FOTOS+" WHERE "+ms.PF_PRODUTO+" = '"+p.getCodigo()+"'");
		while (rs.next()) {
			ret.add(rs.getString(ms.PF_NOME));
		}
		return ret;
	}
	
	/*
	 * SUBGRUPO
	 */
	
	public boolean addSubGrupo(SubGrupo sg) throws SQLException{
		int val = ms.getStmnt().executeUpdate("INSERT INTO "+ms.TAB_SUB_GRUPO+" ("
				  +ms.SG_CODIGO_SUB_GRUPO+","
				  +ms.SG_NOME
				  +") VALUES ("
				  +sg.getCodigoSubGrupo()+","
				  +"'"+sg.getNome()+"')");
		
		if(val == 1)
			return true;
		else
			return false;
	}
	
	public boolean removeSubGrupo(SubGrupo sg) throws SQLException {
		
		int val = ms.getStmnt().executeUpdate("DELETE FROM "+ms.TAB_SUB_GRUPO+" WHERE "+ms.SG_CODIGO_SUB_GRUPO+" = "+sg.getCodigoSubGrupo());
		
		if(val == 1)
			return true;
		else
			return false;
		
	}
	
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
	
	//método padrão doQuery!!!
	public ResultSet doQuery (String query) {
		ResultSet res = null;
		try 
		{
			stmnt = ms.getStmnt();
			res = stmnt.executeQuery(query);
		} 
		catch (SQLException e) { e.printStackTrace(); }
		
		return res;
	}
	
	
	public int getRowCount (String table) {
		ResultSet rs = doQuery("SELECT count(*) FROM "+table);
		try {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) { e.printStackTrace(); }
		return 0;
	}
	
	/**
	 * Metodo padrão para atualizar o progresso das ações no Splash
	 */
	public void splashProgress() {
		autopar.Main.splashController
		.setProgress("Carregando produtos da WEB...", 1);
	}
	public void splashProgress(String s) {
		autopar.Main.splashController
		.setProgress("Carregando produtos da WEB ("+s+")...", 1);
	}
	
	/**
	 * Metodo padrão para atualizar o progresso das ações na janela informativa
	 */
	public void infoProgress() {
		
	}
}
