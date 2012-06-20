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
	
	public void updateWeb() {
		try {
			marcasLocal = fbc.getMarcas();
			System.out.println(this.getClass()+" - marcas Size: "+marcasLocal.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		marcasWeb = msc.getMarcas();
		
		for (Marca m : marcasWeb) {
			if (!marcasLocal.contains(m)) {
				if (msc.removeMarca(m))
					marcasWeb.remove(m);
			}
		}
		for (Marca m : marcasLocal) {
			if (!marcasWeb.contains(m)) {
				if (msc.addMarca(m))
					marcasWeb.add(m);
			}
		}
	}
}
