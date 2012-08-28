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
	
	public void updateWeb() throws Exception {
		try {
			subGruposLocal = fbc.getSubGrupos();
			autopar.Main.splashController
			.setProgress("Atualizando subgrupos ("+subGruposLocal.size()+")...", 5);	
		} catch (SQLException e) {
			autopar.Main.msg.msgError(e.getMessage());
		}
		subGruposWeb = msc.getSubGrupos();
		
		ArrayList<SubGrupo> subGruposWebAdd = new ArrayList<SubGrupo>();
		ArrayList<SubGrupo> subGruposWebRemove = new ArrayList<SubGrupo>();
		
		for (SubGrupo sg : subGruposWeb) {
			if (!subGruposLocal.contains(sg)) {
				if (msc.removeSubGrupo(sg))
					subGruposWebRemove.add(sg);
			}
		}
		for (SubGrupo sg : subGruposLocal) {
			if (!subGruposWeb.contains(sg))
				if (msc.addSubGrupo(sg))
					subGruposWebAdd.add(sg);
		}
		
		for (SubGrupo sg : subGruposWebRemove)
			subGruposWeb.remove(sg);
		
		for (SubGrupo sg : subGruposWebAdd)
			subGruposWeb.add(sg);
		
	}
}
