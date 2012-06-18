package autopar.controller.db;

import java.sql.SQLException;
import java.util.ArrayList;

import autopar.model.Grupo;
import autopar.model.Marca;
import autopar.model.Produto;
import autopar.model.SubGrupo;
import autopar.model.db.MySQLDB;

public class MySQLDBController {
	MySQLDB ms;
	
	public MySQLDBController(MySQLDB ms) {
		this.ms = ms;
	}
	
	/*
	 * Produto
	 */
	public ArrayList<Produto> getProdutos() throws SQLException {
		//TODO: IMPLEMENTAR
		return new ArrayList<Produto>();
	}
	public boolean addProduto(Produto p) {
		//TODO: IMPLEMENTAR
		return true;
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
	 * Grupo
	 */
	public boolean addGrupo(Grupo g) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public boolean removeGrupo(Grupo g) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public ArrayList<Grupo> getGrupos() {
		//TODO: IMPLEMENTAR
		return new ArrayList<Grupo>();
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
	public ArrayList<SubGrupo> getSubGrupos() {
		//TODO: IMPLEMENTAR
		return new ArrayList<SubGrupo>();
	}
	
	
	/*
	 * Marca
	 */
	public boolean addMarca(Marca m) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public boolean removeMarca(Marca m) {
		//TODO: IMPLEMENTAR
		return true;
	}
	public ArrayList<Marca> getMarcas() {
		//TODO: IMPLEMENTAR
		return new ArrayList<Marca>();
	}
}
