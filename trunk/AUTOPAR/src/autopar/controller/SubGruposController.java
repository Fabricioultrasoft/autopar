package autopar.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.SubGrupo;

public class SubGruposController {

	private ArrayList<SubGrupo> subGruposLocal;
	private ArrayList<SubGrupo> subGruposWeb;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	
	public SubGruposController(FirebirdDBController fbc, MySQLDBController msc) {
		this.fbc = fbc;
		this.msc = msc;
	}
	
	public void updateWeb() {
		try {
			subGruposLocal = fbc.getSubGrupos();
			System.out.println(this.getClass()+" - subGruposLocal Size: "+subGruposLocal.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		subGruposWeb = msc.getSubGrupos();
		for (SubGrupo sg : subGruposWeb) {
			if (!subGruposLocal.contains(sg)) {
				if (msc.removeSubGrupo(sg))
					subGruposWeb.remove(sg);
			}
		}
		for (SubGrupo sg : subGruposLocal) {
			if (!subGruposWeb.contains(sg))
				if (msc.addSubGrupo(sg))
					subGruposWeb.add(sg);
		}
	}
}
