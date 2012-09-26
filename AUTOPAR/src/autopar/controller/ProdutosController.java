package autopar.controller;

import java.sql.SQLException;

import autopar.controller.db.MySQLDBController;
import autopar.controller.window.TelaPrincipalController;
import autopar.model.Produto;

public class ProdutosController {
	
	/*
	 * Essa classe poderia ter sido melhor aproveitada, se tivesse sido incluida desde o começo =/
	 * Cagada minha.
	 */
	
	private MySQLDBController msc;
	private TelaPrincipalController telaController;
	
	public ProdutosController (MySQLDBController msc, TelaPrincipalController telaController) {
		this.msc = msc;
		this.telaController = telaController;
	}
	
	/*
	 * Para aparecer e corretamente o checkbox
	 */
	public void setCheckBoxDestaque(Produto prod, Boolean bool) {
		if (bool == Boolean.TRUE) {
			prod.setDestaque(1);
			try {
				if (!msc.atualizaProdutoDestaque(prod)) {
					prod.setDestaque(0);
					telaController.reDesenharTableWeb();
				}
			} catch (SQLException e) {
				prod.setDestaque(0);
				autopar.Main.msg.msgError(e, this);
			}
		}
		else {
			prod.setDestaque(0);
			try {
				if (!msc.atualizaProdutoDestaque(prod)) {
					prod.setDestaque(1);
					telaController.reDesenharTableWeb();
				}
			} catch (SQLException e) {
				prod.setDestaque(1);
				autopar.Main.msg.msgError(e, this);
			}
		}
	}
	public Boolean getCheckBoxDestaque(Produto prod) {
		if (prod.getDestaque() == 1)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	public void setCheckBoxShowPreco(Produto prod, Boolean bool) {
		if (bool == Boolean.TRUE) {
			prod.setShowPreco(1);
			try {
				if (!msc.atualizaProdutoShowPreco(prod)) {
					prod.setShowPreco(0);
					telaController.reDesenharTableWeb();
				}
			} catch (SQLException e) {
				prod.setShowPreco(0);
				autopar.Main.msg.msgError(e, this);
			}
		}
		else {
			prod.setShowPreco(0);
			try {
				if (!msc.atualizaProdutoShowPreco(prod)) {
					prod.setShowPreco(1);
					telaController.reDesenharTableWeb();
				}
			} catch (SQLException e) {
				prod.setShowPreco(1);
				autopar.Main.msg.msgError(e, this);
			}
		}
	}
	public Boolean getCheckBoxShowPreco(Produto prod) {
		if (prod.getShowPreco() == 1)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
}
