package autopar.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import autopar.controller.db.FirebirdDBController;
import autopar.controller.db.MySQLDBController;
import autopar.model.Marca;

public class MarcasController {

	private ArrayList<Marca> marcasLocal;
	private ArrayList<Marca> marcasWeb;
	private FirebirdDBController fbc;
	private MySQLDBController msc;
	
	public MarcasController(FirebirdDBController fbc, MySQLDBController msc) {
		this.fbc = fbc;
		this.msc = msc;
	}
	
	public void updateWeb() throws Exception {
		try {
			marcasLocal = fbc.getMarcas();
			autopar.Main.splashController
			.setProgress("Atualizando marcas ("+marcasLocal.size()+")...", 5);
		} catch (SQLException e) {
			autopar.Main.msg.msgError(e.getMessage());
		}
		marcasWeb = msc.getMarcas();
		ArrayList<Marca> marcasWebRemove = new ArrayList<Marca>();
		ArrayList<Marca> marcasWebAdd = new ArrayList<Marca>();
		
		for (Marca m : marcasWeb) {
			if (!marcasLocal.contains(m)) {
				if (msc.removeMarca(m))
					marcasWebRemove.add(m);
			}
		}
		for (Marca m : marcasLocal) {
			if (!marcasWeb.contains(m)) {
				if (msc.addMarca(m))
					marcasWebAdd.add(m);
			}
		}
		
		for (Marca m : marcasWebRemove)
			marcasWeb.remove(m);
		
		for (Marca m : marcasWebAdd)
			marcasWeb.add(m);
		
	}
}
