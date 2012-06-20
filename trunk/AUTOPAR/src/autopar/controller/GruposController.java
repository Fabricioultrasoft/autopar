package autopar.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.Grupo;

public class GruposController {
	
	private ArrayList<Grupo> gruposLocal;
	private ArrayList<Grupo> gruposWeb;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	
	public GruposController(FirebirdDBController fbc, MySQLDBController msc) {
		this.fbc = fbc;
		this.msc = msc;
	}
	
	public void updateWeb() {
		try {
			gruposLocal = fbc.getGrupos(); 
			System.out.println(this.getClass()+" - gruposLocal Size: "+gruposLocal.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gruposWeb = msc.getGrupos();
		for (Grupo g : gruposWeb) {
			if (!gruposLocal.contains(g)) {
				if (msc.removeGrupo(g))
					gruposWeb.remove(g);
			}
		}
		for (Grupo g : gruposLocal) {
			if (!gruposWeb.contains(g)) {
				if (msc.addGrupo(g))
					gruposWeb.add(g);
			}
		}
	}
}
